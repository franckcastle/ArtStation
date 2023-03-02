package services;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public void ajouter(T t) throws SQLException;
    public void modifer(String s,String s1,String s2,int s3,String s4,String s5) throws SQLException;
    public boolean supprimer(T t) throws SQLException;
    public List<T> recuperer() throws SQLException;
    public User GetByUsername(String username) throws SQLException;

}
