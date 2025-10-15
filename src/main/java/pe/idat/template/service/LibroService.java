package pe.idat.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.template.dto.request.LibroCreateDTO;
import pe.idat.template.dto.request.LibroUpdateDTO;
import pe.idat.template.dto.response.LibroResponseDTO;
import pe.idat.template.entity.Libro;
import pe.idat.template.exception.LibroNotFoundException;
import pe.idat.template.mapper.LibroMapper;
import pe.idat.template.repository.LibroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroMapper libroMapper;

    @Transactional
    //Listar libros todos
    public List<LibroResponseDTO> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libroMapper.toResponseDTOList(libros);
    }

    //Listar los libros por id
    public LibroResponseDTO libroById(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException("Libro no encontrado con ID:" + id));
        return libroMapper.toResponseDTO(libro);
    }
    //Listar los libros por autor
    public List<LibroResponseDTO> listarLibrosPorAutor(String autor) {
        List<Libro> libros = libroRepository.findByAutor(autor);
        return libroMapper.toResponseDTOList(libros);
    }
    //Listar libro por ISBN
    public LibroResponseDTO listarLibroPorISBN(String isbn) {
        Libro libro = libroRepository.findByIsbn(isbn).orElseThrow(() -> new LibroNotFoundException("Libro no encontrado con ISBN: " + isbn));
        return libroMapper.toResponseDTO(libro);
    }

    //Listar libro por stock
    public List<LibroResponseDTO> listarLibroPorStock(Integer stock) {
        List<Libro> libros = libroRepository.findByStockGreaterThan(stock);
        return libroMapper.toResponseDTOList(libros);
    }

    //REgistrar Libro
    public LibroResponseDTO registrarLibro(LibroCreateDTO libroDTO) {
        libroRepository.findByIsbn(libroDTO.getIsbn()).ifPresent(libro -> {
            throw new DataIntegrityViolationException("ISBN ya registrado: " + libroDTO.getIsbn());
        });
        Libro libro = libroMapper.toEntity(libroDTO);
        Libro libroRegistrado = libroRepository.save(libro);
        return libroMapper.toResponseDTO(libroRegistrado);

    }

    //Actualizar Libro
    public LibroResponseDTO actualizar(Long id, LibroUpdateDTO dto) {
        Libro base = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException("Libro no encontrado con ID: " + id));

        libroMapper.updateEntityFromDTO(dto, base);

        if (dto.getIsbn() != null) {
            libroRepository.findByIsbn(dto.getIsbn())
            .filter(l -> !l.getId().equals(id))
            .ifPresent(l -> { throw new DataIntegrityViolationException("ISBN ya registrado: " + dto.getIsbn()); });
        }

        Libro actualizado = libroRepository.save(base);
        return libroMapper.toResponseDTO(actualizado);
    }
    //Eliminar Libro
    public void eliminarLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNotFoundException("Libro no encontrado con ID: " + id);
        }
        libroRepository.deleteById(id);
    }
}
