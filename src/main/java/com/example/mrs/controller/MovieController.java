package com.example.mrs.controller;


import com.example.mrs.model.Movie;
import com.example.mrs.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> read(@PathVariable Long id){
        Movie foundMovie = movieService.getMoviesById(id);
        if(foundMovie == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(foundMovie);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Movie> create(@RequestBody Movie movie) throws IOException{
        Movie createdMovie = movieService.createMovie(movie);
        if(createdMovie == null){
            return ResponseEntity.notFound().build();
        }else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(createdMovie.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(createdMovie);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@RequestBody Movie movie, @PathVariable Long id) throws IOException{
        Movie updatedMovie = movieService.updateMovie(id, movie);
        if(updatedMovie == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(updatedMovie);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Long id) throws  IOException{
        movieService.deleteMovie(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> movieList(@RequestParam(value = "category", defaultValue = "all") String category){
        List<Movie> movies = category.equals("all") ? movieService.getAllMovies() : movieService.getMoviesByCategory(category);
        return ResponseEntity.ok(movies);
    }
}
