package com.learning.movieapp;

import com.learning.movieapp.movie.Movie;
import com.learning.movieapp.movie.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@SpringBootApplication
public class MovieAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, MongoTemplate mongoTemplate){
        return args -> {
            Movie movie = new Movie(
                    "Tokyo Drift",
                    "Action",
                    "PG-13",
                    2016L
            );

//            usingMongoTempAndQueries(movieRepository, mongoTemplate, movie);

//            Cleaner code
////            movieRepository.findMovieByName(movie.getName())
////                    .ifPresentOrElse(s->{
////                        throw new IllegalStateException("Movie already inserted " + movie.getName());
////                    },()->{
////                        System.out.println("Inserting... "+movie.getName());
////                        movieRepository.insert(movie);
////                        System.out.println(movie.getName()+" Added   ");
////
//                    });
        };
    }

    private void usingMongoTempAndQueries(MovieRepository movieRepository, MongoTemplate mongoTemplate, Movie movie) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(movie.getName()));

        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        if(movies.size() > 1){
            throw  new IllegalStateException("Movie already inserted " + movie.getName());
        }
        if(movies.isEmpty()){
            System.out.println("Inserting movie - " + movie);
            movieRepository.insert(movie);
        }else {
            throw  new IllegalStateException(movie.getName() + " already exists");
        }
    }
}
