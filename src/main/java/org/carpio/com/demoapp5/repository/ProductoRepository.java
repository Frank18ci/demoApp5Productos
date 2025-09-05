package org.carpio.com.demoapp5.repository;

import org.carpio.com.demoapp5.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByNombreContaining(String nombre, Pageable pageable);
    Page<Producto> findByNombreContainingAndPrecioBetween(String nombre, Double minPrecio, Double maxPrecio, Pageable pageable);
}
