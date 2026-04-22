//src/main/java/services/MovieService.java
package services;

import dto.MovieDetailResponse;
import model.Movie;
import dto.MovieResponse;
import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    MovieResponse getMovies(int skip, int limit);

    MovieResponse searchMovies(String currentQuery, int pageNumber)throws Exception;

    MovieDetailResponse getMovieDetails(int movieId);
}