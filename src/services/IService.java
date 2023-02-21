package services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    public void ajouter(T t) throws SQLException;

}