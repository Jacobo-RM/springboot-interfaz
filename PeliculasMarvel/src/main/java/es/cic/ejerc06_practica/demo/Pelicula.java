package es.cic.ejerc06_practica.demo;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    private Director director;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "pelicula_actor",
        joinColumns = @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actores;
    private String fechaEstreno;
    private double rating;

    public Pelicula() {}

    public Pelicula(int id, String nombre, Director director, Set<Actor> actores, String fechaEstreno, double rating) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.actores = actores;
        this.fechaEstreno = fechaEstreno;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Actor> getActores() {
        return actores;
    }

    public void setActores(Set<Actor> actores) {
        this.actores = actores;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
