package test;

import entities.Workshop;
import services.WorkshopServices;

import java.io.IOException;
import java.sql.SQLException;



public class Test{





    public static void main(String[] args) throws IOException {

        try {
            Workshop p = new Workshop(3, 20, 45, new java.util.Date(), "guitare", "dave", "bch yaalamkom guitare", "20h", "22h");
            WorkshopServices ps = new WorkshopServices();
            try {
                ps.ajouterWs(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ps.modifierWs(p);
           ps.supprimerWs(2);
            System.out.println(ps.recuperer(p));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }




}

