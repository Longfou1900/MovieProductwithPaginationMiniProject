
import dto.MovieResponse;
import dto.MovieDetailResponse;
import services.MovieService;
import services.MovieServiceImpl;
import utils.TableRender;
import java.util.Scanner;

public class App {

    private static final MovieService service = new MovieServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    private static int pageNumber = 1;
    private static int totalPages = 1;
    private static String currentQuery = "";
    private static MovieResponse currentMovieList;

    private static void updatePageNumber(int pageNum) {
        if (pageNum < 1) {
            pageNumber = 1;
            return;
        }
        if (pageNum > totalPages) {
            pageNumber = totalPages;
            return;
        }
        pageNumber = pageNum;
    }

    private static void searchMovies(String query) {
        try {
            System.out.println("[+] Searching for: " + query);
            currentQuery = query;
            pageNumber = 1;
            currentMovieList = service.searchMovies(query, pageNumber);

            if (currentMovieList == null || currentMovieList.getResults() == null ||
                    currentMovieList.getResults().isEmpty()) {
                System.out.println("[!] No movies found.");
                currentMovieList = null;
                totalPages = 1;
            } else {
                totalPages = currentMovieList.getTotal_pages();
                displayCurrentPage();
            }
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
            currentMovieList = null;
            totalPages = 1;
        }
    }

    private static void displayCurrentPage() {
        if (currentMovieList != null && currentMovieList.getResults() != null) {
            TableRender.displayMovieTable(currentMovieList);
            System.out.printf("Page %d / %d (Total Results: %d)%n",
                    pageNumber, totalPages, currentMovieList.getTotal_results());
        }
    }

    private static void loadPage(int pageNum) {
        try {
            updatePageNumber(pageNum);
            currentMovieList = service.searchMovies(currentQuery, pageNumber);
            displayCurrentPage();
        } catch (Exception e) {
            System.out.println("[!] Error loading page: " + e.getMessage());
        }
    }

    private static void showMovieDetail(int movieId) {
        try {
            System.out.println("[+] Loading movie details...");
            MovieDetailResponse detail = service.getMovieDetails(movieId);

            if (detail == null) {
                System.out.println("[!] Movie not found.");
                return;
            }

            String genres = detail.getGenres() != null && !detail.getGenres().isEmpty()
                    ? "Drama, Crime, Action" : "N/A";

            String origin = detail.getOrigin_country() != null && !detail.getOrigin_country().isEmpty()
                    ? String.join(", ", detail.getOrigin_country()) : "N/A";

            String category = "Movie"; // or from genres
            String price = "$5.99";
            String stock = "10";

            TableRender.displayMovieDetail(
                    detail.getTitle(),
                    detail.getRelease_date() != null ? detail.getRelease_date() : "N/A",
                    detail.getVote_average() != null ? detail.getVote_average() + " / 10" : "N/A",
                    detail.getRuntime() != null ? detail.getRuntime() + " min" : "N/A",
                    detail.getBudget() != null && detail.getBudget() > 0
                            ? "$" + String.format("%,d", detail.getBudget().longValue()) : "N/A",
                    genres,
                    origin,
                    category,   
                    price,      
                    stock       
            );

        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    static void main() {
        System.out.println("""
                   ███╗   ███╗ ██████╗ ██╗   ██╗███████╗    ███████╗███████╗ █████╗ ██████╗  ██████╗██╗  ██╗
                   ████╗ ████║██╔═══██╗██║   ██║██╔════╝    ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝██║  ██║
                   ██╔████╔██║██║   ██║██║   ██║█████╗      ███████╗█████╗  ███████║██████╔╝██║     ███████║
                   ██║╚██╔╝██║██║   ██║╚██╗ ██╔╝██╔══╝      ╚════██║██╔══╝  ██╔══██║██╔══██╗██║     ██╔══██║
                   ██║ ╚═╝ ██║╚██████╔╝ ╚████╔╝ ███████╗    ███████║███████╗██║  ██║██║  ██║╚██████╗██║  ██║
                   ╚═╝     ╚═╝ ╚═════╝   ╚═══╝  ╚══════╝    ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝
                """);

        // Initial search
        System.out.print("[-] Enter movie title: ");
        String query = scanner.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("[!] Please enter a movie title.");
            return;
        }

        searchMovies(query);

        if (currentMovieList == null) {
            return;
        }

        // Main loop
        while (true) {
            System.out.println("""
                    [n] Next page
                    [p] Previous page
                    [gt] Go to page
                    [md] Movie detail
                    [s] New search
                    [e] Exit
                    """);
            System.out.print("[-] Choose an option: ");
            String option = scanner.nextLine().toLowerCase().trim();

            switch (option) {
                case "n" -> {
                    if (pageNumber < totalPages) {
                        loadPage(pageNumber + 1);
                    } else {
                        System.out.println("[!] Already on the last page.");
                    }
                }
                case "p" -> {
                    if (pageNumber > 1) {
                        loadPage(pageNumber - 1);
                    } else {
                        System.out.println("[!] Already on the first page.");
                    }
                }
                case "gt" -> {
                    System.out.print("[-] Enter page number (1-" + totalPages + "): ");
                    try {
                        int pageNum = Integer.parseInt(scanner.nextLine().trim());
                        loadPage(pageNum);
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Invalid page number.");
                    }
                }
                case "md" -> {
                    System.out.print("[-] Enter movie id: ");
                    try {
                        int movieId = Integer.parseInt(scanner.nextLine().trim());
                        showMovieDetail(movieId);
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Invalid movie id.");
                    }
                }
                case "s" -> {
                    System.out.print("[-] Enter movie title: ");
                    String newQuery = scanner.nextLine().trim();
                    if (!newQuery.isEmpty()) {
                        searchMovies(newQuery);
                    } else {
                        System.out.println("[!] Please enter a movie title.");
                    }
                }
                case "e" -> {
                    System.out.println("[+] Thank you! Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("[!] Invalid option.");
            }
        }
    }
}