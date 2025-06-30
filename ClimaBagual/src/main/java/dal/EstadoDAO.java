package dal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;


public class EstadoDAO extends main.java.dal.EntidadeBaseDAO<Estado> {


        //construtor
    public EstadoDAO() {
        super(rs -> {
            Estado e = new Estado();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            return e;
        });
    }


        //metodo implementado por causa do abstract
    @Override
    public String getNomeTabela() {
        return "estado"; // nome da tabela no banco
    }

        //usado para inserir no banco de dados
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
        //usada para puxar estados do banco
    public List<Estado> listarEstados() {
        List<Estado> lista = new ArrayList<>();
        String sql = "SELECT * FROM estado ORDER BY nome";

        try (ResultSet rs = Conexao.getInstance().consultar(sql)) {
            while (rs.next()) {
                Estado e = new Estado();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setSigla(rs.getString("sigla"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Estado buscarPorId(int id) {
        String sql = "SELECT * FROM estado WHERE id = ?";
        try (PreparedStatement ps = Conexao.getInstance().preparar(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Estado e = new Estado();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setSigla(rs.getString("sigla"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
