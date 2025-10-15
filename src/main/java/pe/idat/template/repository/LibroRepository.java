package pe.idat.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.idat.template.entity.Libro;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByAutor(String autor);
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findByStockGreaterThan(Integer stock);
}
