package gui;

import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AfficherEvController implements Initializable {
    EvenementService es = new EvenementService();
    @FXML
    private Button AjouterBtn;
    @FXML
    public TableView EventsTv;
    @FXML
    public TableColumn evaluatinTv;
    @FXML
    public TableColumn titreTv;
    @FXML
    public TableColumn descriptionTv;
    @FXML
    public TableColumn localisationTv;
    @FXML
    public TableColumn dateDebutTv;
    @FXML
    public TableColumn dateFinTv;
    @FXML
    public TableColumn prixTv;
    @FXML
    public TableColumn nbPlaceTv;
    @FXML
    private TableColumn<Evenement, Button> delete;
    @FXML
    private TableColumn<Evenement, Button> modifier;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField keywordsId;
    @FXML
    private TableColumn<Evenement, Button> feedback;

//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Evenement> ev =es.getAll();

            ObservableList<Evenement> olp = FXCollections.observableArrayList(ev);



            EventsTv.setItems(olp);
            //evaluatinTv.setCellValueFactory(new PropertyValueFactory("evaluation"));
            titreTv.setCellValueFactory(new PropertyValueFactory("titre"));
            descriptionTv.setCellValueFactory(new PropertyValueFactory("description"));
            localisationTv.setCellValueFactory(new PropertyValueFactory("localisation"));
            dateDebutTv.setCellValueFactory(new PropertyValueFactory("dateDebut"));
            dateFinTv.setCellValueFactory(new PropertyValueFactory("dateFin"));
            prixTv.setCellValueFactory(new PropertyValueFactory("prix"));
            nbPlaceTv.setCellValueFactory(new PropertyValueFactory("nbPlace"));
            FilteredList<Evenement> filtredData=new FilteredList<>(olp,b ->true);
            keywordsId.textProperty().addListener((observable,oldVlaue,newValue)->{
              filtredData.setPredicate(evenement -> {
                  if(newValue.isEmpty()||newValue.isBlank()||newValue==null){
                      return true;

                  }
                  String searchKeyword=newValue.toLowerCase();
                  if(evenement.getTitre().toLowerCase().indexOf(searchKeyword) > -1){
                      return true;
                  }else if(evenement.getLocalisation().toLowerCase().indexOf(searchKeyword) > -1){return true;}
                  else return false;
              });
            });
            SortedList<Evenement> sortedData=new SortedList<>(filtredData);
            sortedData.comparatorProperty().bind(EventsTv.comparatorProperty());
            EventsTv.setItems(sortedData);


            this.delete();
            this.modifier();
            this.commenter();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    private Stage stage;
    private Scene scene;
    @FXML
    public void ajouterEv(ActionEvent event){
        try {
        Parent loader = FXMLLoader.load(getClass().getResource("AjouterEv.fxml"));

            //Parent root = loader.load();
            EventsTv.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
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
                            Evenement ev= (Evenement) EventsTv.getItems().get(getIndex());
                            if (es.supprimerEv(ev.getId())) {
                                EventsTv.getItems().remove(getIndex());
                                EventsTv.refresh();

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

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

                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("ModiffierEv.fxml"));
                                Parent root = loader.load();

                                //AnchorPane  = loader.load();


                                //AnchorPane pane=loader.load();
                                Evenement even= (Evenement) EventsTv.getItems().get(getIndex());
                                System.out.println("hetha even"+even.getId());
                                ModiffierEvController controller =loader.getController();
                                controller.ee=even;
                                EventsTv.getScene().setRoot(root);

                                //Stage stage = new Stage();
                                //stage.setScene(new Scene(root));
                                //stage.show();
                                //AnchorPane anchorPane = (AnchorPane) root;

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Parent root = loader.load();



                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
    public void commenter(){
        feedback.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("feedback");
                        b.setOnAction(event -> {


                            try {

                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("FeedEv.fxml"));
                                Parent root = loader.load();

                                //AnchorPane  = loader.load();


                                //AnchorPane pane=loader.load();
                                Evenement even= (Evenement) EventsTv.getItems().get(getIndex());
                                System.out.println("hetha even"+even.getId());
                                FeedEvController controller =loader.getController();
                                controller.ee=even;
                                EventsTv.getScene().setRoot(root);

                                //Stage stage = new Stage();
                                //stage.setScene(new Scene(root));
                                //stage.show();
                                //AnchorPane anchorPane = (AnchorPane) root;

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Parent root = loader.load();



                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

}
