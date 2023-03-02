
package services;

import entities.Statut;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

public  class StatutService implements IService <Statut> {

    Connection cnx;

    public StatutService() {
        cnx = MyDB.getInstance().getCnx() ;
    }
    public void ajouter (Statut s) throws SQLException, ParseException {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date created = new java.sql.Date(javaDate.getTime());
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  statut (titre,contenu,created,nbrLike) VALUES" +
                "(?, ?, ?,?)");
        statement.setString(1, s.getTitre());
        statement.setString(2, s.getContenu());
        statement.setDate(3,  created);
        statement.setInt(4,  0);
        statement.executeUpdate();
        System.out.println("Ajout réussi !");

    }

    public void ajouterLikeStatut(Statut statut) throws SQLException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("UPDATE statut SET nbrLike = nbrLike+1 WHERE id_s="+statut.getId_s());
        statement.executeUpdate();
        System.out.println("Like réussi !");
    }

//    @Override
//    public void modifier(String a, String b) throws SQLException, ParseException {
//
//        String req = "UPDATE statut SET titre = ?,contenu = ?,updated = ? where id_s ='" + s.getId_s() + "'";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        java.sql.Date updated = Date.valueOf(LocalDate.now());
//
//        ps.setString(1,a);
//        ps.setString(2,b);
//        ps.setDate(3, (Date) s.getUpdated());
//        // ps.setInt(3, c.getId_c());
//        ps.executeUpdate();
//        System.out.println("Le statut a été modifié avec succés !");
//
//    }
//@Override
//  public void modifier(Statut s) throws SQLException, ParseException {
//    String query = "UPDATE  evenement set titre=? , contenu=? Where id_s ='" + s.getId_s() + "'";
//    java.sql.Date updated = Date.valueOf(LocalDate.now());
//    try {
//        PreparedStatement ste = cnx.prepareStatement(query);
//        ste.setString(1, s.getTitre());
//        ste.setString(2, s.getContenu());
//        ste.setDate(1, s.getUpdated());
//
//
//        ste.executeUpdate();
//        System.out.println("Statut modifié  ");
//
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//
//
//
//}

//    @Override
//    public void modifier(String a, String b, String c, String e) throws SQLException, ParseException {
//        Statut s = new Statut();
//       // String req = "UPDATE statut SET titre = ?,tire = ?,contenu,updated = ? where id_s ='" + s.getId_s() + "'";
//        String req = "UPDATE statut SET titre = ?,contenu=? where id_s = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        java.sql.Date updated = Date.valueOf(LocalDate.now());
//
//        ps.setString(1,a);
//        ps.setString(2,b);
//        //ps.setDate(3, (Date) s.getUpdated());
//        // ps.setInt(3, c.getId_c());
//        ps.executeUpdate();
//        System.out.println("Le statut a été modifié avec succés !");
//    }

//        public void modifier(Statut s,String nouveauTitre, String nouveauContenu) throws SQLException, ParseException {
//        if(nouveauTitre != null && !nouveauTitre.equals("") && nouveauContenu != null && !nouveauContenu.equals("")){
//            s.setContenu(nouveauContenu);
//            s.setTitre(nouveauTitre);
//            String req = "UPDATE statut SET titre = ?,contenu = ?,updated = ? where id_s ='" + s.getId_s() + "'";
//            PreparedStatement ps = cnx.prepareStatement(req);
//            java.sql.Date updated= Date.valueOf(LocalDate.now());
//            ps.setString(1, s.getTitre());
//            ps.setString(2, s.getContenu());
//            ps.setDate(3, updated);
//            //ps.setInt(4, s.getId_s());
//            ps.executeUpdate();
//            System.out.println("Le titre et le contenu ont été modifiés avec succés !");
//        }
//      else if (nouveauTitre != null && !nouveauTitre.equals("")){
//          s.setTitre(nouveauTitre);
//          String req = "UPDATE statut SET titre = ? , updated = ? where id_s ='" + s.getId_s() + "'";
//          PreparedStatement ps = cnx.prepareStatement(req);
//          java.sql.Date updated= Date.valueOf(LocalDate.now());
//          ps.setString(1, s.getTitre());
//          ps.setDate(2, updated);
//          //ps.setInt(3, s.getId_s());
//          ps.executeUpdate();
//            System.out.println("Le titre a été modifiés avec succés !");
//
//
//        }
//        else if (nouveauContenu != null && !nouveauContenu.equals("")){
//            s.setContenu(nouveauContenu);
//            String req = "UPDATE statut SET contenu = ? , updated = ? where id_s ='" + s.getId_s() + "'";
//            PreparedStatement ps = cnx.prepareStatement(req);
//            java.sql.Date updated= Date.valueOf(LocalDate.now());
//            ps.setString(1, s.getContenu());
//            ps.setDate(2, updated);
//            //ps.setInt(3, s.getId_s());
//            ps.executeUpdate();
//            System.out.println("Le contenu a été modifiés avec succés !");
//        }
//
//        else {
//            System.out.println("Modification echouée !");
//        }
//
//    }
    public boolean supprimer (int id_s) throws SQLException{
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from statut where id_s = ? ");
            req.setInt(1, id_s);
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
            stt.setNbrLike(rs.getInt("nbrLike"));
            stt.setId_s(rs.getInt("id_s"));
            statuts.add(stt);

        }
        return statuts;

    }
    public Statut rechStatut(int id_s) throws SQLException, ParseException {
        Statut statut = new Statut();
        try {
            Statement stmt = cnx.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM statut  where id_s ="+id_s);
            while(result.next()) {
                statut.setId_s(result.getInt(1));
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
        String p = "SELECT id_s FROM statut where titre='"+titre+"';";
        PreparedStatement pst = cnx.prepareStatement(p);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {id= rs.getInt("id_s");
        }
        return id;
    }



}

