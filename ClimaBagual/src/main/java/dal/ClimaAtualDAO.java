package dal;

import model.Clima.*;
import model.*;
import java.sql.*;

public class ClimaAtualDAO extends EntidadeBaseDAO<ClimaAtual> {

    public void inserir(ClimaAtual clima) throws SQLException {
        String sql = "INSERT INTO clima_atual (cidade_id, temperatura, umidade, descricao, velocidade_vento, indice_uv, precipitacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexao().prepareStatement(sql)) {
            stmt.setInt(1, clima.getCidade().getId());
            stmt.setDouble(2, clima.getTemperatura());
            stmt.setInt(3, clima.getUmidade());
            stmt.setString(4, clima.getDescricao());
            stmt.setDouble(5, clima.getVelocidadeVento());
            stmt.setDouble(6, clima.getIndiceUV());
            stmt.setDouble(7, clima.getPrecipitacao());
            stmt.executeUpdate();
        }
    }

    @Override
    public String getNomeTabela() {
        return "";
    }
}
