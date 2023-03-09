package services;


import entities.Commentaire;
import entities.Session;
import utils.MyDb;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class CommentaireService implements IServiceCom<Commentaire> {

    Connection cnx;

    public CommentaireService() {cnx = MyDb.getInstance().getCnx() ;}
    public void ajouterCom (Commentaire c) throws SQLException {
        java.util.Date javaDate = new java.util.Date();
      //  Date date_ajout = new Date(javaDate.getTime());
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  commentaire (username,id_s,description) VALUES" +
                "(?,?,?)");
        statement.setString(1, Session.getUserCon().getUsername());

        statement.setString(3, c.getDescription());
        statement.setInt(2, c.getId_s());
        statement.executeUpdate();
        System.out.println("l'ajout du commentaire a ete effectué avec succés!");

    }


    @Override
    public void modifierCom(Commentaire c) throws SQLException {
        String query = "UPDATE  commentaire set description=? Where id_c ='" + c.getId_c() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setString(1, c.getDescription());
            ste.executeUpdate();
            System.out.println("Le commentaire a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
            cm.setUsername(rs.getString("username"));

            cm.setDescription(rs.getString("description"));
            cm.setDate_ajout(rs.getDate("date_ajout"));
            cm.setId_c(rs.getInt("id_c"));
            cm.setId_s(rs.getInt("id_s"));

            commentaires.add(cm);

        }
        return commentaires;

    }

    public Commentaire rechCom(int id_c) throws SQLException {
        Commentaire c = new Commentaire();
        try {
            Statement stmt = cnx.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM commentaire  where id_c ="+id_c);
            while(result.next()) {
                c.setId_c(result.getInt(1));
                c.setDescription(result.getString(2));
                c.setDate_ajout(result.getDate(3));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
    public List<Commentaire> recupererComByIdStatut(int id_s) throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String q = "select * from commentaire where id_s ="+id_s;
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(q);
        while(rs.next()){
            Commentaire cm = new Commentaire();
            cm.setUsername(rs.getString("username"));
            cm.setId_c(rs.getInt("id_c"));
            cm.setId_s(rs.getInt("id_s"));
            cm.setDescription(rs.getString("description"));
            cm.setDate_ajout(rs.getDate("date_ajout"));

            commentaires.add(cm);

        }
        return commentaires;

    }



}