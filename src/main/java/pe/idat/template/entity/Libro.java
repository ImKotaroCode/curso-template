package pe.idat.template.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Table(name = "libro")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 200)
    @Size(max = 200)
    private String titulo;

    @NotBlank
    @Column(nullable = false, length = 100)
    @Size(max = 100)
    private String autor;

    @NotBlank
    @Column(nullable = false, length = 13, unique = true)
    @Size(min = 13, max = 13, message = "El ISBN debe tener exactamente 13 caracteres")
    private String isbn;

    @NotNull
    @Column(nullable = false)
    @Min(1500) @Max(2025)
    private Integer anioPublicacion;

    @NotNull
    @Column(nullable = false)
    private Integer stock = 0;

    @NotNull
    @Column(nullable = false)
    @DecimalMin(value="0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private Double precio;
}
