package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cast {
    private Integer id;
    private String name;
    private String character;
    private String profile_path;
    private Integer order;
}