package dto;

import co.istad.y2.model.Trailer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TrailerResponse {
    List<Trailer> results;
}