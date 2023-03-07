package gui;

import com.mysql.cj.protocol.Message;
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

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherWorkshopController implements Initializable {
    Connection cnx;

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
    public TableColumn categorieField;

    @FXML
    public TableColumn nbPlacesField;
    @FXML
    private TableColumn delete;
    @FXML
    private TableColumn modifier;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Workshop> w = ws.recuperer();

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
            categorieField.setCellValueFactory(new PropertyValueFactory("categorie"));
            this.delete();
            this.modifier();

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
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
                            try {
                                if (ws.supprimerWs((Workshop) AfficherId.getItems().get(getIndex()))) {
                                    AfficherId.getItems().remove(getIndex());
                                    AfficherId.refresh();

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




    public void modifier(){
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction(event -> {


                            try {
                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("ModifierWorkshop.fxml"));
                                Parent root = loader.load();
                                Workshop wp= (Workshop) AfficherId.getItems().get(getIndex());
                                ModifierWorkshopController controller =loader.getController();
                                controller.setWorkshop(wp);
                                controller.initialize();
                                AfficherId.getScene().setRoot(root);

                             //   TableCell cell = (TableCell) ((Button) event.getSource()).getParent();
                              //  int rowIndexBtnmodifier = cell.getIndex();











                              //  controller.w=wp;
                                // AfficherId.getScene().setRoot(root);

                                //Stage stage = new Stage();
                                //stage.setScene(new Scene(root));
                                //stage.show();
                                //AnchorPane anchorPane = (AnchorPane) root;

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Parent root = loader.load();



                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }


    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterWorkshop.fxml"));

            //Parent root = loader.load();
            AfficherId.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}