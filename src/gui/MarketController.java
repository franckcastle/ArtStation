package gui;

import entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MarketController implements Initializable {

    ProduitService ps = new ProduitService();

    @FXML
    public ScrollPane scroll;
    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateView(newValue);
        });
        if (productsFlowPane != null) {

            try {
                List<Produit> listProd = null;
                try {
                    listProd = ps.getAll();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                for (Produit produit : listProd) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/product.fxml"));
                    Parent parent = fxmlLoader.load();
                    ProductController productController = fxmlLoader.getController();
                    productController.setProduct(produit);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    productsFlowPane.getChildren().add(node);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }

    private void updateView(String searchQuery) {
        if (productsFlowPane != null) {
            try {
                List<Produit> listProd = null;
                try {
                    listProd = ps.getAll();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                productsFlowPane.getChildren().clear(); // clear the current products

                // filter the list of products based on the search query
                List<Produit> filteredProducts = listProd.stream()
                        .filter(p -> p.getNom().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());

                for (Produit produit : filteredProducts) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/product.fxml"));
                    Parent parent = fxmlLoader.load();
                    ProductController productController = fxmlLoader.getController();
                    productController.setProduct(produit);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    productsFlowPane.getChildren().add(node);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }

}
