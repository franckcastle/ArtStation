package test;

import entities.Commentaire;
import entities.Statut;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.List;


import services.CommentaireService;
import services.StatutService;
import utils.MyDB;
public class Test {
    public static void main(String[] args) throws RuntimeException, SQLException, ParseException {

  //      Commentaire c = new Commentaire("hhh");
        //CommentaireService cs = new CommentaireService();
//        try {
//         //   cs.ajouterCom(c);
//
//         //   String q= cs.recupererComByIdStatut(17).toString();
//            System.out.println(cs.recupererComByIdStatut(17));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//            Statut s1 = new Statut("Musique", "qui est interese par un workshop le 19 mars? ");
//            Statut s2 = new Statut("peinture", "comment vous interpretez la joconde ");
//            Statut s3 = new Statut("Poterie", "Tout se transorme ");
//Statut s4 = new Statut("Guitare", "jeu facile ");
//
//CommentaireService  cs = new CommentaireService();
 //ss.modifier();
////            ss.ajouter(s1);
////            ss.ajouter(s2);
////            ss.ajouter(s3);
 //ss.ajouter(s4);
// ss.recuperer();
 //s4.setId_s(27);
// ss.ajouterLikeStatut(s4);
//           Statut statut = ss.rechStatut(17);
        //  ss.modifier(statut, "ESSAI", "contenu");
        // ss.supprimer(3);
        StatutService ss = new StatutService();
       ss.recupererStatutByTitre("Musique");
    }

    }

