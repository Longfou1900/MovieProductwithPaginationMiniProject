//src/main/java/dto/TrailerResponse.java
package dto;
import lombok.Getter;
import lombok.Setter;
import model.Trailer;

import java.util.List;

@Setter
@Getter
public class TrailerResponse {
    List<Trailer> results;
}