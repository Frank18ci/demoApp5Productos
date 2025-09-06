package org.carpio.com.demoapp5.util;

import org.carpio.com.demoapp5.dto.ProductoDto;
import org.carpio.com.demoapp5.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "precio", target = "price")
    })
    Producto toEntity(ProductoDto productoDto);
    @Mappings({
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "price", target = "precio")
    })
    ProductoDto toDto(Producto producto);

    List<ProductoDto> toDtoList(List<Producto> productos);

}
