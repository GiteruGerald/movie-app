package com.learning.movieapp.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data

@Document("movies")
public class Movie {
//TODO: Why ID only works for String data type, not(Long, int)
    @Id
    private String id;
    private String name;
    private String type;
    private String rating;
    private Long releaseYear;

    public Movie(String name, String type, String rating, Long releaseYear) {

        this.name = name;
        this.type = type;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }
}
