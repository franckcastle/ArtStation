package gui;


import entities.Reservation_Workshop;
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
import services.Reservation_WorkshopServices;
import services.WorkshopServices;
import utils.MyDB;

import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.net.URL;
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


    private int idCategorieSelectionnee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        WorkshopServices ws =new WorkshopServices();
        try{

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

            catId.setOnAction(event->{

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
                DureeChoix.setCellValueFactory(new PropertyValueFactory<>("duree"));
                dateChoix.setCellValueFactory(new PropertyValueFactory<>("date"));
                heure_debutChoix.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
                heure_finChoix.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
                nom_artisteChoix.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
                prixChoix.setCellValueFactory(new PropertyValueFactory<>("prix"));

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
                            Workshop ws= (Workshop)tableChoix.getItems().get(getIndex());

                            System.out.println(ws);

                            int id_workshop = ws.getId();

                            System.out.println(id_workshop);

                            int id_user = 10;// Remplacez 1 par l'ID de l'utilisateur connecté
                            String categorie = etatSelectionne;

                            Reservation_Workshop r= new Reservation_Workshop();
                            r.setId_workshop(id_workshop);
                            r.setId_user(id_user);
                            r.setCategorie(categorie);
                            System.out.println("sucessssss");


                            Reservation_WorkshopServices rs= new Reservation_WorkshopServices();
                            try {
                                rs.ajouterR(r);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        });
                        setGraphic(b);
                    }
                }
            };
        });
    }






}
