package gui;

import entities.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherUsersController implements Initializable {
    @FXML
    public TableView<User> usersTv;
    @FXML
    public TableColumn<User,Integer> UserIdTv;
    @FXML
    public TableColumn<User,String> usernameTv;
    @FXML
    public TableColumn<User,String> passwordTv;
    @FXML
    public TableColumn<User,String> emailTv;
    @FXML
    public TableColumn<User,String> role;
    @FXML
    private TableColumn<User, Button> supprimer;
    @FXML
    private TableColumn<User, Button> modifier;

    UserService us = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<User> users = us.recuperer();
            ObservableList<User> olu = FXCollections.observableArrayList(users);
            usersTv.setItems(olu);
            UserIdTv.setCellValueFactory(new PropertyValueFactory("userId"));
            usernameTv.setCellValueFactory(new PropertyValueFactory("username"));
            passwordTv.setCellValueFactory(new PropertyValueFactory("email"));
            emailTv.setCellValueFactory(new PropertyValueFactory("password"));
            role.setCellValueFactory(new PropertyValueFactory("role"));

            this.delete();
            this.modifier();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }


    public void delete() {
        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (us.supprimer(usersTv.getItems().get(getIndex()))) {
                                    usersTv.getItems().remove(getIndex());
                                    usersTv.refresh();

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


    public void modifier() {

        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                            b.setOnAction(event -> {
                                try {
                                    TableCell cell = (TableCell) ((Button) event.getSource()).getParent();
                                    int rowIndexBtnmodifier = cell.getIndex();

                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
                                    Parent root = loader.load();

                                    ModifierUserController controller = loader.getController();

                                    controller.setUsername(usernameTv.getCellData(rowIndexBtnmodifier));


                                    usersTv.getScene().setRoot(root);
                                }catch (IOException e){
                                    System.out.println("Erreur lors du chargement de l'interface utilisateur : " + e.getMessage());
                                }

                            });
                        setGraphic(b);

                    }
                }
            };

        });

    }

    @FXML
    void Reclam(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("AjouterReclam.fxml"));
        usersTv.getScene().setRoot(loader);
    }

}
