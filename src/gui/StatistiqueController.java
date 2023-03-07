package gui;
import entities.Rating;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.AnchorPane;
import services.RatingService;
import java.sql.SQLException;
import java.util.List;
public class StatistiqueController {

    @FXML
    private AnchorPane root;

    @FXML
    private BarChart<String, Number> chart;

    private RatingService ratingService;


    public StatistiqueController() {
        ratingService = new RatingService();
    }


    @FXML
    private void initialize() throws SQLException {
        CategoryAxis xAxis = (CategoryAxis) chart.getXAxis();
        xAxis.setLabel("Product ID");

        NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        yAxis.setLabel("Rating");

        List<Rating> ratings = ratingService.getAll();
        Series<String, Number> series = new Series<>();
        series.setName("Rating");
        for (Rating rating : ratings) {
            series.getData().add(new XYChart.Data<>(String.valueOf(rating.getId_produit()), rating.getRating()));
        }
        chart.getData().add(series);
    }
}
