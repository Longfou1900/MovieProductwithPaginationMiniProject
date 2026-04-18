package utils;

import model.Product;
import model.ProductResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

public class TableRender {
    public ProductResponse getDummyProducts(int skip, int limit){
        String url =
                String.format("https://dummyjson.com/products?skip=30&limit=10",skip, limit);

        HttpResponse
    };

    public static void displayTabbleProduct(List<Product> products,
                                            int skip, int limit){

        Table table = new Table(
                5,
                BorderStyle.CLASSIC,
                ShownBorders.ALL
        );

        String[] conlumns = {"ID" , "Tittle" , "Category", "Price", "Stock"};
        for (String coString : conlumns){
            table.addCell(coString);
        }

        List<Product> partialData = products
                .stream().skip(skip).limit(limit).toList();

        for (Product product : partialData){
            table.addCell(product.getId().toString());
            table.addCell(product.getTitle());
            table.addCell(product.getCategory());
            table.addCell(product.getPrice().toString());
            table.addCell(product.getStock().toString());
        }

        System.out.println(table.render());
    }



}
