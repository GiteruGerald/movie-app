package com.learning.movieapp.movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{movieId}")
    public Optional<Movie> getMovie(@PathVariable("movieId") String movieId){
        return movieService.getMovieById(movieId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movie registerNewMovie(@RequestBody Movie movie){
        return movieService.addNewMovie(movie);
    }

//    TODO: fix update resource
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{movieId}")
    public void updateMovie(@PathVariable String movieId,
//                            @RequestParam String name,
//                            @RequestParam Long releaseYear
                            @RequestBody Movie movie
    ){
        movieService.updateMovie(movieId, movie);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable String movieId){
        movieService.removeMovie(movieId);
    }


}
