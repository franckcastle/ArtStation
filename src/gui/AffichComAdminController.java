package gui;



import entities.Commentaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import services.CommentaireService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class AffichComAdminController implements  Initializable{
    CommentaireService cs = new CommentaireService();
    @FXML
    private TextField descriptionTf;

    @FXML
    private TextField dateajouTf;

    @FXML
    private TextField idcTf;



    @FXML
    private ListView<Commentaire> comsLv;



    @FXML
    void modifierCom(ActionEvent event) throws SQLException{
        Commentaire c = new Commentaire();

        String description = descriptionTf.getText();
       // String date_ajout = dateajouTf.getText();
        c.setId_c(Integer.parseInt(idcTf.getText()));
        c.setDescription(descriptionTf.getText());
        cs.modifierCom(c);
        comsLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Commentaire> observableUserList = FXCollections.observableList(cs.recupererCom());
        comsLv.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        comsLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
   //     idcTf.clear();
        descriptionTf.clear();
     //   dateajouTf.clear();
    }


    @FXML
    void supprimerCom(ActionEvent event) throws SQLException{
        int id_c=Integer.parseInt(idcTf.getText());
       String description=descriptionTf.getText();
      //  String date_ajout=dateajouTf.getText();

        cs.supprimerCom(id_c);
        comsLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Commentaire> observableUserList = FXCollections.observableList(cs.recupererCom());
        comsLv.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        comsLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        idcTf.clear();
        descriptionTf.clear();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            ObservableList<Commentaire> observableUserList = FXCollections.observableList(cs.recupererCom());
            comsLv.setItems(observableUserList);
            comsLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            comsLv.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() {
                @Override
                public ListCell<Commentaire> call(ListView<Commentaire> listView) {
                    return new ListCell<Commentaire>() {
                        @Override
                        protected void updateItem(Commentaire c, boolean empty) {
                            super.updateItem(c, empty);
                            if (c != null) {
                                setText(c.getId_c() + " - " +c.getDescription()+ " - " +c.getDate_ajout());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            comsLv.setOnMouseClicked(event -> {
                Commentaire comSelectionne = comsLv.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {

                    idcTf.setText(Integer.toString(comSelectionne.getId_c()));
                    descriptionTf.setText(comSelectionne.getDescription());
                    //dateajouTf.setText(comSelectionne.getDate_ajout());

                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}

