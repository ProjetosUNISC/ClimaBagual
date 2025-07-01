package dal;

import model.Clima.*;

import java.sql.*;

public class ClimaDiaDAO extends EntidadeBaseDAO<ClimaDia> {

    public void inserir(ClimaDia climaDia) throws SQLException {
        String sql = "INSERT INTO clima_dia (cidade_id, data, temp_min, temp_max, descricao, velocidade_vento, precipitacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexao().prepareStatement(sql)) {
            stmt.setInt(1, climaDia.getCidade().getId());
            stmt.setDate(2, Date.valueOf(climaDia.getData()));
            stmt.setDouble(3, climaDia.getTempMin());
            stmt.setDouble(4, climaDia.getTempMax());
            stmt.setString(5, climaDia.getDescricao());
            stmt.setDouble(6, climaDia.getVelocidadeVento());
            stmt.setDouble(7, climaDia.getPrecipitacao());
            stmt.executeUpdate();
        }
    }

    @Override
    public String getNomeTabela() {
        return "clima_dia";
    }

}