package test;

import java.sql.SQLException;
import entities.Produit;
import services.ProduitService;
import utils.MyDb;

public class Test {
    public static void main(String[] args) throws Exception {
        MyDb db= new MyDb();
        ProduitService ps = new ProduitService();

        try{
            Produit p1 = new Produit( "jareb","55 pieces","ght",32.0F,2,3);

            ps.ajouterProduit(p1);
            p1.setNom("zama takhtef");
            p1.setID_produit(16);
            ps.modifierProduit(p1);
            ps.supprimerProduit(13);


    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }}
