package es.cic.ejerc06_practica.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

    List<Pelicula> findByNombreContainingIgnoreCase (String nombre);
    List<Pelicula> findByActoresNombreContainingIgnoreCase(String nombreActor);
    List<Pelicula> findByRatingGreaterThanEqual (double rating);

}
