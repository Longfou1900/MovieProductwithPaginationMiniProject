package services;

import model.Product;
import model.ProductResponse;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.net.http.HttpRequest.newBuilder;

public class ProductServiceImpl implements ProductService{

    private static List<Product> products = new ArrayList<>();
    private static final HttpClient client =
            HttpClient.newHttpClient().connectTimeout(
            Duration.ofSeconds(5)).build();

    @Override
    public List<Product> getAll() {
        initDate();
        return products;
    }
//    @Override
//    public ProductResponse getDummyProducts() {
//
//        return newBuilder().build();
//    }

    private void initDate() {
        Random ramdom = new Random();
        for (int i=1; i<=40; i++){
            Product product = new Product();
            product.setId(ramdom.nextInt(9999));
            product.setTitle("Pro"+ ramdom.nextInt(99));
            product.setCategory("Cat"+ ramdom.nextInt(99));
            product.setPrice(
                    Double.valueOf(
                            String.format("%2.f", ramdom.nextDouble(99.99))
                    )
            );
            product.setStock(ramdom.nextInt(100));
            products.add(product);
        }
    }

}
