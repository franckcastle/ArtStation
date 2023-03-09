package gui;

import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MarketController implements Initializable {

    ProduitService ps = new ProduitService();
  int c =22;
    @FXML
    public ScrollPane scroll;
    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private TextField searchField;
    @FXML
    public Button trierparprix;
    @FXML
    public Button trierparnom;

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
                    productController.c=c;
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
    @FXML
    private void updateViewByPrice() {
        if (productsFlowPane != null) {
            try {
                List<Produit> listProd = ps.getByPrice();
                productsFlowPane.getChildren().clear();

                for (Produit produit : listProd) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/product.fxml"));
                    Parent parent = fxmlLoader.load();
                    ProductController productController = fxmlLoader.getController();

                    productController.setProduct(produit);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    productsFlowPane.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }

    @FXML
    private void updateViewByName() {
        if (productsFlowPane != null) {
            try {
                List<Produit> listProd = ps.getByName();
                productsFlowPane.getChildren().clear();

                for (Produit produit : listProd) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/product.fxml"));
                    Parent parent = fxmlLoader.load();
                    ProductController productController = fxmlLoader.getController();
                    productController.setProduct(produit);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    productsFlowPane.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }

    public void panierbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("panierT.fxml"));
        Parent root = loader.load();
        PanierT controller =loader.getController();
        controller.display(c);
        controller.s=c;
        trierparnom.getScene().setRoot(root);
    }

    @FXML
    void events(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("UsEvent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void forum(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("HomeApp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void orders(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("PanierT.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void products(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("HomeApp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void workshops(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("ChoisirWorkshop.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

}
