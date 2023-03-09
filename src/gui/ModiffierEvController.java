package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModiffierEvController implements Initializable {
    @FXML
    private TextField titreId;
    EvenementService es = new EvenementService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public Evenement ee;
    int iii;

    public ModiffierEvController(int iii) {
        this.iii = iii;
    }

    public ModiffierEvController() {
    }

    public ModiffierEvController(Evenement ee) {
        this.ee = ee;
    }


    public void modifierEv(ActionEvent event){
    System.out.println(ee);

        if(!titreId.getText().isEmpty()){
            Evenement e = new Evenement();
            //e.setId(ee.getId());
            e.setId(iii);
            e.setTitre(titreId.getText());
            es.modifierEvent(e);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }


        //e.setTitre(titreId.getText());
        //es.modifierEvent(e);
        //cmnt
    }
    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherEv.fxml"));

            //Parent root = loader.load();
            titreId.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void retour(MouseEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));

            //Parent root = loader.load();
            titreId.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
