package test;

import entities.Statut;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.List;


import services.StatutService;
import utils.MyDB;
public class Test {
    public static void main(String[] args) {
        try {
            Statut s1 = new Statut("Musique", "qui est interese par un workshop le 19 mars? ");
            Statut s2 = new Statut("peinture", "comment vous interpretez la joconde ");
            Statut s3 = new Statut("Poterie", "Tout se transorme ");
            Statut s4 = new Statut("Guitare", "jeu facile ");

            StatutService ss = new StatutService();
//            ss.ajouter(s1);
//            ss.ajouter(s2);
//            ss.ajouter(s3);
//            ss.ajouter(s4);
           Statut statut = ss.rechStatut(17);
        //  ss.modifier(statut, "ESSAI", "contenu");
          // ss.supprimer(3);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}

