package es.cic.ejerc06_practica.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*")
@RequestMapping("/api/cine")
@RestController
public class PeliculasController {
    
    @Autowired
    private PeliculasService peliculasService;

    @GetMapping("/peliculas")
    public List<Pelicula> getPeliculas() throws Exception {
        return peliculasService.getPeliculas();
    }

    @GetMapping("/peliculasOrdenadas")
    public List<Pelicula> getPeliculasOrdenAlfabetico() throws Exception {
        return peliculasService.getPeliculasOrdenAlfabetico();
    }

    @GetMapping("/pelicula/{id}")
    public Pelicula getPeliculaPorId(@PathVariable ("id") int id) throws Exception{
        return peliculasService.getPeliculaById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Pelicula> getPeliculasByNombre(@PathVariable("nombre") String nombre) throws Exception {
        return peliculasService.getPeliculaByNombre(nombre);
    }

    @GetMapping("/actor/{nombreActor}")
    public List<Pelicula> getPeliculasByActores(@PathVariable("nombreActor") String nombreActor) throws Exception {
        Actor actor = new Actor();
        actor.setNombre(nombreActor);
        
        return peliculasService.getPeliculaByActores(actor);
    }

    @GetMapping("/actores")
    public List<Actor> getActores() throws Exception {
        return peliculasService.getActores();
    }

    @GetMapping("/rating/{rating}")
    public List<Pelicula> getPeliculasByRating(@PathVariable("rating") double rating) throws Exception{
        return peliculasService.getPeliculasByRating(rating);
    }

    @PostMapping("/anadirPelicula")
    @ResponseStatus(HttpStatus.CREATED)
    public Pelicula anadirPelicula(@RequestBody Pelicula pelicula) throws Exception{
        return peliculasService.addPelicula(pelicula);
    }

    @PutMapping("/actualizarPelicula/{id}/{nombre}")
    public Pelicula actualizarPelicula(@PathVariable("id") int id, @PathVariable("nombre") String nombre) throws Exception{
        return peliculasService.updatePelicula(id, nombre);
    
    }

    @DeleteMapping("/borrarPelicula/{id}")
    public boolean borrarPelicula(@PathVariable ("id") int id) throws Exception{
        return this.peliculasService.deletePelicula(id);
    }
}

