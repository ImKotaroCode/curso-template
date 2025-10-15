package pe.idat.template.mapper;
import org.mapstruct.*;
import pe.idat.template.dto.request.LibroCreateDTO;
import pe.idat.template.dto.request.LibroUpdateDTO;
import pe.idat.template.dto.response.LibroResponseDTO;
import pe.idat.template.entity.Libro;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface LibroMapper {

    @Mapping(target = "id", ignore = true)
    Libro toEntity(LibroCreateDTO dto);

    LibroResponseDTO toResponseDTO(Libro libro);


    List<LibroResponseDTO> toResponseDTOList(List<Libro> libros);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(LibroUpdateDTO dto, @MappingTarget Libro libro);
}
