package services;

import entities.Statut;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IService <P>{
    public void ajouter (P p) throws SQLException, ParseException;
    //public void modifier(String a, String b, String c,String d) throws SQLException, ParseException;
    public boolean supprimer(int id) throws SQLException, ParseException;

    public List<P> recuperer() throws SQLException, ParseException;
    public Statut rechStatut(int id_s) throws SQLException, ParseException;
    public int recupererIdStatut(String titre) throws SQLException,ParseException;

    }
//    @FXML
//    public void handleLikeButtonAction (ActionEvent event) {
//        portfolio selectedPortfolio = produitsTv.getSelectionModel().getSelectedItem() ;
//        if (selectedPortfolio == null) (
//// show error message if no item is selected
//                Alert alert = new Alert (Alert.AlertType.ERROR);
//        alert.setTitle ("Error");
//        alert. setHeaderText ("No Portfolio Selected");
//        alert. setContentText ("please select a portfolio to like.");
//        alert.showAndwait () ;
//    } else (
//        if (lisLiked) (
//        isLiked = true;
//        } else
//        isLiked = false;
//        heartIcon. setImage (new Image (getClass () . getResourceAsStream ("https: //www.flaticon.com/free-icons/heart") ));
//        heartIcon. setImage (new Image (getClass () . getResourceAsStream ("https: //www.flaticon.com/free-icons/heart")