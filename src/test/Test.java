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

        /*Categorie c1 = new Categorie (0,"Art");
        c1.setnom_ctg("essaie");
        c1.setid_ctg(0);
        cs.modifierCategorie(c1);*/
        //cs.ajouterCategorie(c1);
        //cs.supprimerCategorie(1);

         Produit p1 = new Produit( );

        //ps.ajouterProduit(p1);


       /* p1.setNom("zama takhtef");
        p1.setDescription("zama takhtef");
        p1.setPrix(2F);
        p1.setQte_stock(3);
        p1.setID_produit(9);
        ps.modifier(p1.getNom(), p1.getDescription(), p1.getPrix(), p1.getQte_stock());*/
        //  ps.supprimerProduit(13);


    }
}
