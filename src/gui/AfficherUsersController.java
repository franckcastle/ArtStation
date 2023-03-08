package gui;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficherUsersController implements Initializable {
    UserService us = new UserService();
    @FXML
    private TextField emailTf;

    @FXML
    private TextField passwordTf;

    @FXML
    private ChoiceBox<String> roleTf;

    @FXML
    private TextField telTf;

    @FXML
    private TextField usernameTf;

    @FXML
    private ListView<User> usersLv;

    private final String[] roles = {"Admin","Artiste","Client"};


    @FXML
    void ModifierUser(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        String username=usernameTf.getText();
        String password = passwordTf.getText();
        String email = emailTf.getText();
        int tel = Integer.parseInt(telTf.getText());
        String role = roleTf.getValue();
        us.modifer(username,password,email,tel,role,username);
        usersLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<User> observableUserList = FXCollections.observableList(us.recuperer());
        usersLv.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        usersLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        usernameTf.clear();
        emailTf.clear();
        passwordTf.clear();
        telTf.clear();
        roleTf.getSelectionModel().clearSelection();

    }

    @FXML
    void Reclam(ActionEvent event) throws IOException {
        Parent loader = null;
        loader = FXMLLoader.load(getClass().getResource("AfficherReclam.fxml"));
        emailTf.getScene().setRoot(loader);

    }

    @FXML
    void SupprimerUser(ActionEvent event) throws SQLException{
            String username=usernameTf.getText();

            us.supprimer(us.GetByUsername(username));
            usersLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<User> observableUserList = FXCollections.observableList(us.recuperer());
        usersLv.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        usersLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        usernameTf.clear();
        emailTf.clear();
        passwordTf.clear();
        telTf.clear();
        roleTf.getSelectionModel().clearSelection();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        roleTf.getItems().addAll(roles);
        try {

            ObservableList<User> observableUserList = FXCollections.observableList(us.recuperer());
            usersLv.setItems(observableUserList);
            usersLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            usersLv.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
                @Override
                public ListCell<User> call(ListView<User> listView) {
                    return new ListCell<User>() {
                        @Override
                        protected void updateItem(User user, boolean empty) {
                            super.updateItem(user, empty);
                            if (user != null) {
                                setText(user.getUsername() + " - " +user.getEmail()+ " - " + user.getRole());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            usersLv.setOnMouseClicked(event -> {
                User userSelectionne = usersLv.getSelectionModel().getSelectedItem();
                if (userSelectionne != null) {

                    usernameTf.setText(userSelectionne.getUsername());
                    emailTf.setText(userSelectionne.getEmail());
                    passwordTf.setText(userSelectionne.getPassword());

                    emailTf.setText(userSelectionne.getEmail());
                    telTf.setText(Integer.toString(userSelectionne.getTel()));
                    SingleSelectionModel<String> selectionModel = roleTf.getSelectionModel();
                    selectionModel.select(userSelectionne.getRole());

                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
