package org.carpio.com.demoapp5.dto.mapper;

import org.carpio.com.demoapp5.dto.ProductoDto;
import org.carpio.com.demoapp5.model.Producto;

import java.util.List;

public class ProductoMapper {
    public static ProductoDto toDto(Producto producto) {
        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .build();
    }
    public static List<ProductoDto> toDtoList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::toDto).toList();
    }
    public static Producto toEntity(ProductoDto productoDto) {
        return new Producto(
                productoDto.getId(),
                productoDto.getNombre(),
                productoDto.getPrecio()
        );
    }
}
