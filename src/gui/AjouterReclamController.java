package gui;

import entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.ReclamService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterReclamController implements Initializable {
    @FXML
    private ImageView back;
    @FXML
    public TextField sujetTf;
    @FXML
    public TextField plainteTf;

    ReclamService rs = new ReclamService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    private void reset() {
        sujetTf.setText("");
        plainteTf.setText("");
    }

    @FXML
    public void handleBack(Event event) {
    }

    @FXML
    void AjouterReclam(ActionEvent event) {
        try{
            Reclamation r = new Reclamation();
            r.setUserId(10);
            r.setSujet(sujetTf.getText());
            r.setPlainte(plainteTf.getText());
            rs.ajouter(r);
            System.out.println("reclamation ajoutée");
        }catch (SQLException ex){
            System.out.println("erreur d'ajout reclamation ");
        }
    }


}