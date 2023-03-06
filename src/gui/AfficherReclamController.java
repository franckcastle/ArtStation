package gui;
import entities.Reclamation;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import services.ReclamService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherReclamController implements Initializable {
    ReclamService rs = new ReclamService();
    @FXML
    private TextArea plainteTf;

    @FXML
    private ListView<Reclamation> reclamLv;

    @FXML
    private TextField sujetTf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sujet=sujetTf.getText();
        String plainte = plainteTf.getText();
        try {
            List<Reclamation> reclamList = rs.recuperer();
            ObservableList<Reclamation> observableReclamList = FXCollections.observableList(reclamList);
            reclamLv.setItems(observableReclamList);
            reclamLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            reclamLv.setCellFactory(new Callback<ListView<Reclamation>, ListCell<Reclamation>>() {
                @Override
                public ListCell<Reclamation> call(ListView<Reclamation> listView) {
                    return new ListCell<Reclamation>() {
                        @Override
                        protected void updateItem(Reclamation reclamation, boolean empty) {
                            super.updateItem(reclamation, empty);
                            if (reclamation != null) {
                                setText(reclamation.getSujet() + " - " +reclamation.getUserId());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            reclamLv.setOnMouseClicked(event -> {
                Reclamation reclamationSelectionne = reclamLv.getSelectionModel().getSelectedItem();
                if (reclamationSelectionne != null) {

                    sujetTf.setText(reclamationSelectionne.getSujet());
                    plainteTf.setText(reclamationSelectionne.getPlainte());


                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    public void SupprimerReclam(ActionEvent event)throws SQLException{

        String sujet = sujetTf.getText();
        rs.supprimer(rs.GetReclamBySujet(sujet));
        reclamLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Reclamation> observableReclamList = FXCollections.observableList(rs.recuperer());
        reclamLv.setItems(observableReclamList);
        // Réinitialisation de la sélection de la ListView
        reclamLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        sujetTf.clear();
        plainteTf.clear();

    }
}
