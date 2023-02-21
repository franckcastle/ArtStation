package services;


import entities.Commentaire;
import utils.MyDB;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CommentaireService implements IServiceCom<Commentaire> {

    Connection cnx;

    public CommentaireService() {cnx = MyDB.getInstance().getcnx ;}
    public void ajouterCom (Commentaire c) throws SQLException {
        String req = "INSERT INTO commentaire (description) VALUES("
                + "'" + c.getDescription() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("l'ajout du commentaire a ete effectué avec succés!");

    }
    public void modifierCom (Commentaire c) throws SQLException{
        String req = "UPDATE commentaire SET description = ?,date_ajout = ? where id_c = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getDescription());
        ps.setDate(2, (Date) c.getDate_ajout());
        ps.setInt(3, c.getId_c());
        ps.executeUpdate();

    }
    public boolean supprimerCom (Commentaire c) throws SQLException{
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from commentaire where id_c = ? ");
            req.setInt(1, c.getId_c());
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