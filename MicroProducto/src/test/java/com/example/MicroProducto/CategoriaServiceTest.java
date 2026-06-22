package com.example.MicroProducto;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.service.CategoriaService;

@SpringBootTest
@ActiveProfiles("test")
public class CategoriaServiceTest  {

    @Autowired
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    public void testFindAll() {
        Categoria categoria = crearCategoria();
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<Categoria> categorias = categoriaService.findAll();
        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        assertEquals(categoria.getId(), categorias.get(0).getId());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Categoria categoria = crearCategoria();
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        Categoria found = categoriaService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Categoria categoria = crearCategoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria saved = categoriaService.save(categoria);
        assertNotNull(saved);
        assertEquals(1, saved.getEstado());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(categoriaRepository).deleteById(id);

        categoria   Service.deleteById(id);
        verify(categoriaRepository, times(1)).deleteById(id);
    }

    private Categoria crearCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Test Categoria");
        return categoria;
    }
}