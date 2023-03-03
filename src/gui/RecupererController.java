//package gui;
//
//import entities.Commentaire;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import services.CommentaireService;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import static java.beans.WeakIdentityMap.getIndex;
//
//public class RecupererController implements Initializable {
//    @FXML
//    private TableView<Commentaire> commentairesTv;
//
//    @FXML
//    private TableColumn<Commentaire, String> descriptionTv;
//
//    @FXML
//    private TableColumn<Commentaire,Integer> idTv;
//
//    @FXML
//    private TableColumn<Commentaire,Integer> sidTv;
//
//    CommentaireService cs = new CommentaireService();
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        try {
//            List<Commentaire> cm = cs.recupererComByIdStatut();
//
//            ObservableList<Commentaire> olp = FXCollections.observableArrayList(cm);
//            commentairesTv.setItems(olp).get(getIndex());
//            sidTv.setCellValueFactory(new PropertyValueFactory<>("id_s"));
//            idTv.setCellValueFactory(new PropertyValueFactory<>("id_c"));
//            descriptionTv.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//        } catch (SQLException ex) {
//            System.out.println("Erreur" + ex.getMessage());
//        }
//    }
//}
