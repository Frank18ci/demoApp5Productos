package org.carpio.com.demoapp5.controller;

import lombok.RequiredArgsConstructor;
import org.carpio.com.demoapp5.dto.ProductoDto;
import org.carpio.com.demoapp5.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;
    @GetMapping
    public ResponseEntity<?> getAllProductos(){
        return ResponseEntity.ok(productoService.getAllProductos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.getProductoById(id));
    }
    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody ProductoDto productoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.createProducto(productoDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoService.updateProducto(id, productoDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reporte/v1")
    public ResponseEntity<?> generarReporteProductosv1Paginado(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(productoService.generarReporteProductosv1Paginado(nombre, page, size, sortBy, direction));
    }
    @GetMapping("/reporte/v2")
    public ResponseEntity<?> generarReporteProductosv2Paginado(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double minPrecio,
            @RequestParam(required = false) Double maxPrecio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(productoService.generarReporteProductosv2Paginado(nombre, minPrecio, maxPrecio, page, size, sortBy, direction));
    }

    @GetMapping("/reporte/v1/no-paginado")
    public ResponseEntity<?> generarReporteProductosv1NoPaginado(
            @RequestParam(required = false) String nombre
    ) {
        return ResponseEntity.ok(productoService.generarReporteProductosv1NoPaginado(nombre));
    }
}
