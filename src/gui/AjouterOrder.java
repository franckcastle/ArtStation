package gui;

import entities.CartItem;
import entities.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.CItemService;
import services.ShoppingCartService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;

public class AjouterOrder implements Initializable {

    @FXML
    private TextField addresse_column;


    @FXML
    private TextField code_column;

    @FXML
    private TextField nom_column;

    @FXML
    private TextField prenom_column;

    @FXML
    private TextField prix_totale;

    @FXML
    private TextField ville_column;
    @FXML
    private Button retourbtn;
    @FXML
    void onClickAjouter(ActionEvent event) throws IOException {


        ShoppingCartService ss =new ShoppingCartService();
        ShoppingCart s =new ShoppingCart();
        s.setNom(nom_column.getText());
        s.setPrenom(prenom_column.getText());
        s.setVille(ville_column.getText());
        s.setAdresse(addresse_column.getText());
        s.setCode_postale(Integer.parseInt(code_column.getText()));
        s.setTotal_price(parseFloat(prix_totale.getText()));


       // Parent loader = FXMLLoader.load(getClass().getResource("ajouterOrder.fxml"));
       // retourbtn.getScene().setRoot(loader);
        try {
            ss.ajouter(s);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void reset() {
        nom_column.setText("");
        prenom_column.setText("");
       ville_column.setText("");
        addresse_column.setText("");

       code_column.setText("");
        prix_totale.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
