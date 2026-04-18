package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GenreResponse {
    private List<GenreResponse> genres;
}