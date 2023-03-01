package services;

import java.sql.SQLException;
import java.util.List;

public interface CService <T>{
    public void ajouter(T t) throws SQLException;
    public void modifier(T t) throws SQLException;
    public void supprimer(T t) throws SQLException;
    public List<T> recuperer(T t) throws SQLException;

}
