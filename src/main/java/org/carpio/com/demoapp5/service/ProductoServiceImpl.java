package org.carpio.com.demoapp5.service;

import lombok.RequiredArgsConstructor;
import org.carpio.com.demoapp5.dto.ProductoDto;
import org.carpio.com.demoapp5.exception.BadRequestException;
import org.carpio.com.demoapp5.exception.ResourceNotFoundException;
import org.carpio.com.demoapp5.model.Producto;
import org.carpio.com.demoapp5.repository.ProductoRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{
    private final ProductoRepository productoRepository;
    private final org.carpio.com.demoapp5.util.ProductoMapper productoMapper;
    @Override
    public List<ProductoDto> getAllProductos() {
        return productoMapper.toDtoList(productoRepository.findAll());
    }

    @Override
    public ProductoDto getProductoById(Long id) {
        return productoMapper.toDto(productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto no encontrado con id: " + id)
        ));
    }

    @Override
    public ProductoDto createProducto(ProductoDto productoDto) {
        if(productoDto.getPrecio() < 0){
            throw new BadRequestException("El precio no puede ser negativo");
        }
        return productoMapper.toDto(productoRepository.save(productoMapper.toEntity(productoDto)));
    }

    @Override
    public ProductoDto updateProducto(Long id, ProductoDto productoDto) {
        Producto productoFound = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto no encontrado con id: " + id)
        );
        if(productoDto.getPrecio() < 0){
            throw new BadRequestException("El precio no puede ser negativo");
        }
        productoFound.setName(productoDto.getNombre());
        productoFound.setPrice(productoDto.getPrecio());

        return productoMapper.toDto(productoRepository.save(productoFound));
    }

    @Override
    public void deleteProducto(Long id) {
        Producto productoFound = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto no encontrado con id: " + id)
        );
        productoRepository.delete(productoFound);
    }

    @Override
    public Page<ProductoDto> generarReporteProductosv1Paginado(String nombre, int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if(direction != null && "desc".equalsIgnoreCase(direction.trim())) {
            sortDirection = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Producto> productosPage = productoRepository.findByNameContaining(nombre, pageable);

        return new PageImpl<>(
                productoMapper.toDtoList(productosPage.getContent()),
                pageable,
                productosPage.getTotalElements()
                );
    }

    @Override
    public Page<ProductoDto> generarReporteProductosv2Paginado(String nombre, Double minPrecio, Double maxPrecio, int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if(direction != null && "desc".equalsIgnoreCase(direction.trim())) {
            sortDirection = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Producto> productosPage = productoRepository.findByNameContainingAndPriceBetween(nombre, minPrecio, maxPrecio, pageable);

        return new PageImpl<>(
                productoMapper.toDtoList(productosPage.getContent()),
                pageable,
                productosPage.getTotalElements()
        );
    }
    @Override
    public List<ProductoDto> generarReporteProductosv1NoPaginado(String nombre) {
        return productoMapper.toDtoList(productoRepository.findAllProductosByNombrePersonalizado(nombre));
    }

    @Override
    public String getOneProduct(Long id) {
        Optional<Producto> p = productoRepository.findById(id);
        String nombre = p.get().getName();
        return nombre;
    }
}
