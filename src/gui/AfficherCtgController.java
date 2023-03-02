package gui;

import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CategorieService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCtgController implements Initializable {

    CategorieService cs = new CategorieService();

    @FXML
    public TableView CategorieTv;
    @FXML
    public TableColumn idCtgTv;
    @FXML
    public TableColumn nomCtgTv;
    @FXML
    public TableColumn supprimer;
    @FXML
    public TableColumn modifier;
    @FXML
    public Button ajouter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
            List<Categorie> cat = cs.getAll();

            ObservableList<Categorie> olp = FXCollections.observableArrayList(cat);
            CategorieTv.setItems(olp);
            idCtgTv.setCellValueFactory(new PropertyValueFactory("Id_ctg"));
            nomCtgTv.setCellValueFactory(new PropertyValueFactory("Nom_ctg"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void AjouterCategorie (ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjoutCtg.fxml"));

            //Parent root = loader.load();
            CategorieTv.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
