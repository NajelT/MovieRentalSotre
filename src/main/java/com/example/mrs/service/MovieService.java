package com.example.mrs.service;

import com.example.mrs.dto.MovieDAO;
import com.example.mrs.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {

    private final MovieDAO movieDAO;

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getAllMovies(){
        return movieDAO.getAllMovies();
    }

    public List<Movie> getMoviesByCategory(String category){
        return movieDAO.getAllMoviesByCategory(category);
    }

    public Movie getMoviesById(long id){
        return movieDAO.getMovieById(id);
    }

    public Movie createMovie(Movie user) throws IOException{
        return movieDAO.createMovie(user);
    }

    public Movie updateMovie(long id, Movie user) throws IOException{
        return movieDAO.updateMovie(id, user);
    }

    public void deleteMovie(long id) throws IOException{
        movieDAO.deleteMovie(id);
    }
}
