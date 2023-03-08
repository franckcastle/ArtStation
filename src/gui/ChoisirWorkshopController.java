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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.WorkshopServices;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ChoisirWorkshopController implements Initializable {
    WorkshopServices ws = new WorkshopServices();

    @FXML
    private ComboBox<String> catId;
    @FXML
    private TableView tableChoix;

    @FXML
    private TableColumn titreChoix;
    @FXML
    private TableColumn DureeChoix;



    @FXML
    private TableColumn  dateChoix;

    @FXML
    private TableColumn heure_debutChoix;

    @FXML
    private TableColumn heure_finChoix;

    @FXML
    private TableColumn nom_artisteChoix;

    @FXML
    private TableColumn prixChoix;


    private String etatSelectionne ;

    @FXML
    private TableColumn <Workshop,Button> details;
    @FXML
    private TableColumn<Workshop, Button> modifier;

    @FXML
    private TextField ess;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        WorkshopServices ws = new WorkshopServices();


        try {

            List<Workshop> cat = ws.getAll();
            ObservableList<String> catNames = FXCollections.observableArrayList();
            catNames.add(0, "All");

            // liste temporaire pour stocker les noms de catégories uniques
            List<String> uniqueCatNames = new ArrayList<>();

            for (Workshop c : cat) {
                String catName = c.getCategorie();
                // Vérifier si le nom de catégorie est déjà présent dans la liste des catégories uniques
                if (!uniqueCatNames.contains(catName)) {

                    uniqueCatNames.add(catName);
                    catNames.add(catName);
                }
            }

            catId.setItems(catNames);

            catId.setOnAction(event -> {

                etatSelectionne = catId.getSelectionModel().getSelectedItem();

                System.out.println(etatSelectionne);

                if (etatSelectionne.equals("All")) {
                    // récupérer tous les workshops
                    ObservableList<Workshop> liste = null;
                    try {
                        liste = FXCollections.observableArrayList(ws.getAll());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    tableChoix.setItems(liste);
                } else {
                    // filtrer par catégorie sélectionnée
                    ObservableList<Workshop> liste = WorkshopServices.getIdByCategorie(etatSelectionne);
                    tableChoix.setItems(liste);
                }

                titreChoix.setCellValueFactory(new PropertyValueFactory<>("titre"));
                nom_artisteChoix.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
                dateChoix.setCellValueFactory(new PropertyValueFactory<>("date"));
                prixChoix.setCellValueFactory(new PropertyValueFactory<>("prix"));

                this.details();






            });







        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }


    public void details(){
        details.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Plus de détails");
                        b.setOnAction(event -> {
                           /* try {
                                Parent root = FXMLLoader.load(getClass().getResource("/gui/Details.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setTitle("Consultation des réclamations");
                                stage.setScene(scene);
                                stage.show();*/

                            try {
                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("Details.fxml"));
                                Parent root = loader.load();
                                Workshop wp= (Workshop) tableChoix.getItems().get(getIndex());
                                DetailsController controller =loader.getController();
                                controller.setWorkshop(wp);
                                controller.initialize();
                                tableChoix.getScene().setRoot(root);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }




                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }









}
