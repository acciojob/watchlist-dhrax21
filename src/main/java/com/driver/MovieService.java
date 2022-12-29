package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void saveMovies(Movie movie){
        movieRepository.add(movie);
    }
    public void saveDirector(Director director){

        movieRepository.addDirector(director);
    }
    public void createMovieDirectorPair(String movie,String director){
        movieRepository.saveMovieDirectorPair(movie,director);
    }

    public Movie findMovie(String movieName){

        return movieRepository.findMovie(movieName);
    }

    public Director findDirector(String directorName){

        return movieRepository.findDirector(directorName);
    }

    public List<String> findMoviesFromDirector(String directorName){
        return movieRepository.findMoviesFromDirector(directorName);
    }
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public void deleteDirector(String directorName){
        movieRepository.deleteDirector(directorName);
    }
    public void deleteAllMovies(){
        movieRepository.deleteAllMovies();
    }
}

