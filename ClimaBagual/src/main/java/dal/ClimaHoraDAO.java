package dal;

import model.Clima.*;

import java.sql.*;

public class ClimaHoraDAO extends EntidadeBaseDAO<ClimaHora> {

    public void inserir(ClimaHora climaHora) throws SQLException {
        String sql = "INSERT INTO clima_hora (cidade_id, data_hora, temperatura, umidade, descricao, velocidade_vento, indice_uv, precipitacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexao().prepareStatement(sql)) {
            stmt.setInt(1, climaHora.getCidade().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(climaHora.getDataHora()));
            stmt.setDouble(3, climaHora.getTemperatura());
            stmt.setInt(4, climaHora.getUmidade());
            stmt.setString(5, climaHora.getDescricao());
            stmt.setDouble(6, climaHora.getVelocidadeVento());
            stmt.setDouble(7, climaHora.getIndiceUV());
            stmt.setDouble(8, climaHora.getPrecipitacao());
            stmt.executeUpdate();
        }
    }

    @Override
    public String getNomeTabela() {
        return "";
    }

    // outros métodos de consulta podem ser adicionados aqui
}
