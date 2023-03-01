package test;

import java.sql.SQLException;
import java.util.List;

import entities.Produit;
import services.ProduitService;
import entities.Categorie;
import services.CategorieService;
import utils.MyDb;

public class Test {
    public static void main(String[] args) throws Exception {
        MyDb db= new MyDb();
        ProduitService ps = new ProduitService();
        CategorieService cs = new CategorieService();
        List<Categorie> cat = cs.getAll();
       System.out.println(cat);
        //try{
           //Categorie c1 = new Categorie ("musique");
           //cs.ajouterCategorie(c1);
           // cs.supprimerCategorie(1);

           // Produit p1 = new Produit( "jareb","55 pieces","ght",32.0F,2,3);

            //ps.ajouterProduit(p1);
            //p1.setNom("zama takhtef");
           // p1.setID_produit(16);
            //ps.modifierProduit(p1);
          //  ps.supprimerProduit(13);


    // } catch (SQLException ex) {
    //   System.out.println(ex.getMessage());
   // }
    }
}
