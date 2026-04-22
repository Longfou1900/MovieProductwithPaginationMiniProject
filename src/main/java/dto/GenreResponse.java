//src/main/java/dto/GenreResponse.java

package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import model.Genre;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreResponse {
    private List<Genre> genres;
}