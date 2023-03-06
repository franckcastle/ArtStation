package gui;

import entities.Workshop;
import entities.Reservation_Workshop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.WorkshopServices;
import services.Reservation_WorkshopServices;
import test.Stat;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    Connection cnx;

    Reservation_WorkshopServices ws = new Reservation_WorkshopServices();
    @FXML
    private TableView tablee;

    @FXML
    public TableColumn id_resField;

    @FXML
    public TableColumn id_workField;

    @FXML
    public TableColumn id_userField;

    @FXML
    public TableColumn cattField;

    @FXML
    private TableColumn delete;
    @FXML
    private Button btnShowStats;

    @FXML
    public void afficherStat(ActionEvent event) {
        try {
            Stat demo = new Stat("Statistiques sur les workshops les plus assistés");
            demo.pack();
            demo.setVisible(true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Reservation_Workshop> w = ws.recupererR();

            ObservableList<Reservation_Workshop> olp = FXCollections.observableArrayList(w);
            tablee.setItems(olp);
            id_resField.setCellValueFactory(new PropertyValueFactory("id_reservation"));
            id_workField.setCellValueFactory(new PropertyValueFactory("id_workshop"));
            id_userField.setCellValueFactory(new PropertyValueFactory("id_user"));
            cattField.setCellValueFactory(new PropertyValueFactory("categorie"));
            this.delete();


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
                                if (ws.supprimerR((Reservation_Workshop) tablee.getItems().get(getIndex()))) {
                                    tablee.getItems().remove(getIndex());
                                    tablee.refresh();

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
