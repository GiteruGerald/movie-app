package com.learning.movieapp.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{movieId}")
    public Optional<Movie> getMovie(@PathVariable("movieId") String movieId){
        return movieService.getMovieById(movieId);
    }

    @PostMapping
    public void registerNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
    }
}
