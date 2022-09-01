package com.learning.movieapp.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> fetchAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/{movieId}")
    public Optional<Movie> getMovie(@PathVariable("movieId") String movieId){
        return movieService.getMovieById(movieId);
    }
}
