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

    public Movie addNewMovie(Movie movie) {
        Optional<Movie> movieOptional =movieRepository.findMovieByName(movie.getName());
        movieOptional.ifPresentOrElse(s->{
            throw new IllegalStateException(movie.getName()+" already inserted");
        },()->{
            System.out.println("Inserting new movie... "+movie.getName());
            movieRepository.insert(movie);
        });
        return movie;
    }

    public void updateMovie(String id,
//                            String name,
//                            Long releaseYear
                            Movie movie
    ) {
        Movie movieFromRepo = movieRepository.findMovieById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "Movie with id "+id+" does not exist"
                ));
//        if(name !=null && name.length()>0 && !Objects.equals(movieFromRepo.getName(), name)){
//            movieFromRepo.setName(name);
//        }
//        if(releaseYear !=null && releaseYear>0){
//            movieFromRepo.setReleaseYear(releaseYear);
//        }

        movie.setId(movieFromRepo.getId());
        movieRepository.insert(movie);
    }

    public void removeMovie(String movieId) {
        Optional<Movie> movie = movieRepository.findMovieById(movieId);
        movieRepository.deleteById(movieId);
    }
}
