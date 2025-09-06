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
    Page<ProductoDto> generarReporteProductosv1Paginado(String name, int page, int size, String sortBy, String direction);
    Page<ProductoDto> generarReporteProductosv2Paginado(String name, Double minPrice, Double maxPrice, int page, int size, String sortBy, String direction);

    List<ProductoDto> generarReporteProductosv1NoPaginado(String name);

    String getOneProduct(Long id);

}
