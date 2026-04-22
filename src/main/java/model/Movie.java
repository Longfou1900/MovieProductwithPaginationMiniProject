package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private Integer id;
    private String title;
    private String release_date;
    private Double vote_average;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private Double popularity;
    private String category;
    private Double price;
    private Integer stock;

}