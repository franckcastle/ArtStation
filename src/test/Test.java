package test;


import entities.Evenement;
import services.EvenementService;
import utils.MyDb;

import java.sql.SQLException;
import java.util.Date;
//TEST
public class Test {
    public static void main(String[] args) throws Exception {


            MyDb db= new MyDb();
        EvenementService es= new EvenementService();


        try {
            Evenement e1= new Evenement(4,"aslema","event","raddes",new java.util.Date(),new java.util.Date(),12.66F,6 ) ;
            es.ajouterEv(e1);
            //Evenement e1=new Evenement();
            //e1.setTitre("haja modifié");
            //e1.setId(2);
            es.supprimerEv(6);
            //es.modifierEvent(e1);
            System.out.println(es.getAll());
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        }

}
