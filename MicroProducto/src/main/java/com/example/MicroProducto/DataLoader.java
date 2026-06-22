package com.example.MicroProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.repository.ProductoRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
  
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar tipos de categoriia
        for (int i = 0; i < 3; i++) {
            Categoria tipoSala = new Categoria();
            tipoSala.setId_categoria(i + 1);
            tipoSala.setNombre(faker.book().genre());
            categoriaRepository.save(tipoSala);
        }

        // Generar categorías
        for (int i = 0; i < 5; i++) {
            Categoria categoria = new Categoria();
            categoria.setCodigo(faker.code().asin());
            categoria.setNombre(faker.educator().course());
            categoriaRepository.save(categoria);
        }

        List<Categoria> categorias = categoriaRepository.findAll();

        // Generar productos
        for (int i = 0; i < 50; i++) {
            Producto producto = new Producto();
            producto.setId(i + 1);
            producto.setRun(faker.idNumber().valid());
            producto.setNombres(faker.name().fullName());
            producto.setCorreo(faker.internet().emailAddress());
            producto.setJornada(faker.options().option("D", "N").charAt(0));
            producto.setTelefono(faker.number().numberBetween(100000000, 999999999));
            producto.setCategoria(categorias.get(random.nextInt(categorias        .size())));
            productoRepository.save(producto);
        }
    }
}