
package services;

import entities.Workshop;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WorkshopServices {

    static Connection cnx;

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
            Workshop t = new Workshop(rs.getInt("id"), rs.getInt("duree"), rs.getInt("nbPlaces"), rs.getFloat("prix"), rs.getDate("date"), rs.getString("titre"), rs.getString("nom_artiste"), rs.getString("description"), rs.getString("heure_debut"), rs.getString("heure_fin"), rs.getString("categorie"));

            listEv.add(t);
        }
        return listEv;
    }



    public void ajouterWs(Workshop t) throws SQLException, Exception {

        String sql = "INSERT INTO workshop (duree,nbPlaces,prix,date,titre,nom_artiste,description,heure_debut,heure_fin,categorie) VALUES (?, ?, ?, ?,?, ?, ?, ?,?,?)";
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
        pstmt.setString(10, t.getCategorie());


        pstmt.executeUpdate();
    }



    public boolean  supprimerWs(Workshop t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from workshop where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    public List<Workshop> recuperer() throws SQLException {
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
            p.setCategorie(rs.getString("categorie"));




            workshops.add(p);

        }
        return workshops;
    }



    public void modifierWs(Workshop t) {
        String query = "UPDATE  workshop set titre=? , description=? , duree=?, nom_artiste=? , heure_debut=?, heure_fin=?, prix=?, nbPlaces=?, categorie=?  Where id ='" + t.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, t.getTitre());
            ste.setString(2, t.getDescription());
            ste.setInt(3, t.getDuree());
            ste.setString(4, t.getNom_artiste());
            ste.setString(5, t.getHeure_debut());
            ste.setString(6, t.getHeure_fin());
            ste.setFloat(7, t.getPrix());
            ste.setInt(8, t.getNbPlaces());
            ste.setString(9, t.getCategorie());








            ste.executeUpdate();
            System.out.println("workshop modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static ObservableList<Workshop> assister(String cat){
           ObservableList<Workshop> workshops= FXCollections.observableArrayList();
        try {
            String requete="SELECT * FROM workshop where categorie=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, cat); // Modification de l'index de 0 à 1
            ResultSet result =pst.executeQuery();
            while (result.next()) {
                workshops.add(new Workshop(
                        result.getString("titre"),
                        result.getString("description"),
                        result.getInt("duree"),
                        result.getString("nom_artiste"),
                        result.getString("heure_debut"),
                        result.getString("heure_fin"),
                        result.getInt("prix")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return workshops;
    }

}
