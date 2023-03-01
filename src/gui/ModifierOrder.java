package gui;

import entities.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ShoppingCartService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierOrder implements Initializable {

    @FXML
    private TextField addresse_col;

    @FXML
    private TextField etat_col;

    @FXML
    private Button modifier_button;

    @FXML
    private TextField nom_col;

    @FXML
    private TextField prenom_col;

    @FXML
    private TextField ville_col;

    @FXML
    private TextField postale_id;
    @FXML
    private Button retourId;

     ShoppingCartService ss =new ShoppingCartService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public ShoppingCart sc;

    public ModifierOrder() {
    }

    public ModifierOrder(ShoppingCart sc) {
        this.sc=sc;
    }




    @FXML
    void btnModifier(ActionEvent event) throws SQLException {

        System.out.println(sc);

        if(!nom_col.getText().isEmpty()||!prenom_col.getText().isEmpty()||!prenom_col.getText().isEmpty()||!ville_col.getText().isEmpty()||!addresse_col.getText().isEmpty()){
            ShoppingCart s = new ShoppingCart();
           s.setOrderId(sc.getOrderId());
            s.setNom(nom_col.getText());
            s.setPrenom(prenom_col.getText());
            s.setVille(ville_col.getText());
            s.setAdresse(addresse_col.getText());
            s.setCode_postale(Integer.parseInt(postale_id.getText()));
            s.setSta(etat_col.getText());
            ss.modifier(s);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }


    }
    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("afficherOrder.fxml"));

            //Parent root = loader.load();
            nom_col.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
