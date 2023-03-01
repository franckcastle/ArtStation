package gui;

import entities.Produit;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import services.ProduitService;
import services.CategorieService;
import java.lang.Integer;



public class AjouterPdtController  implements Initializable {

    @FXML
    private ComboBox comb;
    @FXML
    public TextField nomTf;
    @FXML
    public TextField descriptionTf;
    @FXML
    public TextField prixTf;
    @FXML
    public TextField qte_stockTf;
    @FXML
    public TextField categorieTf;
    @FXML
    public TextField imageTf;

    // instance database Service
    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Categorie> cat = cs.getAll();
            ObservableList<String> catNames = FXCollections.observableArrayList();
            for (Categorie c : cat) {
                catNames.add(c.getNom_ctg());}
                comb.setItems(catNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        @FXML
      public void AjouterProduit (ActionEvent event) throws SQLException {

            Produit p = new Produit();
            p.setNom(nomTf.getText());
            p.setDescription (descriptionTf.getText());
            p.setPrix(Float.valueOf(prixTf.getText()));
            p.setQte_stock(Integer.parseInt(qte_stockTf.getText()));
            p.setId_ctg(i);
            p.setImage(imageTf.getText());
            try {
                ps.ajouterProduit(p);
                reset();
            } catch (Exception ex) {
                System.out.println("error" + ex.getMessage());
            }}
int i;
            @FXML
            void select (ActionEvent event) throws SQLException {
        String s = comb.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
                List<Categorie> categories = cs.getAll();
                for (Categorie c : categories) {
                    if (c.getNom_ctg().equals(s)) {
                       int categoryId = c.getid_ctg();

                        i=categoryId;
                        break; }
                }

            }

            private void reset() {
                nomTf.setText("");
                descriptionTf.setText("");
                prixTf.setText("");
                qte_stockTf.setText("");
                categorieTf.setText("");
                imageTf.setText("");
            }

}


















