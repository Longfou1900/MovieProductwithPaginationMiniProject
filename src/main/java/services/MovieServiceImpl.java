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
    private static final String API_KEY = "32fbe8133de3bbd17efae14b81941868";

    private static final HttpClient client =
            HttpClient.newBuilder().connectTimeout(
                    Duration.ofSeconds(10)).build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Movie> getAll() {
        try {
            String url = TMDB_API_BASE_URL + "/movie/popular?api_key=" + API_KEY + "&page=1";
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

            //  ADD API_KEY HERE
            String url = TMDB_API_BASE_URL + "/movie/popular?api_key=" + API_KEY + "&page=" + page;
            String responseBody = makeRequest(url);
            MovieResponse response = objectMapper.readValue(responseBody, MovieResponse.class);
            return response;
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public MovieResponse searchMovies(String query, int page) throws Exception {
        try {
            String encodedQuery = encodeUrl(query);
            //  ADD API_KEY HERE - MOST IMPORTANT FIX!
            String url = TMDB_API_BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + encodedQuery + "&page=" + page;

            String responseBody = makeRequest(url);
            MovieResponse response = objectMapper.readValue(responseBody, MovieResponse.class);

            return response;
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public MovieDetailResponse getMovieDetails(int movieId) throws Exception {
        try {
            //  ADD API_KEY HERE
            String url = TMDB_API_BASE_URL + "/movie/" + movieId + "?api_key=" + API_KEY;
            String responseBody = makeRequest(url);
            MovieDetailResponse response = objectMapper.readValue(responseBody, MovieDetailResponse.class);
            return response;
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            throw e;
        }
    }

    private static String makeRequest(String urlString) throws Exception {
        System.out.println("[DEBUG] Calling URL: " + urlString);

        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(urlString))
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
}