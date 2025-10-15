package pe.idat.template.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.idat.template.dto.request.LibroCreateDTO;
import pe.idat.template.dto.request.LibroUpdateDTO;
import pe.idat.template.dto.response.LibroResponseDTO;
import pe.idat.template.service.LibroService;
import pe.idat.template.dto.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Api para gestion de libros")
@Validated
public class LibroController {
    @Autowired
    private LibroService libroService;

    //Get
    @Operation(summary = "Obtener Todos los libros")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de libros obtenida exitosamente")
    @GetMapping
    public ResponseEntity<ApiResponse<List<LibroResponseDTO>>> obtenerLibros() {
        List<LibroResponseDTO> libros = libroService.listarLibros();
        return ResponseEntity.ok(ApiResponse.success("Lista de libros obtenida exitosamente", libros));
    }

    //Get por id
    @Operation(summary = "Obtener Libro por ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LibroResponseDTO>> obtenerLibroBYID(@Parameter(description = "Id Del Libro") @PathVariable Long id) {
        LibroResponseDTO libro = libroService.libroById(id);
        return ResponseEntity.ok(ApiResponse.success("Libro encontrado", libro));
    }

    //Get por Autor
    @Operation(summary = "Obtener Libro por Autor")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Libro del Autor Encontrador"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Libro del Autor no encontrado")
    })
    @GetMapping("/autor")
    public ResponseEntity<ApiResponse<List<LibroResponseDTO>>> obtenerLibroByAutor(@Parameter(description = "Nombre del autor") @RequestParam String autor) {
        List<LibroResponseDTO> libro = libroService.listarLibrosPorAutor(autor);
        return ResponseEntity.ok(ApiResponse.success("Libro encontrado", libro));
    }

    //Get por ISBN
    @Operation(summary = "Obtener Libro por ISBN")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Libro Con el ISBN encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Libro COn el ISBN No encontrado")
    })
    @GetMapping("/isbn")
    public ResponseEntity<ApiResponse<LibroResponseDTO>> obtenerLibroByISBN(@Parameter(description = "Ingresa el ISBN: ") @RequestParam String isbn) {
        LibroResponseDTO libro = libroService.listarLibroPorISBN(isbn);
        return ResponseEntity.ok(ApiResponse.success("Libro Con el ISBN:" + isbn +   "encontrado", libro));
    }
    // Get por stock
    @Operation(summary = "Obtener libros con stock mayor a un valor")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Libros obtenidos correctamente"
    )
    @GetMapping("/stock")
    public ResponseEntity<ApiResponse<List<LibroResponseDTO>>> obtenerLibrosPorStock(
            @RequestParam @Min(0) Integer stock) {

        List<LibroResponseDTO> libros = libroService.listarLibroPorStock(stock);
        return ResponseEntity.ok(
                ApiResponse.success("Libros con stock mayor a " + stock + " obtenidos", libros)
        );
    }

    //Registrar lIbro
    @Operation(summary = "Registrar un nuevo Libro")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Libro Registrado COrrectamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "ISBN ya existe")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<LibroResponseDTO>> registrarLibro(
            @Valid @RequestBody LibroCreateDTO request) {

        LibroResponseDTO libro = libroService.registrarLibro(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Libro registrado correctamente", libro));
    }

    //Actualizar Libro
    @Operation(summary = "Actualizar Libro")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Libro actualizado correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Libro no encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "ISBN ya existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LibroResponseDTO>> actualizarLibro(@Parameter(description = "Id del libro") @PathVariable Long id,
                                                                         @RequestBody @Valid LibroUpdateDTO libroUpdateDTO){
        LibroResponseDTO libro = libroService.actualizar(id, libroUpdateDTO);
        return ResponseEntity.ok(ApiResponse.success("Libro actualizado correctamente", libro));
    }

    //Eliminar Libro
    @Operation(summary = "Eliminar Libro")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Libro eliminado correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarLibro(@Parameter(description = "Id del Libro")@PathVariable Long id){
        libroService.eliminarLibro(id);
        return ResponseEntity.ok(ApiResponse.success("Libro eliminado correctamente"));
    }

}
