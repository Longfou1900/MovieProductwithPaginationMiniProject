package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientUtil {

    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YzQzNGY2YzcwMWFlODZkMzY1OWM1OGQ5MzI1ZjM5YSIsIm5iZiI6MTczMzMwOTIwMi44MTY0MDA1LCJzdWIiOiI2NzMzMGMzYTAwZDJkOGY2OWY3MzFmYTEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wzHSvqZT384z00cpE2wM6_ilvg049VRwZKW4bjKb_VE";

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String get(String endpoint, String queryParams) throws Exception {
        String fullUrl = TMDB_API_BASE_URL + endpoint;
        if (queryParams != null && !queryParams.isEmpty()) {
            fullUrl += "?" + queryParams;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullUrl))
                .header("Authorization", "Bearer " + BEARER_TOKEN)
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