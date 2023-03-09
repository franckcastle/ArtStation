package services;

import entities.Evenement;
import entities.User;
import utils.MyDb;

import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EvenementService {
    Connection cnx;

    public EvenementService() {
        cnx = MyDb.getInstance().getCnx();
    }

    public List<Evenement> getAll() throws SQLException {
        List<Evenement> listEv = new ArrayList<Evenement>();
        String req = "select * from evenement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            //    public Evenement(int id, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, float rating, int ratingNumber, int points, int nbPlace) {
            Evenement e = new Evenement(rs.getInt("id"),  rs.getString("titre"), rs.getString("description"), rs.getString("localisation"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getFloat("prix"),rs.getFloat("rating"),rs.getInt("ratingNumber"),rs.getInt("points"),rs.getString("image"), rs.getInt("nbPlace"));

            listEv.add(e);
        }
        return listEv;
    }

    public void ajouterEv(Evenement e) throws SQLException, Exception {
        String sDateDebut = "31/12/1998";
        Date dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(sDateDebut);
        //String req="INSERT INTO evenement VALUES(" +
        // "'"+e.getEvaluation()+"','"+e.getTitre()+"','"+e.getDescription()+"','"+e.getLocalisation()+"','"+dateDebut+"','"+dateDebut+"','"+e.getPrix()+"',"+e.getNbPlace()+ ")";
        String sql = "INSERT INTO evenement (titre,description,localisation,dateDebut,dateFin,prix,nbPlace) VALUES (?, ?, ?, ?,?, ?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);

        pstmt.setString(1, e.getTitre());
        pstmt.setString(2, e.getDescription());
        pstmt.setString(3, e.getLocalisation());
        pstmt.setTimestamp(4, new Timestamp(e.getDateDebut().getTime()));
        pstmt.setTimestamp(5, new Timestamp(e.getDateFin().getTime()));
        pstmt.setFloat(6, e.getPrix());
        pstmt.setInt(7, e.getNbPlace());
        //Statement st=cnx.createStatement();
        //st.executeUpdate(req);
        pstmt.executeUpdate();
    }
    public void ajouter(Evenement e) throws SQLException, Exception {
        String sDateDebut = "31/12/1998";
        Date dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(sDateDebut);
        //String req="INSERT INTO evenement VALUES(" +
        // "'"+e.getEvaluation()+"','"+e.getTitre()+"','"+e.getDescription()+"','"+e.getLocalisation()+"','"+dateDebut+"','"+dateDebut+"','"+e.getPrix()+"',"+e.getNbPlace()+ ")";
        String sql = "INSERT INTO evenement (titre,description,localisation,dateDebut,dateFin,prix,image,nbPlace) VALUES (?, ?, ?, ?,?, ?,?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);

        pstmt.setString(1, e.getTitre());
        pstmt.setString(2, e.getDescription());
        pstmt.setString(3, e.getLocalisation());
        pstmt.setTimestamp(4, new Timestamp(e.getDateDebut().getTime()));
        pstmt.setTimestamp(5, new Timestamp(e.getDateFin().getTime()));
        pstmt.setFloat(6, e.getPrix());
        pstmt.setString(7,e.getImage());
        pstmt.setInt(8, e.getNbPlace());
        //Statement st=cnx.createStatement();
        //st.executeUpdate(req);
        pstmt.executeUpdate();
    }

    public boolean supprimerEv(Integer id) {
        String sql = "delete from evenement where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


        return false;
    }

    public void modifierEvent(Evenement e) {
        String query = "UPDATE  evenement set titre=? Where id ='" + e.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, e.getTitre());
            ste.executeUpdate();
            System.out.println("eventement modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void rateEvent(int idEvent, int rate) throws SQLException {
        //recuperation de l'evenement :
        String req = "Select * from evenement where id = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,idEvent);
        ResultSet rs = ps.executeQuery();
        Evenement e = new Evenement();
        rs.next();
        if (rs.getRow()!=0){
            e.setId(rs.getInt("id"));
            e.setRating (rs.getInt("rating"));
            e.setPoints(rs.getInt("points"));
            e.setRatingNumber(rs.getInt("ratingNumber"));
        }
        //end recuperation
        int rattingNumber = e.getRatingNumber();
        float eval ;
        if (rattingNumber==0) {
            eval = rate ;
        }else {
            //float eval = (( user.getRating()*rattingNumber)+(rate/(rattingNumber+1))) ;
            eval = ((e.getRating() * rattingNumber )+rate) / (rattingNumber+1) ;

        }
        rattingNumber ++ ;
        int evaluation = Math.round(eval );
        System.out.println("evaluation = " + evaluation);
        e.setRating(evaluation);
        e.setRatingNumber(rattingNumber);
        //Mettre a jour les variable ratting number et rating apres le calcul
        String query = "UPDATE  evenement set rating=?,ratingNumber=?  Where id = '" + e.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setFloat(1, e.getRating());
            ste.setInt(2, e.getRatingNumber());
            ste.executeUpdate();
            System.out.println("eventement modifié dans la base de données ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//
    }
    public Evenement GetByTitre(String titre) throws SQLException {
        List<Evenement> listEv = new ArrayList<>();
        String req = "Select * from evenement where titre = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,titre);
        ResultSet rs = ps.executeQuery();
        Evenement e = new Evenement();
        rs.next();
        //    public Evenement(int id, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, float rating, int ratingNumber, int points, int nbPlace) {

        if (rs.getRow()!=0){
            e.setId(rs.getInt(1));
            e.setRating(rs.getFloat(2));
            e.setTitre(titre);
            e.setLocalisation(rs.getString(4));
            e.setDescription(rs.getString(5));

            e.setDateDebut(rs.getDate(6));
            e.setDateFin(rs.getDate(7));
            e.setPrix(rs.getFloat(8));

            e.setRatingNumber(rs.getInt(9));
            e.setPoints(rs.getInt(10));
            e.setNbPlace(rs.getInt(11));

            return e;
        }
        else {
            System.out.println("Evenement inexistant");
            return null;
        }

    }

}



