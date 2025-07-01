package dal;


import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CidadeDAO extends EntidadeBaseDAO<Cidade> {

    public CidadeDAO() {
        super(rs -> {
            Cidade c = new model.Cidade();
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
            Estado estado = new EstadoDAO().buscarPorId(idEstado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cidade c = new Cidade();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setState_id(rs.getInt("state_id"));
                c.setState_id(idEstado);
                c.setEstado(estado);
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizarCoordenadas(int idCidade, Coordenada coord) {
        String sql = "UPDATE cidade SET latitude = ?, longitude = ? WHERE id = ?";
        try (PreparedStatement ps = Conexao.getInstance().preparar(sql)) {
            ps.setDouble(1, coord.getLatitude());
            ps.setDouble(2, coord.getLongitude());
            ps.setInt(3, idCidade);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
