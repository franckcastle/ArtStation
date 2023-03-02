package gui;

import entities.Statut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.awt.event.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.StatutService;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class AfficherStatutController implements Initializable{
    @FXML
    public TableView<Statut> statutsTv;
    @FXML
    private TableColumn<Statut, Integer> idTv;
    @FXML
    private TableColumn<Statut, String> titreTv;
    @FXML
    private TableColumn<Statut, String> contenuTv;
    @FXML
    private TableColumn<Statut, Date> createdTv;
    @FXML
    private TableColumn<Statut, Date> updatedTv;
    @FXML
    private TableColumn<Statut , Button> supprimer;
    @FXML
    private TableColumn<Statut, Button> modifier;
    @FXML
    private TableColumn<String, Button> commenter2;
    @FXML
    private TableColumn<Statut, Integer> nbrlikeTv;


    @FXML
    private Button liker;

    private boolean isLiked;

    @FXML
    private ImageView icon;

    StatutService s = new StatutService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Statut> stt =s.recuperer();

            ObservableList<Statut> olp = FXCollections.observableArrayList(stt);
            statutsTv.setItems(olp);
            idTv.setCellValueFactory(new PropertyValueFactory<>("id_s"));
            titreTv.setCellValueFactory(new PropertyValueFactory<>("titre"));
            contenuTv.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            createdTv.setCellValueFactory(new PropertyValueFactory<>("created"));
            updatedTv.setCellValueFactory(new PropertyValueFactory<>("updated"));
            nbrlikeTv.setCellValueFactory(new PropertyValueFactory<>("nbrLike"));



            this.supprimer();
            this.modifier();
            this.commenter2();
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }
        }




    @FXML
    public void commenter2() {
        commenter2.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("commenter");
                        b.setOnAction((event) -> {

                            try {
                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("AjouterCom.fxml"));
                                Parent root = loader.load();

                               Statut st =(Statut)statutsTv.getItems().get(getIndex());
                               AjouterComController controller = loader.getController();
                               controller.sta=st;
                                statutsTv.getScene().setRoot(root);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
    void liker(ActionEvent event) throws SQLException {

        Statut selectedStatus = statutsTv.getSelectionModel().getSelectedItem();
        if (selectedStatus == null) {
            // show error message if no item is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No status Selected");
            alert.setContentText("please select a status to like.");
        } else {
            if (!isLiked) {
                isLiked = true;

                s.ajouterLikeStatut(selectedStatus);
               // icon.setImage(new Image(getClass().getResourceAsStream("https: //www.flaticon.com/free-icons/heart")));

            } else {
                isLiked = false;
              //  icon.setImage(new Image(getClass().getResourceAsStream("https: //www.flaticon.com/free-icons/heart")));

            }
        }

    }

    @FXML
   public void modifier() {
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction((event) -> {

                            try {
                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("ModifierStatut.fxml"));
                                Parent root = loader.load();

                                ModifierStatutController controller = loader.getController();

                                int rowIndexBtnmodifier = getIndex();
                                controller.setTitre(titreTv.getCellData(rowIndexBtnmodifier));
                                controller.setContenu(contenuTv.getCellData(rowIndexBtnmodifier));

                                statutsTv.getScene().setRoot(root);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }



    public void supprimer() {
        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("supprimer");
                        b.setOnAction((event) -> {
                            Statut stt= (Statut) statutsTv.getItems().get(getIndex());
                            try {
                                if (s.supprimer(stt.getId_s())) {
                                    statutsTv.getItems().remove(getIndex());
                                    statutsTv.refresh();

                                }
                            } catch (SQLException e) {
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

