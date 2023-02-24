package gui;

import entities.Statut;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import services.StatutService;

import java.net.URL;
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

    StatutService s = new StatutService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Statut> stt =s.recuperer();

            ObservableList<Statut> olp = FXCollections.observableArrayList(stt);
            statutsTv.setItems(olp);
            idTv.setCellValueFactory(new PropertyValueFactory("id_s"));
            titreTv.setCellValueFactory(new PropertyValueFactory("titre"));
            contenuTv.setCellValueFactory(new PropertyValueFactory("contenu"));
            createdTv.setCellValueFactory(new PropertyValueFactory("created"));
            updatedTv.setCellValueFactory(new PropertyValueFactory("updated"));


            this.supprimer();
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }
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
