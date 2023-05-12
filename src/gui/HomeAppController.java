package gui;

import entities.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeAppController {
    @FXML
    private AnchorPane home;

    @FXML
    void Events(ActionEvent event) throws IOException {
        if (Session.getUserCon().getRole().equals("Admin")) {
            Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));
            home.getScene().setRoot(loader);
        } else {
            Parent loader = FXMLLoader.load(getClass().getResource("Usevent.fxml"));
            home.getScene().setRoot(loader);
        }
    }

    @FXML
    void Shop(ActionEvent event) throws IOException {
        if (Session.getUserCon().getRole().equals("Admin")) {
            Parent loader = FXMLLoader.load(getClass().getResource("AffichagePdt.fxml"));
            home.getScene().setRoot(loader);
        } else if (Session.getUserCon().getRole().equals("Client")) {
            Parent loader = FXMLLoader.load(getClass().getResource("market.fxml"));
            home.getScene().setRoot(loader);
        } else {
            Parent loader = FXMLLoader.load(getClass().getResource("InterfaceArt.fxml"));
            home.getScene().setRoot(loader);
        }
    }


    @FXML
    void Workshop(ActionEvent event) throws IOException {
        if (Session.getUserCon().getRole().equals("Admin")) {
            Parent loader = FXMLLoader.load(getClass().getResource("InterfaceAdminWs.fxml"));
            home.getScene().setRoot(loader);
        } else if (Session.getUserCon().getRole().equals("Client")) {
            Parent loader = FXMLLoader.load(getClass().getResource("ChoisirWorkshop.fxml"));
            home.getScene().setRoot(loader);
        } else {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterWorkshop.fxml"));
            home.getScene().setRoot(loader);
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(600);
        stage.setHeight(400);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
}
