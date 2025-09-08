package org.carpio.com.demoapp5.repository;

import org.carpio.com.demoapp5.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByNombreContaining(String nombre, Pageable pageable);
    Page<Producto> findByNombreContainingAndPrecioBetween(String nombre, Double minPrecio, Double maxPrecio, Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) like lower(concat(:nombre, '%'))")
    List<Producto> findAllProductosByNombrePersonalizado(@Param("nombre") String nombre);

}
