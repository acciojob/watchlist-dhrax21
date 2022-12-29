package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Component
public class MovieRepository {

    private Map<String,Movie> movies=new HashMap<>();
    private Map<String,Director> directors=new HashMap<>();
    private Map<String, List<String>> directorMoviesMapping=new HashMap<>();


    public void add(Movie movie){
        movies.put(movie.getName(),movie);
    }


    public void addDirector(Director director) {
        directors.put(director.getName(),director);
    }

    public void saveMovieDirectorPair(String movie, String director) {
//        if(movies.containsKey(movie) && directors.containsKey(director)){
//            movies.put(movie,movies.get(movie));
//            directors.put(director,directors.get(director));
//
//            List<String> currMovies=new ArrayList<String>();
//            if(directorMoviesMapping.containsKey(director))
//                currMovies = directorMoviesMapping.get(director);
//
//            currMovies.add(movie);
//            directorMoviesMapping.put(director, currMovies);

            if(movies.containsKey(movie) && directors.containsKey(director)){
                List<String> movieList=new ArrayList<>();
                movieList=directorMoviesMapping.get(director);
                movieList.add(movie);
                directorMoviesMapping.put(director,movieList);
        }
    }

    public Movie findMovie(String movieName) {
        return movies.get(movieName);
    }

    public Director findDirector(String directorName) {
        return directors.get(directorName);
    }

    public List<String> findMoviesFromDirector(String directorName) {
        List<String> moviesList=new ArrayList<String>();
        if(directorMoviesMapping.containsKey(directorName)){
            moviesList=directorMoviesMapping.get(directorName);
        }
        return moviesList;
    }

    public List<String> findAllMovies() {

        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirector(String directorName) {
        List<String> moviesSet = new ArrayList<>();
        if(directorMoviesMapping.containsKey(directorName)){
            moviesSet = directorMoviesMapping.get(directorName);
            for(String movie : moviesSet){
                if(movies.containsKey(movie)){
                    movies.remove(movie);
                }
            }
            directorMoviesMapping.remove(directorName);
        }
        if(directors.containsKey(directorName)){
            directors.remove(directorName);
        }
    }

    public void deleteAllMovies() {
        Set<String> movieSet=new HashSet<String>();

        for(String director : directorMoviesMapping.keySet()){
            for(String movie : directorMoviesMapping.keySet()){
                movieSet.add(movie);
            }
        }
        for(String movie : movieSet){
            if(movies.containsKey(movie)){
                movies.remove(movie);
            }
        }
    }
}
