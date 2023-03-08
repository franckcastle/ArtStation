package gui;



import entities.Statut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import services.CommentaireService;
import services.StatutService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class AffchStaController implements  Initializable{
    StatutService ss = new StatutService();
    @FXML
    private TextField titreTf;

    @FXML
    private TextField contenuTf;

    @FXML
    private TextField createdTf;

    @FXML
    private TextField updatedTf;
    @FXML
    private TextField idsTf;



    @FXML
    private ListView<Statut> statLv;



    @FXML
    void modifier(ActionEvent event) throws SQLException{
        Statut s = new Statut();
        String titre = titreTf.getText();
        String contenu = contenuTf.getText();
        // String date_ajout = dateajouTf.getText();
        s.setId_s(Integer.parseInt(idsTf.getText()));
        s.setTitre(titreTf.getText());
        s.setContenu(contenuTf.getText());
        s.setCreated(Date.valueOf(createdTf.getText()));
        s.setUpdated(Date.valueOf(updatedTf.getText()));
        ss.modifier(s);
        statLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Statut> observableUserList = FXCollections.observableList(ss.recuperer());
        statLv.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        statLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        //     idcTf.clear();
        titreTf.clear();
        contenuTf.clear();
        //   dateajouTf.clear();
    }


    @FXML
    void supprimer(ActionEvent event) throws SQLException{
        int id_s=Integer.parseInt(idsTf.getText());
        String titre=titreTf.getText();
        String contenu=contenuTf.getText();

        //  String date_ajout=dateajouTf.getText();

        ss.supprimer(id_s);
        statLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Statut> observableUserList = FXCollections.observableList(ss.recuperer());
        statLv.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        statLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        idsTf.clear();
        titreTf.clear();
        contenuTf.clear();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            ObservableList<Statut> observableUserList = FXCollections.observableList(ss.recuperer());
            statLv.setItems(observableUserList);
            statLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            statLv.setCellFactory(new Callback<ListView<Statut>, ListCell<Statut>>() {
                @Override
                public ListCell<Statut> call(ListView<Statut> listView) {
                    return new ListCell<Statut>() {
                        @Override
                        protected void updateItem(Statut s, boolean empty) {
                            super.updateItem(s, empty);
                            if (s != null) {
                                setText(s.getId_s() + " - " +s.getTitre()+ " - " +s.getContenu());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            statLv.setOnMouseClicked(event -> {
                Statut comSelectionne = statLv.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {

                    idsTf.setText(Integer.toString(comSelectionne.getId_s()));
                    titreTf.setText(comSelectionne.getTitre());
                    contenuTf.setText(comSelectionne.getContenu());

                    //dateajouTf.setText(comSelectionne.getDate_ajout());

                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}

