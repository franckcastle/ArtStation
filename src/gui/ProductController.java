package gui;

import entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.net.URL;
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

    public void setProduct(Produit produit) {
        this.produit = produit;
        updateUI();
    }

    private void updateUI() {
        if (produit != null) {
            productName.setText(produit.getNom());
            productPrice.setText(String.valueOf(produit.getPrix()));

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
}

