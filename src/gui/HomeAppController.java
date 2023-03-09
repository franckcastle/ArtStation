package gui;

import entities.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeAppController {
    @FXML
    private AnchorPane home;

    @FXML
    void Events(ActionEvent event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));
        home.getScene().setRoot(loader);
    }

    @FXML
    void Shop(ActionEvent event) throws IOException {
        if (Session.getUserCon().getRole().equals("Admin")){
            Parent loader = FXMLLoader.load(getClass().getResource("AffichagePdt.fxml"));
            home.getScene().setRoot(loader);
        }else if (Session.getUserCon().getRole().equals("Client")){
            Parent loader = FXMLLoader.load(getClass().getResource("market.fxml"));
            home.getScene().setRoot(loader);
        }else {
            Parent loader = FXMLLoader.load(getClass().getResource("InterfaceArt.fxml"));
            home.getScene().setRoot(loader);
        }
    }



    @FXML
    void Workshop(ActionEvent event)  throws IOException {
        if (Session.getUserCon().getRole().equals("Admin")){
        Parent loader = FXMLLoader.load(getClass().getResource("InterfaceAdminWs.fxml"));
        home.getScene().setRoot(loader);
    }else if (Session.getUserCon().getRole().equals("Client")){
            Parent loader = FXMLLoader.load(getClass().getResource("ChoisirWorkshop.fxml"));
            home.getScene().setRoot(loader);
        }else {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterWorkshop.fxml"));
            home.getScene().setRoot(loader);
        }
    }

}
