package gui;

import entities.Reclamation;
import entities.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.ReclamService;

import java.io.IOException;
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
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherUsers.fxml"));
            sujetTf.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("xx"+ex.getMessage());
        }
    }

    @FXML
    void AjouterReclam(ActionEvent event) {
        try{
            Reclamation r = new Reclamation();
            r.setUserId(Session.getUserCon().getUserId());
            r.setSujet(sujetTf.getText());
            r.setPlainte(plainteTf.getText());
            rs.ajouter(r);
            reset();
            System.out.println("reclamation ajoutée");
        }catch (SQLException ex){
            System.out.println("erreur d'ajout reclamation ");
        }
    }


}