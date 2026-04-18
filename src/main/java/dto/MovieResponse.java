package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MovieResponse{
    Integer page;
    List<Movie> results;
    Integer total_pages;
    Integer total_results;
}