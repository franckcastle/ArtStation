package services;
import entities.Reservation_Workshop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Workshop;
import utils.MyDB;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Reservation_WorkshopServices {

    Connection cnx;

    public Reservation_WorkshopServices() {
        cnx = MyDB.getInstance().getCnx();
    }

    public List<Reservation_Workshop> getAll() throws SQLException {
        List<Reservation_Workshop> listEv = new ArrayList<Reservation_Workshop>();
        String req = "select * from reservation_workshop";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Reservation_Workshop t = new Reservation_Workshop(rs.getInt("id"), rs.getInt("id_workshop"), rs.getString("categorie"));
            listEv.add(t);
        }
        return listEv;
    }


    public void ajouterR(Reservation_Workshop t) throws SQLException, Exception {


        String sql = "INSERT INTO reservation_workshop (id_workshop,id_user,categorie) VALUES (?, ?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setInt(1, t.getId_workshop());
        pstmt.setInt(2, t.getId_user());
        pstmt.setString(3, t.getCategorie());

        pstmt.executeUpdate();
    }


    public void supprimerR(Integer id) {
        String sql = "delete from reservation_workshop where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("reservaion supprimé  ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public List<Reservation_Workshop> recupererR(Reservation_Workshop t) throws SQLException {
        List<Reservation_Workshop> reservations = new ArrayList<>();
        String s = "select * from Reservation_Workshop";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reservation_Workshop p = new Reservation_Workshop();
            p.setId_reservation(rs.getInt("id"));
            p.setId_workshop(rs.getInt("id_workshop"));
            p.setId_user(rs.getInt("id_user"));
            p.setCategorie(rs.getString("categorie"));

            reservations.add(p);

        }
        return reservations;
    }

    public void modifierR(Reservation_Workshop t) {
        String query = "UPDATE  reservation_workshop set id_workshop=? Where id ='" + t.getId_reservation() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, t.getId_workshop());
            ste.executeUpdate();
            System.out.println("workshop modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
