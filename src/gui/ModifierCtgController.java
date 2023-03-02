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
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierCtgController implements Initializable {

    @FXML
    public TextField nomTf;
    @FXML
    public Button ModifierCategorie;

    CategorieService cs = new CategorieService();

    public Categorie c;

    public ModifierCtgController () {
    }

    public ModifierCtgController (Categorie c) {
        this.c=c;

    }

    @FXML
    public void initialize() {
        // Set the text of the text fields with the values of the 'p' object
        nomTf.setText(c.getNom_ctg());
    }

    @FXML
    void modifier(ActionEvent event) {

        if(!nomTf.getText().isEmpty()){
            Categorie cc = new Categorie();
            cc.setId_ctg(c.getId_ctg());
            cc.setNom_ctg(nomTf.getText());
            cs.modifierCategorie(cc);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }}
    @FXML
    public void retour(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Afficherctg.fxml"));

            //Parent root = loader.load();
            nomTf.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setCategorie(Categorie c) {
        this.c=c;
    }
}
