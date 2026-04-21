//src/main/java/dto/GenreResponse.java

package dto;

import lombok.Getter;
import lombok.Setter;
import model.Genre;

import java.util.List;

@Setter
@Getter
public class GenreResponse {
    private List<Genre> genres;
}