package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trailer {
    private String id;
    private String name;
    private String key;
    private String site;
    private String type;
    private Boolean official;
}
