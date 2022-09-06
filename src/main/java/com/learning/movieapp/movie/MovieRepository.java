package com.learning.movieapp.movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MovieRepository extends MongoRepository<Movie,String> {
    Optional<Movie> findMovieById(String id);

    Optional<Movie> findMovieByName(String name);
}
