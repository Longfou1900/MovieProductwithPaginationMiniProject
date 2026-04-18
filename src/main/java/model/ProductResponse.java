package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {

    private List<Product> products;
    private Integer total;
    private Integer skip;
    private Integer limit;
}
