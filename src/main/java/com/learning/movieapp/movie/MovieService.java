package com.learning.movieapp.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findMovieById(id);
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional =movieRepository.findMovieByName(movie.getName());
        movieOptional.ifPresentOrElse(s->{
            throw new IllegalStateException(movie.getName()+" already inserted");
        },()->{
            System.out.println("Inserting new movie... "+movie.getName());
            movieRepository.insert(movie);
        });
    }
}
