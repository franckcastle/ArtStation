package services;
import entities.Statut;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IServiceStatut<P> {
    public void ajouter (P p) throws SQLException, ParseException;
    public void modifier(P p) throws SQLException, ParseException;
    public boolean supprimer(int id) throws SQLException, ParseException;

    public List<P> recuperer() throws SQLException, ParseException;
    public Statut rechStatut(int id_s) throws SQLException, ParseException;
    public int recupererIdStatut(String titre) throws SQLException,ParseException;
    public void ajouterLikeStatut(P p) throws SQLException;
    public void supprimerLikeStatut(P p) throws SQLException;



    Statut recupererStatutByTitre(String titre) throws SQLException;
    public List<Statut> getByNbrLike() throws SQLException;
    public List<Statut> getByTitre() throws SQLException;
}