package gui;

import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherEvController implements Initializable {
    EvenementService es = new EvenementService();
    @FXML
    private Button AjouterBtn;
    @FXML
    public TableView EventsTv;
    @FXML
    public TableColumn evaluatinTv;
    @FXML
    public TableColumn titreTv;
    @FXML
    public TableColumn descriptionTv;
    @FXML
    public TableColumn localisationTv;
    @FXML
    public TableColumn dateDebutTv;
    @FXML
    public TableColumn dateFinTv;
    @FXML
    public TableColumn prixTv;
    @FXML
    public TableColumn nbPlaceTv;
    @FXML
    private TableColumn<Evenement, Button> delete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Evenement> ev =es.getAll();

            ObservableList<Evenement> olp = FXCollections.observableArrayList(ev);
            EventsTv.setItems(olp);
            evaluatinTv.setCellValueFactory(new PropertyValueFactory("evaluation"));
            titreTv.setCellValueFactory(new PropertyValueFactory("titre"));
            descriptionTv.setCellValueFactory(new PropertyValueFactory("description"));
            localisationTv.setCellValueFactory(new PropertyValueFactory("localisation"));
            dateDebutTv.setCellValueFactory(new PropertyValueFactory("dateDebut"));
            dateFinTv.setCellValueFactory(new PropertyValueFactory("dateFin"));
            prixTv.setCellValueFactory(new PropertyValueFactory("prix"));
            nbPlaceTv.setCellValueFactory(new PropertyValueFactory("nbPlace"));

            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    private Stage stage;
    private Scene scene;
    @FXML
    public void ajouterEv(ActionEvent event){
        try {
        Parent loader = FXMLLoader.load(getClass().getResource("AjouterEv.fxml"));

            //Parent root = loader.load();
            EventsTv.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void delete() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            Evenement ev= (Evenement) EventsTv.getItems().get(getIndex());
                            if (es.supprimerEv(ev.getId())) {
                                EventsTv.getItems().remove(getIndex());
                                EventsTv.refresh();

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }
}
