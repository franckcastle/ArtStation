package services;

import entities.Evenement;
import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public void ajouter(T t) throws SQLException;
    void modifer(String s,String s1,String s2,int s3,String s4,String s5) throws SQLException;
    public boolean supprimer(T t) throws SQLException;
    public List<T> recuperer() throws SQLException;
    public User GetByUsername(String username) throws SQLException;
    List<User> GetByRole(String role) throws SQLException;
    public boolean participerEv(int id_u, Evenement e) throws SQLException;
    public boolean annulerRes(int id_u, Evenement e) throws SQLException;

}
