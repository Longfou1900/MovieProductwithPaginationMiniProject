//src/main/java/dto/MovieDetailResponse.java
package dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.ProductionCompany;

import java.util.List;

@Getter
@ToString
@Setter
public class MovieDetailResponse {
    private String id;
    private String title;
    private Double budget;
    private String release_date;
    private Double runtime;
    private Double vote_average;
    private String overview;
    private List<GenreResponse> genres;
    private List<String> origin_country;
    private List<ProductionCompany> production_companies;
    private List<CasterResponse> casterResponses;
}