package com.example.MicroProducto;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.repository.ProductoRepository;
import com.example.MicroProducto.service.ProductoService;

public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        Producto producto = crearProducto();
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> productos = productoService.findAll();
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals(producto.getId(), productos.get(0).getId());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Producto producto = crearProducto();
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        Producto found = productoService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Producto producto = crearProducto();
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = productoService.save(producto);
        assertNotNull(saved);
        assertEquals(1, saved.getEstado());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(productoRepository).deleteById(id);

        productoService.deleteById(id);
        verify(productoRepository, times(1)).deleteById(id);
    }

    private Producto crearProducto() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setPrecio(100.0);
        producto.setEstado(1);
        return producto;
    }
}   