
package services;

import entities.Statut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

public abstract class StatutService implements IService <Statut> {

    Connection cnx;

    public StatutService() {cnx = MyDB.getInstance().getcnx ;}
    public void ajouter (Statut s) throws SQLException{
        String req = "INSERT INTO statut (titre,contenu) VALUES("
                + "'" + s.getTitre() + "'," + s.getContenu() +  ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Ajout réussi !");

    }
    public void modifier(Statut s) throws SQLException{
        String req = "UPDATE statut SET titre = ?,contenu = ?,created = ?,updated = ? where id_s = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, s.getTitre());
        ps.setString(2, s.getContenu());
        ps.setDate(3, (Date) s.getUpdated());
        ps.setInt(4, s.getId_s());
        ps.executeUpdate();

    }
    public boolean supprimer (Statut s) throws SQLException{
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from statut where id_s = ? ");
            req.setInt(1, s.getId_s());
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
            stt.setTitre(rs.getString("titre"));
            stt.setContenu(rs.getString("contenu"));
            stt.setCreated(rs.getDate("created"));
            stt.setUpdated(rs.getDate("updated"));
            stt.setId_s(rs.getInt("id_s"));
            statuts.add(stt);

        }
        return statuts;

    }


}

