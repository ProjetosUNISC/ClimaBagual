package model;

import java.sql.ResultSet;
import java.sql.SQLException;

    
public interface ConstruirObjeto<T> {
    T construir(ResultSet rs) throws SQLException;
}
    
