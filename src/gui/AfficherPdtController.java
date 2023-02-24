package gui;

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
import javafx.stage.Stage;
import services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherPdtController implements Initializable {

    ProduitService ps = new ProduitService();

    @FXML
    private Button AjouterProduit;
    @FXML
    public TableView productsTv;
    @FXML
    public TableColumn idproduitTv;
    @FXML
    public TableColumn nomTv;
    @FXML
    public TableColumn descriptionTv;
    @FXML
    public TableColumn prixTv;
    @FXML
    public TableColumn qte_stockTv;
    @FXML
    public TableColumn categorieTv;
    @FXML
    public TableColumn imageTv;
    @FXML
    private TableColumn<Produit, Button> supprimer;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Produit> listProd = ps.getAll();

            ObservableList<Produit> olp = FXCollections.observableArrayList(listProd);
            productsTv.setItems(olp);
            idproduitTv.setCellValueFactory(new PropertyValueFactory("ID_produit"));
            nomTv.setCellValueFactory(new PropertyValueFactory("nom"));
            descriptionTv.setCellValueFactory(new PropertyValueFactory("description"));
            imageTv.setCellValueFactory(new PropertyValueFactory("image"));
            prixTv.setCellValueFactory(new PropertyValueFactory("prix"));
            qte_stockTv.setCellValueFactory(new PropertyValueFactory("qte_stock"));
            categorieTv.setCellValueFactory(new PropertyValueFactory("id_ctg"));




            this.supprimer();
        } catch (SQLException ex) {

            System.out.println("error" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    private Stage stage;
    private Scene scene;
    @FXML
    public void ajouterProduit (ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterPdt.fxml"));

            //Parent root = loader.load();
            productsTv.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   public void supprimer() {
        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            Produit listProd= (Produit) productsTv.getItems().get(getIndex());
                            if (ps.supprimerProduit(listProd.getID_produit())) {
                                productsTv.getItems().remove(getIndex());
                                productsTv.refresh();

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }
}
