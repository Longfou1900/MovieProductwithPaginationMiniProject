package utils;

import dto.MovieResponse;
import model.Movie;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableRender {

    // Handle MovieResponse (from API)
    public static void displayMovieTable(MovieResponse response) {
        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            System.out.println("[!] No movies found.");
            return;
        }

        Table table = new Table(
                8,
                BorderStyle.UNICODE_BOX_WIDE,
                ShownBorders.ALL
        );

        String[] columns = {
                "ID", "Title", "Release Date",
                "Rating", "Popularity",
                "Category", "Price", "Stock"
        };

        for (String column : columns){
            table.addCell(column);
        }

        List<Movie> movies = response.getResults();

        for (Movie movie : movies){
            table.addCell(movie.getId().toString());
            table.addCell(movie.getTitle() != null ? movie.getTitle() : "N/A");
            table.addCell(movie.getRelease_date() != null ? movie.getRelease_date() : "N/A");
            table.addCell(movie.getVote_average() != null ? movie.getVote_average().toString() : "N/A");
            table.addCell(movie.getPopularity() != null ? movie.getPopularity().toString() : "N/A");
            table.addCell(movie.getCategory() != null ? movie.getCategory() : "Movie");
            table.addCell(movie.getPrice() != null ? "$" + movie.getPrice().toString() : "$5.99");
            table.addCell(movie.getStock() != null ? movie.getStock().toString() : "10");
        }

        System.out.println(table.render());
    }

    // Handle List<Movie> (from local data)
    public static void displayMovieTable(List<Movie> movies, int skip, int limit){

        Table table = new Table(
                8,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                ShownBorders.ALL
        );

        String[] columns = {
                "ID", "Title", "Release Date",
                "Rating", "Popularity",
                "Category", "Price", "Stock"
        };

        for (String column : columns){
            table.addCell(column);
        }

        List<Movie> partialData = movies
                .stream().skip(skip).limit(limit).toList();

        for (Movie movie : partialData){
            table.addCell(movie.getId().toString());
            table.addCell(movie.getTitle());
            table.addCell(movie.getRelease_date() != null ? movie.getRelease_date() : "N/A");
            table.addCell(movie.getVote_average() != null ? movie.getVote_average().toString() : "N/A");
            table.addCell(movie.getPopularity() != null ? movie.getPopularity().toString() : "N/A");
            table.addCell(movie.getCategory() != null ? movie.getCategory() : "Movie");
            table.addCell(movie.getPrice() != null ? "$" + movie.getPrice().toString() : "$5.99");
            table.addCell(movie.getStock() != null ? movie.getStock().toString() : "10");
        }

        System.out.println(table.render());
    }

    public static void displayMovieDetail(String title, String releaseDate,
                                          String rating, String runtime,
                                          String budget, String genres,
                                          String origin,
                                          String category, String price, String stock) {

        Table table = new Table(
                2,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                ShownBorders.ALL
        );

        table.addCell("Title");
        table.addCell(title);

        table.addCell("Release Date");
        table.addCell(releaseDate);

        table.addCell("Rating");
        table.addCell(rating);

        table.addCell("Runtime");
        table.addCell(runtime);

        table.addCell("Budget");
        table.addCell(budget);

        table.addCell("Genres");
        table.addCell(genres);

        table.addCell("Origin");
        table.addCell(origin);

        table.addCell("Category");
        table.addCell(category);

        table.addCell("Price");
        table.addCell(price);

        table.addCell("Stock");
        table.addCell(stock);

        System.out.println(table.render());
    }
}