package dto;

import model.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieResponse {
    private Integer page;
    private List<Movie> results;
    private Integer total_pages;
    private Integer total_results;
}
 