package test;

import entities.Reservation_Workshop;
import entities.Workshop;
import services.Reservation_WorkshopServices;
import services.WorkshopServices;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class Test{
    public static void main(String[] args) throws IOException {

        try {
            Reservation_Workshop p = new Reservation_Workshop(8,10,"hhhh");
            Reservation_WorkshopServices ps = new Reservation_WorkshopServices();
            try {
                ps.ajouterR(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

          //  p.setTitre("quelque chose");
            //p.setId(5);
            ps.modifierR(p);




            // ps.supprimerWs(3);

            System.out.println(ps.recupererR(p));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }




}


