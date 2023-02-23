package gui;

import entities.Workshop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.WorkshopServices;
import java.io.IOException;


import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherWorkshopController implements Initializable {

    WorkshopServices ws = new WorkshopServices();

    @FXML
    private TableView AfficherId;

    @FXML
    public TableColumn titreField;
    @FXML
    public TableColumn dureeField;
    @FXML
    public TableColumn nom_artisteField;
    @FXML
    public TableColumn dateField;
    @FXML
    public TableColumn heure_debutField;
    @FXML
    public TableColumn heure_finField;
    @FXML
    public TableColumn prixField;
    @FXML
    public TableColumn nbPlacesField;
    @FXML
    private TableColumn<Workshop, Button> delete;

    @FXML
    private Label welcomeLb;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Workshop> w = ws.getAll();

            ObservableList<Workshop> olp = FXCollections.observableArrayList(w);
            AfficherId.setItems(olp);
            titreField.setCellValueFactory(new PropertyValueFactory("titre"));
            dureeField.setCellValueFactory(new PropertyValueFactory("duree"));
            nom_artisteField.setCellValueFactory(new PropertyValueFactory("nom_artiste"));
            dateField.setCellValueFactory(new PropertyValueFactory("date"));
            heure_debutField.setCellValueFactory(new PropertyValueFactory("heure_debut"));
            heure_finField.setCellValueFactory(new PropertyValueFactory("heure_fin"));
            prixField.setCellValueFactory(new PropertyValueFactory("prix"));
            nbPlacesField.setCellValueFactory(new PropertyValueFactory("nbPlaces"));


        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }


}
