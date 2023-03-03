package gui;

import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ProduitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Panier {

    @FXML
    private TableView<Produit> itemstable;

    @FXML
    private TableColumn<?, ?> nom_column;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TableColumn<?, ?> quantity_column;
    @FXML
    private Button b;

ProduitService ps=new ProduitService();
int s;

    public void display(int i){
System.out.println(i);
        try {
            List<Produit> cartItems = ps.getByorderId(i);
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


    public void buy(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader =  new FXMLLoader(getClass().getResource("panierOrder.fxml"));
        Parent root = loader.load();
        PanierOrder controller =loader.getController();
        System.out.println(s);
       controller.p=s;
        itemstable.getScene().setRoot(root);
    }

    public void retour(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Home.fxml"));

            //Parent root = loader.load();
            itemstable.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
