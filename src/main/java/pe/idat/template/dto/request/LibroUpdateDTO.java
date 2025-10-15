package pe.idat.template.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroUpdateDTO {
    @Size(max = 200)
    private String titulo;

    @Size(max = 100)
    private String autor;

    @Size(min = 13, max = 13, message = "El ISBN debe tener exactamente 13 caracteres")
    private String isbn;

    @Min(1500)
    @Max(2025)
    private Integer anioPublicacion;

    @Min(0)
    private Integer stock;

    @DecimalMin(value = "0.0", inclusive = true)
    private Double precio;


}
