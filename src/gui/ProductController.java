package gui;

import entities.Produit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import services.RatingService;
import javafx.scene.control.ButtonType;



import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    private Produit produit;
    @FXML
    private VBox productContainer;
    @FXML
    private ImageView productImage;
    @FXML
    private Label productName;
    @FXML
    private Label productPrice;
    @FXML
    public Button rate;

    public void setProduct(Produit produit) {
        this.produit = produit;
        updateUI();
    }

    private void updateUI() {
        if (produit != null) {
            productName.setText(produit.getNom());
            productPrice.setText(String.format("%.0f DT", produit.getPrix()));

            String img = produit.getImage();
            String ch = "../img/";
            String imgF = ch + img;

            // Use getClass().getResource() to get the correct URL for the image
            URL imageURL = getClass().getResource(imgF);
            if (imageURL != null) {
                Image imageF = new Image(imageURL.toString());
                productImage.setImage(imageF);
            } else {
                System.err.println("Resource not found: " + imgF);
            }
        } else {
            productName.setText("");
            productPrice.setText("");
            productImage.setImage(null);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUI();
    }


    public void rateProdcut(javafx.event.ActionEvent actionEvent) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Rate Product");

        // Create the rating control
        Rating rating = new Rating();
        rating.setRating(0);

        // Add the rating control to the dialog
        dialog.getDialogPane().setContent(rating);

        // Add the OK button to the dialog
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        // Show the dialog and wait for user response
        Optional<Integer> result = dialog.showAndWait();

        // If the user clicked OK, save the rating value to the database

        int i =(int)rating.getRating();
        //System.out.println(rating.getRating());
        if (result.isPresent() ) {

                // Get the selected product
                Produit produit = this.produit;

                // Call the rateProduit method to store the rating value in the database
                RatingService rs = new RatingService();
               rs.rateProduit(produit.getID_produit(), i);

            }
        }
    }



