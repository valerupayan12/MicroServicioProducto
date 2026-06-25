package com.example.MicroProducto;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.repository.ProductoRepository;
import com.github.javafaker.Faker;

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

        // Generar categorías
        for (int i = 0; i < 5; i++) {
            Categoria categoria = new Categoria();
            categoria.setId_categoria(i + 1);
            categoria.setNombre(faker.commerce().department());
            categoria.setDescripcion(faker.lorem().sentence());
            categoria.setEstado(true);
            categoriaRepository.save(categoria);
        }

        List<Categoria> categorias = categoriaRepository.findAll();

        // Generar productos
        for (int i = 0; i < 50; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setCategoria(categorias.get(random.nextInt(categorias.size())));
            producto.setPrecio_base(random.nextInt(100000) + 1000);
            producto.setEstado(true);
            productoRepository.save(producto);
        }
    }
}