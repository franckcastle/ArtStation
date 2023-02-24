
package services;

import entities.Workshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WorkshopServices {

    Connection cnx;

    public WorkshopServices() {
        cnx = MyDB.getInstance().getCnx();
    }

    public List<Workshop> getAll() throws SQLException {
        List<Workshop> listEv = new ArrayList<Workshop>();
        String req = "select * from workshop";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Workshop t = new Workshop(rs.getInt("id"), rs.getInt("duree"), rs.getInt("nbPlaces"), rs.getFloat("prix"), rs.getDate("date"), rs.getString("titre"), rs.getString("nom_artiste"), rs.getString("description"), rs.getString("heure_debut"), rs.getString("heure_fin"));

            listEv.add(t);
        }
        return listEv;
    }



    public void ajouterWs(Workshop t) throws SQLException, Exception {
        String sDate = "31/12/1998";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

        String sql = "INSERT INTO workshop (duree,nbPlaces,prix,date,titre,nom_artiste,description,heure_debut,heure_fin) VALUES (?, ?, ?, ?,?, ?, ?, ?,?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setInt(1, t.getDuree());
        pstmt.setInt(2, t.getNbPlaces());
        pstmt.setFloat(3, t.getPrix());
        pstmt.setTimestamp(4, new java.sql.Timestamp(t.getDate().getTime()));
        pstmt.setString(5, t.getTitre());
        pstmt.setString(6, t.getNom_artiste());
        pstmt.setString(7, t.getDescription());
        pstmt.setString(8, t.getHeure_debut());
        pstmt.setString(9, t.getHeure_fin());


        pstmt.executeUpdate();
    }


    public void supprimerWs(Integer id) {
        String sql = "delete from workshop where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("workshop supprimé  ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public List<Workshop> recuperer(Workshop t) throws SQLException {
       List<Workshop> workshops = new ArrayList<>();
        String s = "select * from Workshop";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Workshop p = new Workshop();
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("description"));
            p.setNom_artiste(rs.getString("nom_artiste"));
            p.setHeure_debut(rs.getString("heure_debut"));
            p.setHeure_fin(rs.getString("heure_fin"));
            p.setDuree(rs.getInt("duree"));
            p.setId(rs.getInt("id"));
            p.setNbPlaces(rs.getInt("nbPlaces"));
            p.setPrix(rs.getFloat("prix"));
            p.setDate(rs.getDate("date"));





            workshops.add(p);

        }
        return workshops;
    }



    public void modifierWs(Workshop t) {
        String query = "UPDATE  workshop set titre=? Where id ='" + t.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, t.getTitre());
            ste.executeUpdate();
            System.out.println("workshop modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
