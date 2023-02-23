package gui;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import services.ProduitService;
import java.lang.Integer;



public class AjouterPdtController  implements Initializable {
    @FXML
    public TextField nomTf;
    @FXML
    public TextField descriptionTf;
    @FXML
    public TextField prixTf;
    @FXML
    public TextField qte_stockTf;
    @FXML
    public TextField id_categorieTf;
    @FXML
    public TextField imageTf;

    // instance database Service
    ProduitService ps = new ProduitService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

        @FXML
        void AjouterProduit (ActionEvent event) {

                Produit p = new Produit();
                p.setNom(nomTf.getText());
                p.setDescription (descriptionTf.getText());
                p.setPrix(Float.valueOf(prixTf.getText()));
                p.setQte_stock(Integer.parseInt(qte_stockTf.getText()));
                p.setId_ctg(Integer.parseInt(prixTf.getText()));
                p.setImage(imageTf.getText());
            try {
                ps.ajouterProduit(p);
               // reset();
            } catch (Exception ex) {
                System.out.println("error" + ex.getMessage());
            }


        }}
