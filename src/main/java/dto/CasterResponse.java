package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CasterResponse {

    private String id;
    private List<Cast> cast;

}