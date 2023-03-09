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

import javafx.scene.text.Text;
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
    private Text total;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TableColumn<?, ?> quantity_column;
    @FXML
    private Button b;

ProduitService ps=new ProduitService();
int s;
Float t ;
    public void display(int i){


        try {
            List<Produit> cartItems = ps.getByorderId(i);
            System.out.println(cartItems);
            System.out.println(getTotalPrice(cartItems));

           Float tot =getTotalPrice(cartItems);
           t =tot ;
            ObservableList<Produit> olu = FXCollections.observableArrayList(cartItems);
            itemstable.setItems(olu);
            nom_column.setCellValueFactory(new PropertyValueFactory("nom"));
            price_column.setCellValueFactory(new PropertyValueFactory("prix"));
            quantity_column.setCellValueFactory(new PropertyValueFactory("qte_stock"));
            total.setText(String.valueOf(tot));
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }



    public void buy(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("panierOrder.fxml"));
        Parent root = loader.load();
        PanierOrder controller =loader.getController();
       // Cart c =loader.getController();
        //c.setUsername(t);
        System.out.println(s);
       controller.p=s;
        controller.price=t;
        itemstable.getScene().setRoot(root);
    }

    public static Float getTotalPrice(List<Produit> produits) {
        Float total = 0.0f;
        for (Produit p : produits) {
            total += p.getPrix();
        }
        return total;
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
