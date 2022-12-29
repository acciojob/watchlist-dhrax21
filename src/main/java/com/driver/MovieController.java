package com.driver;

import com.sun.source.tree.LabeledStatementTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.saveMovies(movie);
        return new ResponseEntity<>("movie added Successfully",HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.saveDirector(director);
        return new ResponseEntity<>("director added successfully",HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie,@RequestParam String director){
        movieService.createMovieDirectorPair(movie,director);
        return new ResponseEntity<>("New movie Director pair added succesfully", HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie=movieService.findMovie(name);
        return new ResponseEntity<>(movie,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director=movieService.findDirector(name);

        return new ResponseEntity<>(director,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String >> getMoviesByDirectorName(@PathVariable String director){
        List<String> movieList=movieService.findMoviesFromDirector(director);
        return new ResponseEntity<>(movieList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String >> findAllMovies(){
        List<String> movieList=movieService.findAllMovies();
        return new ResponseEntity<>(movieList,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String>  deleteDirectorByName(@RequestParam String directorName){
        movieService.deleteDirector(directorName);
        return new ResponseEntity<>(directorName + "Successfully Removed",HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllMovies();
        return new ResponseEntity<>("All directors are Deleted Successfully",HttpStatus.MOVED_PERMANENTLY);
    }
}
