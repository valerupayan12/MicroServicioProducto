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

import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.service.impl.CategoriaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    // Inyecta los mocks dentro de la implementación real del servicio (sin levantar el contexto de Spring).
    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = crearCategoria();
    }

    @Test
    public void testGetCategorias() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<Categoria> categorias = categoriaService.getCategorias();

        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        assertEquals(categoria.getId_categoria(), categorias.get(0).getId_categoria());
    }

    @Test
    public void testGetCategoria() {
        int id = 1;
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        Categoria found = categoriaService.getCategoria(id);

        assertNotNull(found);
        assertEquals(id, found.getId_categoria());
    }

    @Test
    public void testSaveCategoria() {
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria saved = categoriaService.saveCategoria(categoria);

        assertNotNull(saved);
        assertEquals(true, saved.isEstado());
    }

    @Test
    public void testUpdateCategoria() {
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        int resultado = categoriaService.updateCategoria(categoria);

        assertEquals(1, resultado);
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    public void testDeleteCategoria() {
        int id = 1;

        int resultado = categoriaService.deleteCategoria(id);

        assertEquals(1, resultado);
        verify(categoriaRepository, times(1)).deleteById(id);
    }

    private Categoria crearCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId_categoria(1);
        categoria.setNombre("Test Categoria");
        categoria.setDescripcion("Descripción de prueba");
        categoria.setEstado(true);
        return categoria;
    }
}