package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class InterfaceAdminWsController {
    @FXML
    private Label labelField;

    @FXML
    private Button workshops;

    @FXML
    private Button reservations;



    @FXML
    public void workshops(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterWorkshop.fxml"));
            labelField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    }


    @FXML
    public void reservations(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            labelField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

        }



}
