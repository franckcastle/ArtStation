package services;


import entities.Commentaire;
import utils.MyDB;
import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public  class CommentaireService implements IServiceCom<Commentaire> {

    Connection cnx;

    public CommentaireService() {cnx = MyDB.getInstance().getCnx() ;}
    public void ajouterCom (Commentaire c) throws SQLException {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date date_ajout = new java.sql.Date(javaDate.getTime());
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  commentaire (description) VALUES" +
                "(?)");
        statement.setString(1, c.getDescription());
        statement.executeUpdate();
        System.out.println("l'ajout du commentaire a ete effectué avec succés!");

    }
    public void modifierCom (Commentaire c, String nouvelleDescription) throws SQLException{
        if (nouvelleDescription != null && !nouvelleDescription.equals("")) {
            c.setDescription(nouvelleDescription);


            String req = "UPDATE commentaire SET description = ?,date_ajout = ? where id_s ='" + c.getId_c() + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            java.sql.Date date_ajout = Date.valueOf(LocalDate.now());

            ps.setString(1, c.getDescription());
            ps.setDate(2, (Date) c.getDate_ajout());
            // ps.setInt(3, c.getId_c());
            ps.executeUpdate();
            System.out.println("Le commentaire a été modifié avec succés !");

        }

    }
    public boolean supprimerCom (int id_c) throws SQLException{
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from commentaire where id_c = ? ");
            req.setInt(1, id_c);
            req.executeUpdate();
            ok = true;
            System.out.println("Commentaire supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;

    }


    public List<Commentaire> recupererCom() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String q = "select * from commentaire";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(q);
        while(rs.next()){
            Commentaire cm = new Commentaire();
            cm.setDescription(rs.getString("description"));
            cm.setDate_ajout(rs.getDate("date_ajout"));
            cm.setId_c(rs.getInt("id_c"));
            commentaires.add(cm);

        }
        return commentaires;

    }



}