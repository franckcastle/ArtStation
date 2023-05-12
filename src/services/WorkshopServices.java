
package services;

import entities.Workshop;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDb;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WorkshopServices {

    static Connection cnx;

    public WorkshopServices() {
        cnx = MyDb.getInstance().getCnx();
    }

    public List<Workshop> getAll() throws SQLException {
        List<Workshop> listEv = new ArrayList<Workshop>();
        String req = "select * from workshop";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Workshop t = new Workshop(rs.getInt("id"), rs.getInt("duree"), rs.getInt("nb_places	"), rs.getFloat("prix"), rs.getDate("date"), rs.getString("titre"), rs.getString("nom_artiste"), rs.getString("description"), rs.getString("heure_debut"), rs.getString("heure_fin"), rs.getString("categorie" ), rs.getString("image" ));

            listEv.add(t);
        }
        return listEv;
    }



    public void ajouterWs(Workshop t) throws SQLException, Exception {

        String sql = "INSERT INTO workshop (duree,nb_places	,prix,date,titre,nom_artiste,description,heure_debut,heure_fin,categorie,image) VALUES (?, ?, ?, ?,?, ?, ?, ?,?,?,?)";

        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setInt(1, t.getDuree());
        pstmt.setInt(2, t.getNbPlaces		());
        pstmt.setFloat(3, t.getPrix());
        pstmt.setTimestamp(4, new Timestamp(t.getDate().getTime()));
        pstmt.setString(5, t.getTitre());
        pstmt.setString(6, t.getNom_artiste());
        pstmt.setString(7, t.getDescription());
        pstmt.setString(8, t.getHeure_debut());
        pstmt.setString(9, t.getHeure_fin());
        pstmt.setString(10, t.getCategorie());
        pstmt.setString(11, t.getImage());



        pstmt.executeUpdate();
        System.out.println("ajouté avec succes");
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
            p.setNbPlaces		(rs.getInt("nb_places	"));
            p.setPrix(rs.getFloat("prix"));
            p.setDate(rs.getDate("date"));
            p.setCategorie(rs.getString("categorie"));
            p.setImage(rs.getString("image"));





            workshops.add(p);

        }
        return workshops;
    }


  /*  public void modifer(String s1,String s2,String s3,String s4,String s5,int s6,int s7,float s8) throws SQLException {
        String req = "UPDATE workshop SET titre=?,heure_debut=?, heure_fin=?,categorie=?,nom_artiste=?,duree=?,nb_places	=?,prix=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,s1);
        ps.setString(2,s2);
        ps.setString(3,s3);
        ps.setString(4,s4);
        ps.setString(5,s5);
        ps.setInt(6, s6);
        ps.setInt(7, s7);
        ps.setFloat(8, s8);

        ps.executeUpdate();
    }*/
    public void modifierWs(Workshop t) {
        String query = "UPDATE  workshop set titre=? , duree=?, nom_artiste=? , prix=?, nb_places	=?, categorie=?   Where id ='" + t.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, t.getTitre());

            ste.setInt(2, t.getDuree());
            ste.setString(3, t.getNom_artiste());
        //    ste.setString(4, t.getHeure_debut());
         //   ste.setString(5, t.getHeure_fin());
            ste.setFloat(4, t.getPrix());
            ste.setInt(5, t.getNbPlaces());
            ste.setString(6, t.getCategorie());





            ste.executeUpdate();
            System.out.println("workshop modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static ObservableList<Workshop> getIdByCategorie(String cat){
           ObservableList<Workshop> workshops= FXCollections.observableArrayList();

        try {
            String requete="SELECT * FROM workshop where categorie=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, cat); // Modification de l'index de 0 à 1
            ResultSet result =pst.executeQuery();
            while (result.next()) {
                workshops.add(new Workshop(
                        result.getInt("id"),
                        result.getInt("duree"),
                        result.getFloat("prix"),
                        result.getString("titre"),

                        result.getString("nom_artiste"),
                        result.getString("description"),
                        result.getString("heure_debut"),
                        result.getString("heure_fin"),
                        result.getString("image"),
                        result.getDate("date")





                        ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return workshops;
    }

    public List<Integer> getCountByCategory() throws SQLException {
        List<Integer> countList = new ArrayList<>();

        // Prepare a statement to query the count of each category
        String query = "SELECT COUNT(*) from workshop WHERE categorie = ?";
        PreparedStatement statement = cnx.prepareStatement(query);

        // Loop through each category and retrieve its count
        for (String category : Arrays.asList("Musique","Dessin","g")) {
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            countList.add(count);
        }

        // Close the database connection and return the count list
        cnx.close();
        return countList;
    }

    public List<Workshop> getWorkshopsByCategory(String category) throws SQLException {
        List<Workshop> workshops = new ArrayList<>();
        String req = "SELECT * FROM workshop WHERE categorie = ?";
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setString(1, category);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Workshop workshop = new Workshop();
            workshop.setId(rs.getInt("id"));
            workshop.setTitre(rs.getString("titre"));
            workshop.setCategorie(rs.getString("categorie"));
            workshop.setPrix(rs.getFloat("prix"));
            workshops.add(workshop);
        }
        return workshops;
    }

    public List<String> getAllCategories() throws SQLException {
        List<String> categories = new ArrayList<>();
        String req = "SELECT DISTINCT categorie FROM workshop";
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(req);
        while (rs.next()) {
            String category = rs.getString("categorie");
            categories.add(category);
        }
        return categories;
    }




    /*public static int getIdWorkshopByCategorie(String categorie) throws SQLException {
        int idWorkshop = -1;

        String requete = "SELECT id FROM workshop WHERE categorie = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, categorie);
        ResultSet result = pst.executeQuery();

        if (result.next()) {
            idWorkshop = result.getInt("id");
        }

        return idWorkshop;
    }*/

}
