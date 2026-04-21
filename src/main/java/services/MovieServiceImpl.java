package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MovieDetailResponse;
import dto.MovieResponse;
import model.Movie;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.net.http.HttpRequest.newBuilder;

public class MovieServiceImpl implements MovieService {

    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YzQzNGY2YzcwMWFlODZkMzY1OWM5OGQ5MzI1ZjM5YSIsIm5iZiI6MTczMzMwOTIwMi44MTY0MDA1LCJzdWIiOiI2NzMzMGMzYTAwZDJkOGY2OWY3MzFmYTEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wzHSvqZT384z00cpE2wM6_ilvg049VRwZKW4bjKb_VE";

    private static final HttpClient client =
            HttpClient.newBuilder().connectTimeout(
                    Duration.ofSeconds(10)).build(); //error (Duration.ofSeconds(10))

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Movie> getAll() {
        try {
            String url = TMDB_API_BASE_URL + "/movie/popular?page=1";
            String responseBody = makeRequest(url);
            MovieResponse response = objectMapper.readValue(responseBody, MovieResponse.class);
            return response.getResults() != null ? response.getResults() : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public MovieResponse getMovies(int skip, int limit) {
        try {
            int pageSize = limit;
            int page = (skip / pageSize) + 1;

            String url = TMDB_API_BASE_URL + "/movie/popular?page=" + page;
            String responseBody = makeRequest(url);
            MovieResponse response = objectMapper.readValue(responseBody, MovieResponse.class);
            return response;
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            return null;
        }
    }

    private static String makeRequest(String urlString) throws Exception {
        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(urlString))
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("API Error: Status Code " + response.statusCode() +
                    " - " + response.body());
        }

        return response.body();
    }

    private static String encodeUrl(String url) {
        return url.replace(" ", "%20")
                .replace("&", "%26")
                .replace("?", "%3F")
                .replace("#", "%23");
    }

    @Override
    public MovieResponse searchMovies(String query, int page) {
        try {
            String encodedQuery = encodeUrl(query);
            String url = TMDB_API_BASE_URL + "/search/movie?query=" + encodedQuery + "&page=" + page;

            String responseBody = makeRequest(url);
            MovieResponse response = objectMapper.readValue(responseBody, MovieResponse.class);

            return response;
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public MovieDetailResponse getMovieDetails(int movieId) {
        return null;
    }
}