package gui;
import entities.Remise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.RemiseService;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficherRemise implements Initializable {

    @FXML
    private TextField code;

    @FXML
    private TextField nb;

    @FXML
    private TextField owner;

    @FXML
    private ListView<Remise> remiseTable;

    @FXML
    void ModifierRemise(ActionEvent event) throws SQLException {
        int Code=Integer.parseInt(code.getText());
        String Owner = owner.getText();
        int Nb = Integer.parseInt(nb.getText());
       Remise r =new Remise();
       r.setCode(Code);
       r.setOwner(Owner);
       r.setNb(Nb);
       rs.modifier(r);
        remiseTable.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Remise> observableUserList = FXCollections.observableList(rs.recuperer());
       remiseTable.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
       remiseTable.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        code.clear();
       owner.clear();
        nb.clear();

    }

    @FXML
    void SupprimerRemise(ActionEvent event) throws SQLException {
        int codet=Integer.parseInt(code.getText());

        rs.supprimer(codet);
        remiseTable.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Remise> observableUserList = FXCollections.observableList(rs.recuperer());
        remiseTable.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
       remiseTable.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        code.clear();
       owner.clear();
       nb.clear();


    }

    @FXML
    void order(ActionEvent event) throws IOException {
        Parent loader = null;
        loader = FXMLLoader.load(getClass().getResource("AfficherOrder.fxml"));
       remiseTable.getScene().setRoot(loader);

    }



    RemiseService rs =new RemiseService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            ObservableList<Remise> observableUserList = FXCollections.observableList(rs.recuperer());
           remiseTable.setItems(observableUserList);
            remiseTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            remiseTable.setCellFactory(new Callback<ListView<Remise>, ListCell<Remise>>() {
                @Override
                public ListCell<Remise> call(ListView<Remise> listView) {
                    return new ListCell<Remise>() {
                        @Override
                        protected void updateItem(Remise remise, boolean empty) {
                            super.updateItem(remise, empty);
                            if (remise != null) {
                                setText(remise.getCode() + " - " +remise.getOwner()+ " - " + remise.getNb());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            remiseTable.setOnMouseClicked(event -> {
                Remise userSelectionne = remiseTable.getSelectionModel().getSelectedItem();
                if (userSelectionne != null) {

                    code.setText(Integer.toString(userSelectionne.getCode()));
                    owner.setText(userSelectionne.getOwner());
                   nb.setText(Integer.toString(userSelectionne.getNb()));



                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


    public void AjouterRemise(ActionEvent actionEvent) throws SQLException {

       Remise r =new Remise();
        r.setCode(Integer.parseInt(code.getText()));
        r.setOwner(owner.getText());



        rs.ajouter(r);
        remiseTable.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Remise> observableUserList = FXCollections.observableList(rs.recuperer());
        remiseTable.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        remiseTable.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        code.clear();
        owner.clear();
        nb.clear();

    }
    @FXML
    public void prod(ActionEvent event)throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
    @FXML
    public void panier(ActionEvent event)throws IOException{

        AnchorPane root = FXMLLoader.load(getClass().getResource("OrderAffiche.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
    @FXML
    public void workshop(ActionEvent event)throws IOException{

        AnchorPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminWs.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
    @FXML
    public void even(ActionEvent event)throws IOException{

        AnchorPane root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
    @FXML
    public void forum(ActionEvent event)throws IOException{

        BorderPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminForum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
    @FXML
    public void categorie(ActionEvent event)throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("AfficherCtg.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(750 );
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }
}