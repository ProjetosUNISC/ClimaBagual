package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;


public class EstadoDAO extends EntidadeBaseDAO<Estado>{
    
    @Override
    protected String getNomeTabela() {
        return "estado";
    }

    @Override
    protected Estado ConstruirObjeto(ResultSet rs) throws SQLException {
        Estado e = new Estado();
        e.setId(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        return e;
    }
}
