package gui;

import entities.CartItem;
import entities.Produit;
import entities.ShoppingCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CItemService;
import services.ProduitService;

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



    ProduitService Ps = new ProduitService();

    public ShoppingCart sc;


    public GetProducts() {
    }
    public GetProducts(ShoppingCart sc) {
        this.sc = sc;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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


}