package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientUtil {

    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";
//    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMmZiZTgxMzNkZTNiYmQxN2VmYWUxNGI4MTk0MTg2OCIsIm5iZiI6MTc3Njc3NTk5MS40MTYsInN1YiI6IjY5ZTc3MzM3OGQ4YThhYTMzNTE5NjA4NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tLLPYpuKv06h79OZRgNjG2ehMeH8bQsZWtC704IGxOY";
    private static final String API_KEY = "32fbe8133de3bbd17efae14b81941868";

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String get(String endpoint, String queryParams) throws Exception {
        String fullUrl = TMDB_API_BASE_URL + endpoint;

        // First parameter gets the '?'
        fullUrl += "?api_key=" + API_KEY;

        // Any additional parameters get an '&'
        if (queryParams != null && !queryParams.isEmpty()) {
            fullUrl += "&" + queryParams;
        }

        // Optional: Print URL console to verify correct
        System.out.println("[DEBUG] Calling URL: " + fullUrl);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullUrl))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("API Error: " + response.statusCode() + " - " + response.body());
        }

        return response.body();
    }

    public static <T> T parseResponse(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }
}