package gui;

import entities.Commentaire;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CommentaireService;

import java.net.URL;
import java.util.ResourceBundle;
public class AfficherComController implements Initializable {

    @FXML
    private TableView<Commentaire> commentairesTv;
    @FXML
    private  TableColumn<Commentaire, Integer >  idTv;
    @FXML
    private  TableColumn<Commentaire, String> descriptionTv;
    @FXML
    private TableColumn<Commentaire, Date> dateajoutTv;
    @FXML
    private TableColumn<Commentaire, Button> delete;


    CommentaireService cs = new CommentaireService() ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Commentaire> cm = cs.recupererCom();

            ObservableList<Commentaire> olp = FXCollections.observableArrayList(cm);
            commentairesTv.setItems(olp);
            idTv.setCellValueFactory(new PropertyValueFactory<>("id_c"));
            descriptionTv.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateajoutTv.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));

            this.supprimerCom();
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }
    }



    @FXML
    void supprimerCom() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("supprimerCom");
                        b.setOnAction((event) -> {
                            Commentaire cm= (Commentaire) commentairesTv.getItems().get(getIndex());
                            try {
                                if (cs.supprimerCom(cm.getId_c())) {
                                    commentairesTv.getItems().remove(getIndex());
                                    commentairesTv.refresh();

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
