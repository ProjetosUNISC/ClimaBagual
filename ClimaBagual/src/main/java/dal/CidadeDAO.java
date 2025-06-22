package dal;


import model.*;
import java.sql.*;


public class CidadeDAO extends EntidadeBaseDAO<Cidade>{

    public CidadeDAO() {
        super(rs -> {
            Cidade c = new Cidade();
            c.setId(rs.getInt("id"));   
            c.setNome(rs.getString("nome"));
            return c;
        });
    }


    @Override
    public String getNomeTabela() {
        return "cidade";
    }


    public void inserir(Cidade c) {
        String sql = "INSERT INTO cidade (id, nome, state_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = Conexao.getInstance().preparar(sql);
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getNome());
            stmt.setInt(3, c.getState_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
