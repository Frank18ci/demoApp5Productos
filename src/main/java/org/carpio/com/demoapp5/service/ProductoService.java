package org.carpio.com.demoapp5.service;

import org.carpio.com.demoapp5.dto.ProductoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {
    List<ProductoDto> getAllProductos();
    ProductoDto getProductoById(Long id);
    ProductoDto createProducto(ProductoDto productoDto);
    ProductoDto updateProducto(Long id, ProductoDto productoDto);
    void deleteProducto(Long id);
    Page<ProductoDto> generarReporteProductosv1Paginado(String nombre, int page, int size, String sortBy, String direction);
    Page<ProductoDto> generarReporteProductosv2Paginado(String nombre, Double minPrecio, Double maxPrecio, int page, int size, String sortBy, String direction);

    List<ProductoDto> generarReporteProductosv1NoPaginado(String nombre);


}
