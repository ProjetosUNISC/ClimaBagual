package dal;

import dal.*;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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


        //usada para inserir cidade do json no banco
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

        //usada para puxar do banco para o front, se basea no id do estado
        public List<Cidade> listarPorEstado(int idEstado) {

            List<Cidade> lista = new ArrayList<>();
            String sql = "SELECT * FROM cidade WHERE state_id = ? ORDER BY nome";

            try {
                PreparedStatement ps = Conexao.getInstance().getConexao().prepareStatement(sql);
                ps.setInt(1, idEstado);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Cidade c = new Cidade();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setState_id(rs.getInt("state_id"));
                    lista.add(c);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return lista;
        }


}
