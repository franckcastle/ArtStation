package gui;

import entities.Categorie;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.CategorieService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjoutCtgController implements Initializable {
    @FXML
    public TextField idctgTf;
    @FXML
    public TextField nomctgTf;
    @FXML
    private Button retourId;

    CategorieService cs = new CategorieService();

    @FXML
    public void AjouterCategorie (ActionEvent event) throws SQLException {

        Categorie c= new Categorie();
        c.setId_ctg(Integer.parseInt(idctgTf.getText()));
        c.setNom_ctg (nomctgTf.getText());
        try{
            cs.ajouterCategorie(c);
        reset();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void reset() {
        idctgTf.setText("");
        nomctgTf .setText("");

    }
    @FXML
    public void retour(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherCtg.fxml"));

            //Parent root = loader.load();
            idctgTf.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
