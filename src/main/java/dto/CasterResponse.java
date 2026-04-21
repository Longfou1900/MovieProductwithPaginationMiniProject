//src/main/java/dto/CasterResponse.java

package dto;

import lombok.Getter;
import lombok.Setter;
import model.Cast;

import java.util.List;

@Setter
@Getter
public class CasterResponse {

    private String id;
    private List<Cast> cast;

}