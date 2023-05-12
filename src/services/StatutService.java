package services;

import entities.Session;
import entities.Statut;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import utils.MyDb;


public  class StatutService implements IServiceStatut<Statut> {

    Connection cnx;

    public StatutService() {
        cnx = MyDb.getInstance().getCnx() ;
    }
    public void ajouter (Statut s) throws SQLException, ParseException {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date created = new java.sql.Date(javaDate.getTime());
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  statut (username,titre,contenu,created,nbr_like) VALUES" +
                "(?, ?, ?,?,?)");
        statement.setString(1, Session.getUserCon().getUsername());

        statement.setString(2, s.getTitre());
        statement.setString(3, s.getContenu());
        statement.setDate(4,  created);
        statement.setInt(5,  0);
        statement.executeUpdate();
        System.out.println("Ajout réussi !");

    }

    public void ajouterLikeStatut(Statut statut) throws SQLException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("UPDATE statut SET nbr_like = nbr_like+1 WHERE id="+statut.getId());
        statement.executeUpdate();
        System.out.println("Like réussi !");
    }
    public void supprimerLikeStatut(Statut statut) throws SQLException {

        PreparedStatement statement;
        statement = cnx.prepareStatement("UPDATE statut SET nbr_like = nbr_like-1  WHERE id=" + statut.getId());
        statement.executeUpdate();


        System.out.println("Like est supprimé !");
    }


    @Override
    public void modifier(Statut s) throws SQLException {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date updated = new java.sql.Date(javaDate.getTime());
        String query = "UPDATE  statut set titre=?,contenu=? ,updated=? Where id ='" + s.getId() + "'";
        //   java.sql.Date updated = Date.valueOf(LocalDate.now());
        try {
            PreparedStatement ste = cnx.prepareStatement(query);


            ste.setString(1, s.getTitre());
            ste.setString(2, s.getContenu());
            ste.setDate(3, updated);

            ste.executeUpdate();
            System.out.println("Le statut a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean supprimer (int id) throws SQLException{
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from statut where id = ? ");
            req.setInt(1, id);
            req.executeUpdate();
            ok = true;
            System.out.println("Statut supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;

    }


    public List<Statut> recuperer() throws SQLException {
        List<Statut> statuts = new ArrayList<>();
        String p = "select * from statut";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(p);
        while(rs.next()){
            Statut stt = new Statut();
            stt.setUsername(rs.getString("username"));

            stt.setTitre(rs.getString("titre"));
            stt.setContenu(rs.getString("contenu"));
            stt.setCreated(rs.getDate("created"));
            stt.setUpdated(rs.getDate("updated"));
            stt.setNbr_like(rs.getInt("nbr_like"));
            stt.setId(rs.getInt("id"));
            statuts.add(stt);

        }
        return statuts;

    }
    public Statut rechStatut(int id) throws SQLException, ParseException {
        Statut statut = new Statut();
        try {
            Statement stmt = cnx.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM statut  where id ="+id);
            while(result.next()) {
                statut.setId(result.getInt(1));
                statut.setTitre(result.getString(2));
                statut.setContenu(result.getString(3));
                statut.setCreated(result.getDate(4));
                statut.setUpdated(result.getDate(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return statut;
    }

    @Override
    public int recupererIdStatut(String titre) throws SQLException, ParseException {
        int id=0;
        String p = "SELECT id FROM statut where titre='"+titre+"';";
        PreparedStatement pst = cnx.prepareStatement(p);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {id= rs.getInt("id");
        }
        return id;
    }


    @Override
    public Statut recupererStatutByTitre(String titre) throws SQLException {
        List<Statut> stat = new ArrayList<>();
        String req = "Select * from statut where titre = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,titre);
        ResultSet rs = ps.executeQuery();
        Statut s = new Statut();
        rs.next();
        if (rs.getRow()!=0){
            s.setId(rs.getInt(1));
            s.setTitre(titre);
            s.setContenu(rs.getString(3));


            return s;
        }
        else {
            System.out.println("Statut inexistant !");
            return null;
        }

    }
    public List<Statut> getByNbrLike() throws SQLException {
        List<Statut> statuts = recuperer();
        Collections.sort(statuts, Comparator.comparing(Statut::getNbr_like));
        return statuts;
    }
    public List<Statut> getByTitre() throws SQLException {
        List<Statut> statuts = recuperer();
        Collections.sort(statuts, Comparator.comparing(Statut::getTitre));
        return statuts;
    }

}