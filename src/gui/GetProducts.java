package gui;

import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GetProducts implements Initializable {

    @FXML
    private TableView itemstable;

    @FXML
    private TableColumn nom_column;

    @FXML
    private TableColumn price_column;

    @FXML
    private TableColumn quantity_column;

    @FXML
    private Button btn;

    ProduitService Ps = new ProduitService();
    public int sc;

    public GetProducts() {
    }
    public GetProducts(int sc) {
        this.sc = sc;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(sc);
      /* try {
            List<Produit> cartItems = Ps.getByorderId(i);
            ObservableList<Produit> olu = FXCollections.observableArrayList(cartItems);

            itemstable.setItems(olu);
            nom_column.setCellValueFactory(new PropertyValueFactory("nom"));
            price_column.setCellValueFactory(new PropertyValueFactory("prenom"));
          quantity_column.setCellValueFactory(new PropertyValueFactory("ville"));

       } catch (SQLException ex) {
           System.out.println("error" + ex.getMessage());
        }*/
 }


    public void test(javafx.event.ActionEvent actionEvent) {
       System.out.println(sc);
           try {
            List<Produit> cartItems = Ps.getByorderId(sc);
                System.out.println(cartItems);
            ObservableList<Produit> olu = FXCollections.observableArrayList(cartItems);

            itemstable.setItems(olu);
            nom_column.setCellValueFactory(new PropertyValueFactory("nom"));
            price_column.setCellValueFactory(new PropertyValueFactory("prix"));
          quantity_column.setCellValueFactory(new PropertyValueFactory("qte_stock"));

       } catch (SQLException ex) {
           System.out.println("error" + ex.getMessage());
        }
    }
    @FXML
    public void prod(ActionEvent event)throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
    @FXML
    public void panier(ActionEvent event)throws IOException{

        AnchorPane root = FXMLLoader.load(getClass().getResource("OrderAffiche.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
    @FXML
    public void workshop(ActionEvent event)throws IOException{

        AnchorPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminWs.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
    @FXML
    public void even(ActionEvent event)throws IOException{

        AnchorPane root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
    @FXML
    public void forum(ActionEvent event)throws IOException{

        BorderPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminForum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
    @FXML
    public void categorie(ActionEvent event)throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("AfficherCtg.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
}