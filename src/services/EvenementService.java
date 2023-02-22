package services;

import entities.Evenement;
import utils.MyDb;

import java.sql.*;
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
            Evenement e = new Evenement(rs.getInt("id"), rs.getInt("evaluation"), rs.getString("titre"), rs.getString("description"), rs.getString("localisation"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getFloat("prix"), rs.getBytes("image"), rs.getInt("nbPlace"));

            listEv.add(e);
        }
        return listEv;
    }

    public void ajouterEv(Evenement e) throws SQLException, Exception {
        String sDateDebut = "31/12/1998";
        Date dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(sDateDebut);
        //String req="INSERT INTO evenement VALUES(" +
        // "'"+e.getEvaluation()+"','"+e.getTitre()+"','"+e.getDescription()+"','"+e.getLocalisation()+"','"+dateDebut+"','"+dateDebut+"','"+e.getPrix()+"',"+e.getNbPlace()+ ")";
        String sql = "INSERT INTO evenement (evaluation,titre,description,localisation,dateDebut,dateFin,prix,nbPlace) VALUES (?, ?, ?, ?,?, ?, ?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setInt(1, e.getEvaluation());
        pstmt.setString(2, e.getTitre());
        pstmt.setString(3, e.getDescription());
        pstmt.setString(4, e.getLocalisation());
        pstmt.setTimestamp(5, new java.sql.Timestamp(e.getDateDebut().getTime()));
        pstmt.setTimestamp(6, new java.sql.Timestamp(e.getDateFin().getTime()));
        pstmt.setFloat(7, e.getPrix());
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
}



