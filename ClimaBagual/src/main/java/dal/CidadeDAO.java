package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;


public class CidadeDAO extends EntidadeBaseDAO<Cidade>{
    
    @Override
    protected String getNomeTabela() {
        return "cidade";
    }

    @Override
    protected Cidade ConstruirObjeto(ResultSet rs) throws SQLException {
        Cidade c = new Cidade();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        // Se quiser adicionar estado depois, aqui seria o lugar
        return c;
    }
}
