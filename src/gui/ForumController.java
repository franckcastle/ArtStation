package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForumController implements Initializable {


    @FXML
    private TextField textfield;



    @FXML
    private Button ajouter;

    @FXML
    void ajouter(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterStatut.fxml"));
            textfield.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
