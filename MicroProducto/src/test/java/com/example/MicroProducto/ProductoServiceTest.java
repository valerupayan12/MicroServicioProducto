package com.example.MicroProducto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroProducto.dto.ProductoDTO;
import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.repository.ProductoRepository;
import com.example.MicroProducto.service.impl.ProductoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    // Inyecta los mocks dentro de la implementacion real del servicio (sin levantar el contexto de Spring).
    @InjectMocks
    private ProductoServiceImpl productoService;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Producto producto;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria();
        categoria.setId_categoria(1);
        categoria.setNombre("Electrónica");
        categoria.setDescripcion("Productos electrónicos");
        categoria.setEstado(true);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setCategoria(categoria);
        producto.setPrecio_base(100);
        producto.setEstado(true);
    }

    @Test
    public void testListarTodos() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<ProductoDTO.Response> productos = productoService.listarTodos();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals(producto.getId(), productos.get(0).getId());
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        ProductoDTO.Response found = productoService.buscarPorId(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testCrear() {
        ProductoDTO.Request request = new ProductoDTO.Request(
                "Producto 1",
                "Descripción del producto 1",
                "Electrónica",
                100,
                true
        );

        when(productoRepository.existsByNombreIgnoreCase("Producto 1")).thenReturn(false);
        when(categoriaRepository.findByNombre("Electrónica")).thenReturn(Optional.of(categoria));
        when(productoRepository.save(org.mockito.ArgumentMatchers.any(Producto.class))).thenReturn(producto);

        ProductoDTO.Response saved = productoService.crear(request);

        assertNotNull(saved);
        assertEquals(true, saved.isEstado());
        assertEquals("Producto 1", saved.getNombre());
    }

    @Test
    public void testEliminar() {
        Long id = 1L;
        when(productoRepository.existsById(id)).thenReturn(true);

        productoService.eliminar(id);

        verify(productoRepository, times(1)).deleteById(id);
    }
}