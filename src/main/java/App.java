import model.Product;
import services.ProductService;
import services.ProductServiceImpl;
import utils.TableRender;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final ProductService service = new ProductServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    private static int pageSize = 5;
    private static int pageNumber = 1;
    private static int skip = (pageNumber - 1) * pageSize;
    private static int totalpages = (
            ( service.getAll().size() + pageSize - 1) / pageSize
    );
    private static void updatepageNumber(int pageNum){
        if (pageNum < 1){
            pageNumber = 1;
            return;
        }
        if (pageNum > totalpages){
            pageNumber = totalpages;
            return;
        }
        pageNumber = pageNum;
        skip = (pageNumber - 1) * pageSize;
    }

    static void main() {
        List<Product> products = service.getAll();

        TableRender.displayTabbleProduct(products, skip, pageSize);
        System.out.printf("Page %d / %d \n", pageNumber, totalpages);
        while (true){
            System.out.println("""
                    [n] Next page
                    [p] Previous page
                    [gt] Go to page
                    [e] Exit
                    """);
            System.out.println("Choose an option: ");
            String op = scanner.next();

            switch (op.toLowerCase()){
                case "n" -> {updatepageNumber(pageNumber + 1);}
                case "p" -> {updatepageNumber(pageNumber - 1);}
                case "gt" -> {
                    System.out.print("[!] Enter page number: ");
                    int page = scanner.nextInt();
                    updatepageNumber(page);
                }
                case "e" -> System.exit(0);
            }
        }

    }
}
