package com.example.mrs.dto;

import com.example.mrs.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieDAO {
    static HashMap<Long, Movie> movieMap;
    static ObjectMapper mapper = new ObjectMapper();

    public MovieDAO() throws IOException{
        mapper.findAndRegisterModules();
        readJson();
    }

    public List<Movie> getAllMovies(){
        return new ArrayList<>(movieMap.values());
    }

    public List<Movie> getAllMoviesByCategory(String category){
        return this.getAllMovies()
                .stream()
                .filter(movie -> movie.getCategories().contains(category.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public Movie getMovieById(long id){
        return movieMap.get(id);
    }

    public Movie createMovie(Movie movie) throws IOException{
        movieMap.put(movie.getId(), movie);
        writeJson();
        return movieMap.get(movie.getId());
    }

    public Movie updateMovie(long id, Movie movie) throws IOException{
        if(movieMap.get(id) != null){
            //toDo write update
        }else{
            movieMap.put(id,movie);
        }
        writeJson();
        return movieMap.get(id);
    }

    public void deleteMovie(long id) throws IOException{
        movieMap.remove(id);
        writeJson();

    }

    private static void readJson() throws IOException{
        movieMap = new HashMap<>();
        File jsonFile = ResourceUtils.getFile("classpath:data/movies.json");
        List<Movie> movies = Arrays.asList(mapper.readValue(jsonFile,Movie[].class));
        movies.forEach(movie -> movieMap.put(movie.getId(), movie));
    }

    private static void writeJson( ) throws IOException{
        File jsonFile = ResourceUtils.getFile("classpath:data/movies.json");
        System.out.println(jsonFile.getPath());

        mapper.writeValue(jsonFile, movieMap);
    }



}
