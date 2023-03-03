package services;

import entities.Commentaire;
import entities.Statut;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IServiceCom <Q>{
    public void ajouterCom (Q q) throws SQLException;
    public void modifierCom(Q q) throws SQLException;
    public boolean supprimerCom(int id) throws SQLException;

    public List<Q> recupererCom() throws SQLException;
    public Commentaire rechCom(int id_c) throws SQLException;

    public List<Q> recupererComByIdStatut(int id_s) throws SQLException;

}
