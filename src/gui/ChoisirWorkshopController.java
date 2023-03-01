package gui;


import entities.Workshop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.WorkshopServices;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ChoisirWorkshopController implements Initializable {

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

    @FXML
    private TableColumn choisir;
    private String etatSelectionne ;



    WorkshopServices ws =new WorkshopServices();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            List<Workshop> cat = ws.getAll();
            ObservableList<String> catNames = FXCollections.observableArrayList();

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



            catId.setOnAction(event->{

                etatSelectionne = catId.getSelectionModel().getSelectedItem();
                System.out.println(etatSelectionne);



                // Appeler la méthode rechercherReclamation avec l'état sélectionné
                /// CRUDReclamation crudReclamation = new CRUDReclamation();
                // ObservableList<Reclamation> reclamations = reclamations.RechercherReclamation(etatSelectionne);
                Workshop workshop = new Workshop();
                ObservableList<Workshop> listeReclamations = WorkshopServices.assister(etatSelectionne);
                ObservableList<Workshop> reclamations = FXCollections.observableArrayList(listeReclamations);
                titreChoix.setCellValueFactory(new PropertyValueFactory<>("titre"));
                DureeChoix.setCellValueFactory(new PropertyValueFactory<>("duree"));
                dateChoix.setCellValueFactory(new PropertyValueFactory<>("date"));
                heure_debutChoix.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
                heure_finChoix.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
                nom_artisteChoix.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
                prixChoix.setCellValueFactory(new PropertyValueFactory<>("prix"));



                tableChoix.setItems(FXCollections.observableArrayList(reclamations));
            } );

           this.choisir();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void choisir(){

        choisir.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Choisir");
                        b.setOnAction((event) -> {
                            try {
                                if (ws.supprimerWs((Workshop) tableChoix.getItems().get(getIndex()))) {
                                    tableChoix.getItems().remove(getIndex());
                                    tableChoix.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }
}
