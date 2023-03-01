package services;

import entities.Statut;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IService <P>{
    public void ajouter (P p) throws SQLException, ParseException;
    public void modifier(String a , String b, Date d1,Date d2) throws SQLException, ParseException;
    public boolean supprimer(int id) throws SQLException, ParseException;

    public List<P> recuperer() throws SQLException, ParseException;
    public Statut rechStatut(int id_s) throws SQLException, ParseException;
    public int recupererIdStatut(String titre) throws SQLException,ParseException;

    }
