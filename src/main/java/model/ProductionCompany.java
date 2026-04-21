package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductionCompany {
    private Integer id;
    private String name;
    private String logo_path;
    private String origin_country;
}