//src/main/java/model/Product.java
package model;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private Integer id;
    private String title;
    private String category;
    private Double price;
    private Integer stock;
}
