package es.cic.ejerc06_practica.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PeliculasService {

    private final PeliculasRepository peliculasRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public PeliculasService(PeliculasRepository peliculasRepository, DirectorRepository directorRepository, ActorRepository actorRepository) {
        this.peliculasRepository = peliculasRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;

        if (directorRepository.count() == 0) {
            initializeDirectores();
        }
        if (actorRepository.count() == 0) {
            initializeActores();
        }
        if (peliculasRepository.count() == 0) {
            initializePeliculas();
        }
    }

    private void initializeDirectores() {
        List<Director> directores = List.of(
            new Director(1, "Jon Favreau", "Estadounidense", "1966-10-19"),
            new Director(2, "Louis Leterrier", "Francés", "1973-06-17"),
            new Director(3, "Kenneth Branagh", "Irlandés", "1960-12-10"),
            new Director(4, "Joe Johnston", "Estadounidense", "1950-05-13"),
            new Director(5, "Joss Whedon", "Estadounidense", "1964-06-23"),
            new Director(6, "Shane Black", "Estadounidense", "1961-12-16"),
            new Director(7, "Alan Taylor", "Estadounidense", "1965-06-15"),
            new Director(8, "Anthony Russo", "Estadounidense", "1970-02-03"),
            new Director(9, "James Gunn", "Estadounidense", "1966-08-05"),
            new Director(10, "Peyton Reed", "Estadounidense", "1964-08-03"),
            new Director(11, "Scott Derrickson", "Estadounidense", "1966-07-16"),
            new Director(12, "Taika Waititi", "Neozelandés", "1975-08-16"),
            new Director(13, "Ryan Coogler", "Estadounidense", "1986-05-23"),
            new Director(14, "Sam Raimi", "Estadounidense", "1959-10-23"),
            new Director(15, "Nia DaCosta", "Estadounidense", "1989-11-08"),
            new Director(16, "Tim Miller", "Estadounidense", "1964-11-16"),
            new Director(17, "James Mangold", "Estadounidense", "1963-12-16"),
            new Director(18, "Chloé Zhao", "China", "1982-03-31"),
            new Director(19, "Cate Shortland", "Australiana", "1968-08-10")
        );
        directorRepository.saveAll(directores);
    }
    private void initializeActores() {
        List<Actor> actores = List.of(
            new Actor(1, "Robert Downey Jr.", 59, true),
            new Actor(2, "Gwyneth Paltrow", 51, true),
            new Actor(3, "Terrence Howard", 54, true),
            new Actor(4, "Edward Norton", 55, true),
            new Actor(5, "Liv Tyler", 46, true),
            new Actor(6, "Tim Roth", 63, true),
            new Actor(7, "Don Cheadle", 59, true),
            new Actor(8, "Chris Hemsworth", 41, true),
            new Actor(9, "Natalie Portman", 43, true),
            new Actor(10, "Tom Hiddleston", 43, true),
            new Actor(11, "Chris Evans", 43, true),
            new Actor(12, "Hayley Atwell", 42, true),
            new Actor(13, "Sebastian Stan", 41, true),
            new Actor(14, "Mark Ruffalo", 56, true),
            new Actor(15, "Chris Pratt", 45, true),
            new Actor(16, "Vin Diesel", 56, true),
            new Actor(17, "Bradley Cooper", 49, true),
            new Actor(18, "Paul Rudd", 55, true),
            new Actor(19, "Michael Douglas", 79, true),
            new Actor(20, "Evangeline Lilly", 45, true),
            new Actor(21, "Chadwick Boseman", 43, true),
            new Actor(22, "Michael B. Jordan", 37, true),
            new Actor(23, "Lupita Nyong'o", 41, true),
            new Actor(24, "Zendaya", 27, true),
            new Actor(25, "Jake Gyllenhaal", 43, true),
            new Actor(26, "Scarlett Johansson", 39, true),
            new Actor(27, "Florence Pugh", 28, true),
            new Actor(28, "David Harbour", 49, true),
            new Actor(29, "Simu Liu", 34, true),
            new Actor(30, "Awkwafina", 35, true),
            new Actor(31, "Tony Chiu-Wai Leung", 61, true),
            new Actor(32, "Gemma Chan", 41, true),
            new Actor(33, "Richard Madden", 37, true),
            new Actor(34, "Angelina Jolie", 49, true),
            new Actor(35, "Tom Holland", 28, true),
            new Actor(36, "Benedict Cumberbatch", 48, true),
            new Actor(37, "Elizabeth Olsen", 35, true),
            new Actor(38, "Jonathan Majors", 34, true),
            new Actor(39, "Brie Larson", 35, true),
            new Actor(40, "Samuel L. Jackson", 75, true),
            new Actor(41, "Teyonah Parris", 36, true),
            new Actor(42, "Iman Vellani", 22, true),
            new Actor(43, "Ryan Reynolds", 48, true),
            new Actor(44, "Morena Baccarin", 44, true),
            new Actor(45, "T.J. Miller", 43, true),
            new Actor(46, "Hugh Jackman", 55, true),
            new Actor(47, "Patrick Stewart", 84, true),
            new Actor(48, "Dafne Keen", 19, true)
        );
        actorRepository.saveAll(actores);
    }
    private void initializePeliculas() {
        List<Director> directores = directorRepository.findAll();
        List<Actor> actores = actorRepository.findAll();
        List<Pelicula> peliculas = new LinkedList<>();
    
        peliculas.add(new Pelicula(1, "Iron Man", directores.stream().filter(d -> d.getNombre().equals("Jon Favreau")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Gwyneth Paltrow", "Terrence Howard").contains(a.getNombre())).collect(Collectors.toSet()), "2008-05-02", 4.8));
        peliculas.add(new Pelicula(2, "The Incredible Hulk", directores.stream().filter(d -> d.getNombre().equals("Louis Leterrier")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Edward Norton", "Liv Tyler", "Tim Roth").contains(a.getNombre())).collect(Collectors.toSet()), "2008-06-13", 3.9));
        peliculas.add(new Pelicula(3, "Iron Man 2", directores.stream().filter(d -> d.getNombre().equals("Jon Favreau")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Gwyneth Paltrow", "Don Cheadle").contains(a.getNombre())).collect(Collectors.toSet()), "2010-05-07", 4.5));
        peliculas.add(new Pelicula(4, "Thor", directores.stream().filter(d -> d.getNombre().equals("Kenneth Branagh")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Hemsworth", "Natalie Portman", "Tom Hiddleston").contains(a.getNombre())).collect(Collectors.toSet()), "2011-05-06", 4.3));
        peliculas.add(new Pelicula(5, "Captain America: The First Avenger", directores.stream().filter(d -> d.getNombre().equals("Joe Johnston")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Evans", "Hayley Atwell", "Sebastian Stan").contains(a.getNombre())).collect(Collectors.toSet()), "2011-07-22", 4.4));
        peliculas.add(new Pelicula(6, "The Avengers", directores.stream().filter(d -> d.getNombre().equals("Joss Whedon")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Chris Evans", "Mark Ruffalo").contains(a.getNombre())).collect(Collectors.toSet()), "2012-05-04", 4.7));
        peliculas.add(new Pelicula(7, "Iron Man 3", directores.stream().filter(d -> d.getNombre().equals("Shane Black")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Gwyneth Paltrow", "Don Cheadle").contains(a.getNombre())).collect(Collectors.toSet()), "2013-05-03", 4.3));
        peliculas.add(new Pelicula(8, "Thor: The Dark World", directores.stream().filter(d -> d.getNombre().equals("Alan Taylor")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Hemsworth", "Natalie Portman", "Tom Hiddleston").contains(a.getNombre())).collect(Collectors.toSet()), "2013-11-08", 3.9));
        peliculas.add(new Pelicula(9, "Captain America: The Winter Soldier", directores.stream().filter(d -> d.getNombre().equals("Anthony Russo")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Evans", "Scarlett Johansson", "Sebastian Stan").contains(a.getNombre())).collect(Collectors.toSet()), "2014-04-04", 4.6));
        peliculas.add(new Pelicula(10, "Guardians of the Galaxy", directores.stream().filter(d -> d.getNombre().equals("James Gunn")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Pratt", "Vin Diesel", "Bradley Cooper").contains(a.getNombre())).collect(Collectors.toSet()), "2014-08-01", 4.5));
        peliculas.add(new Pelicula(11, "Avengers: Age of Ultron", directores.stream().filter(d -> d.getNombre().equals("Joss Whedon")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Chris Evans", "Mark Ruffalo").contains(a.getNombre())).collect(Collectors.toSet()), "2015-05-01", 4.4));
        peliculas.add(new Pelicula(12, "Ant-Man", directores.stream().filter(d -> d.getNombre().equals("Peyton Reed")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Paul Rudd", "Michael Douglas", "Evangeline Lilly").contains(a.getNombre())).collect(Collectors.toSet()), "2015-07-17", 4.1));
        peliculas.add(new Pelicula(13, "Captain America: Civil War", directores.stream().filter(d -> d.getNombre().equals("Anthony Russo")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Evans", "Robert Downey Jr.", "Scarlett Johansson").contains(a.getNombre())).collect(Collectors.toSet()), "2016-05-06", 4.6));
        peliculas.add(new Pelicula(14, "Doctor Strange", directores.stream().filter(d -> d.getNombre().equals("Scott Derrickson")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Benedict Cumberbatch", "Chiwetel Ejiofor", "Rachel McAdams").contains(a.getNombre())).collect(Collectors.toSet()), "2016-11-04", 4.2));
        peliculas.add(new Pelicula(15, "Guardians of the Galaxy Vol. 2", directores.stream().filter(d -> d.getNombre().equals("James Gunn")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Pratt", "Zoe Saldana", "Dave Bautista").contains(a.getNombre())).collect(Collectors.toSet()), "2017-05-05", 4.2));
        peliculas.add(new Pelicula(16, "Spider-Man: Homecoming", directores.stream().filter(d -> d.getNombre().equals("Jon Watts")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Tom Holland", "Michael Keaton", "Robert Downey Jr.").contains(a.getNombre())).collect(Collectors.toSet()), "2017-07-07", 4.4));
        peliculas.add(new Pelicula(17, "Thor: Ragnarok", directores.stream().filter(d -> d.getNombre().equals("Taika Waititi")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Hemsworth", "Tom Hiddleston", "Cate Blanchett").contains(a.getNombre())).collect(Collectors.toSet()), "2017-11-03", 4.6));
        peliculas.add(new Pelicula(18, "Black Panther", directores.stream().filter(d -> d.getNombre().equals("Ryan Coogler")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chadwick Boseman", "Michael B. Jordan", "Lupita Nyong'o").contains(a.getNombre())).collect(Collectors.toSet()), "2018-02-16", 4.5));
        peliculas.add(new Pelicula(19, "Avengers: Infinity War", directores.stream().filter(d -> d.getNombre().equals("Anthony Russo")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Chris Hemsworth", "Mark Ruffalo").contains(a.getNombre())).collect(Collectors.toSet()), "2018-04-27", 4.7));
        peliculas.add(new Pelicula(20, "Ant-Man and the Wasp", directores.stream().filter(d -> d.getNombre().equals("Peyton Reed")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Paul Rudd", "Evangeline Lilly", "Michael Peña").contains(a.getNombre())).collect(Collectors.toSet()), "2018-07-06", 4.1));
        peliculas.add(new Pelicula(21, "Captain Marvel", directores.stream().filter(d -> d.getNombre().equals("Anna Boden")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Brie Larson", "Samuel L. Jackson", "Ben Mendelsohn").contains(a.getNombre())).collect(Collectors.toSet()), "2019-03-08", 4.0));
        peliculas.add(new Pelicula(22, "Avengers: Endgame", directores.stream().filter(d -> d.getNombre().equals("Anthony Russo")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Robert Downey Jr.", "Chris Evans", "Mark Ruffalo").contains(a.getNombre())).collect(Collectors.toSet()), "2019-04-26", 4.7));
        peliculas.add(new Pelicula(23, "Spider-Man: Far From Home", directores.stream().filter(d -> d.getNombre().equals("Jon Watts")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Tom Holland", "Samuel L. Jackson", "Jake Gyllenhaal").contains(a.getNombre())).collect(Collectors.toSet()), "2019-07-02", 4.5));
        peliculas.add(new Pelicula(24, "Black Widow", directores.stream().filter(d -> d.getNombre().equals("Cate Shortland")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Scarlett Johansson", "Florence Pugh", "David Harbour").contains(a.getNombre())).collect(Collectors.toSet()), "2021-07-09", 4.2));
        peliculas.add(new Pelicula(25, "Shang-Chi and the Legend of the Ten Rings", directores.stream().filter(d -> d.getNombre().equals("Destin Daniel Cretton")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Simu Liu", "Awkwafina", "Tony Chiu-Wai Leung").contains(a.getNombre())).collect(Collectors.toSet()), "2021-09-03", 4.3));
        peliculas.add(new Pelicula(26, "Eternals", directores.stream().filter(d -> d.getNombre().equals("Chloé Zhao")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Gemma Chan", "Richard Madden", "Angelina Jolie").contains(a.getNombre())).collect(Collectors.toSet()), "2021-11-05", 3.8));
        peliculas.add(new Pelicula(27, "Spider-Man: No Way Home", directores.stream().filter(d -> d.getNombre().equals("Jon Watts")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Tom Holland", "Zendaya", "Benedict Cumberbatch").contains(a.getNombre())).collect(Collectors.toSet()), "2021-12-17", 4.8));
        peliculas.add(new Pelicula(28, "Doctor Strange in the Multiverse of Madness", directores.stream().filter(d -> d.getNombre().equals("Sam Raimi")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Benedict Cumberbatch", "Elizabeth Olsen", "Chiwetel Ejiofor").contains(a.getNombre())).collect(Collectors.toSet()), "2022-05-06", 4.2));
        peliculas.add(new Pelicula(29, "Thor: Love and Thunder", directores.stream().filter(d -> d.getNombre().equals("Taika Waititi")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Hemsworth", "Natalie Portman", "Christian Bale").contains(a.getNombre())).collect(Collectors.toSet()), "2022-07-08", 4.0));
        peliculas.add(new Pelicula(30, "Black Panther: Wakanda Forever", directores.stream().filter(d -> d.getNombre().equals("Ryan Coogler")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Letitia Wright", "Lupita Nyong'o", "Danai Gurira").contains(a.getNombre())).collect(Collectors.toSet()), "2022-11-11", 4.1));
        peliculas.add(new Pelicula(31, "Ant-Man and the Wasp: Quantumania", directores.stream().filter(d -> d.getNombre().equals("Peyton Reed")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Paul Rudd", "Evangeline Lilly", "Jonathan Majors").contains(a.getNombre())).collect(Collectors.toSet()), "2023-02-17", 3.9));
        peliculas.add(new Pelicula(32, "Guardians of the Galaxy Vol. 3", directores.stream().filter(d -> d.getNombre().equals("James Gunn")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Chris Pratt", "Zoe Saldana", "Dave Bautista").contains(a.getNombre())).collect(Collectors.toSet()), "2023-05-05", 4.2));
        peliculas.add(new Pelicula(33, "The Marvels", directores.stream().filter(d -> d.getNombre().equals("Nia DaCosta")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Brie Larson", "Teyonah Parris", "Iman Vellani").contains(a.getNombre())).collect(Collectors.toSet()), "2023-11-10", 3.8));
        peliculas.add(new Pelicula(34, "Deadpool", directores.stream().filter(d -> d.getNombre().equals("Tim Miller")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Ryan Reynolds", "Morena Baccarin", "T.J. Miller").contains(a.getNombre())).collect(Collectors.toSet()), "2016-02-12", 4.5));
        peliculas.add(new Pelicula(35, "Logan", directores.stream().filter(d -> d.getNombre().equals("James Mangold")).findFirst().orElse(null), actores.stream().filter(a -> List.of("Hugh Jackman", "Patrick Stewart", "Dafne Keen").contains(a.getNombre())).collect(Collectors.toSet()), "2017-03-03", 4.7));
        peliculasRepository.saveAll(peliculas);
    }
    

    @Transactional(readOnly = true)
    public List<Pelicula> getPeliculas() throws Exception {
        List<Pelicula> peliculas = peliculasRepository.findAll();
        if (peliculas.isEmpty()) {
            throw new Exception("No hay peliculas disponibles");
        }
        return peliculas;
    }


    @Transactional(readOnly = true)
    public List<Pelicula> getPeliculasOrdenAlfabetico() throws Exception {
        List<Pelicula> peliculas = peliculasRepository.findAll();
        if (peliculas.isEmpty()) {
            throw new Exception("No hay peliculas disponibles");
        }
        peliculas.sort(Comparator.comparing(Pelicula::getNombre));
        return peliculas;
    }

    public Pelicula getPeliculaById(int id) throws Exception {
        return peliculasRepository.findById(id)
                .orElseThrow(() -> new Exception("Pelicula not found"));
    }

    public List<Pelicula> getPeliculaByNombre(String nombre) throws Exception{
        List<Pelicula> peliculas = peliculasRepository.findByNombreContainingIgnoreCase(nombre);
        if (peliculas.isEmpty()) {
            throw new Exception("No hay peliculas disponibles con ese nombre");
        }
        return peliculas;
    }

    public List<Pelicula> getPeliculaByActores(Actor actor) throws Exception {
        List<Pelicula> peliculas = peliculasRepository.findByActoresNombreContainingIgnoreCase(actor.getNombre());
        if (peliculas.isEmpty()) {
            throw new Exception("No hay películas disponibles con ese actor");
        }
        return peliculas;
    }

    public List<Pelicula> getPeliculasByRating(double rating) throws Exception{
        List<Pelicula> peliculas = peliculasRepository.findByRatingGreaterThanEqual(rating);
        if (peliculas.isEmpty()){
            throw new Exception("No hay un rating superior disponible");

        }
        return peliculas;
    }

    public List<Actor> getActores() throws Exception{
        List<Actor> actor = actorRepository.findAll();
        if (actor.isEmpty()) {
            throw new Exception("No hay peliculas disponibles");
        }
        return actor;
    }
    

    public Pelicula addPelicula(Pelicula nuevaPelicula) {
        return peliculasRepository.save(nuevaPelicula);
    }

    public Pelicula updatePelicula(int id, String nombre) throws Exception {
        Pelicula pelicula = getPeliculaById(id);
        pelicula.setNombre(nombre);
        return peliculasRepository.save(pelicula);
    }

    public boolean deletePelicula(int id) throws Exception {
        Pelicula pelicula = getPeliculaById(id);
        if (pelicula != null) {
            peliculasRepository.delete(pelicula);
            return true;
        }
        return false;
    }
}
