package dal;


import java.sql.*;
import model.*;


public class EstadoDAO extends EntidadeBaseDAO<Estado>{

    public EstadoDAO() {
        super(rs -> {
            Estado e = new Estado();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            return e;
        });
    }

    @Override
    public String getNomeTabela() {
        return "estado"; // nome da tabela no banco
    }

    public void inserir(Estado e) {
        String sql = "INSERT INTO estado (id, nome) VALUES (?, ?)";
        try {
            PreparedStatement stmt = Conexao.getInstance().preparar(sql);
            stmt.setInt(1, e.getId());
            stmt.setString(2, e.getNome());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
