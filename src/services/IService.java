package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public void ajouter(T t) throws SQLException;
    public void modifer(T t) throws SQLException;
    public boolean supprimer(T t) throws SQLException;
    public List<T> recuperer() throws SQLException;

}
