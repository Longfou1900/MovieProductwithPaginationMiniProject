package services;

import model.Product;
import model.ProductResponse;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    ProductResponse getDummyProducts(int skip, int limit);

}
