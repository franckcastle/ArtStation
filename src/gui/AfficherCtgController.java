package gui;

import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.CategorieService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCtgController implements Initializable {

    CategorieService cs = new CategorieService();

    @FXML
    public TableView CategorieTv;
    @FXML
    public TableColumn idCtgTv;
    @FXML
    public TableColumn nomCtgTv;
    @FXML
    private TableColumn<Categorie, Button> modifier;
    @FXML
    public Button ajouter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
            List<Categorie> cat = cs.getAll();

            ObservableList<Categorie> olp = FXCollections.observableArrayList(cat);
            CategorieTv.setItems(olp);
            idCtgTv.setCellValueFactory(new PropertyValueFactory("Id_ctg"));
            nomCtgTv.setCellValueFactory(new PropertyValueFactory("Nom_ctg"));


                this.modifier();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void AjouterCategorie (ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjoutCtg.fxml"));

            //Parent root = loader.load();
            CategorieTv.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifier(){
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction(event -> {
                            try {
                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("ModifierCtg.fxml"));
                                Parent root = loader.load();
                                Categorie cate= (Categorie) CategorieTv.getItems().get(getIndex());
                                ModifierCtgController controller = loader.getController();
                                controller.setCategorie(cate);
                                controller.initialize();
                               /* System.out.println("the product"+prod.getID_produit());
                                ModiffierPdtController controller =loader.getController();
                                controller.p=prod;*/
                                CategorieTv.getScene().setRoot(root);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        setGraphic(b);
                    }
                }
            };

        });
    }
    @FXML
    public void prod(ActionEvent event)throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("AffichagePdt.fxml"));
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
