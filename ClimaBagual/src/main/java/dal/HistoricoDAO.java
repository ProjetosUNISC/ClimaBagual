package dal;

import model.Clima.ClimaAtual;
import model.Cidade;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoricoDAO {

    // Mapa para armazenar as cidades já consultadas e seus respectivos históricos
    private static Map<Integer, List<ClimaAtual>> historicoCidadeCache = new HashMap<>();

    // Método para buscar o histórico de clima de uma cidade
    public static List<ClimaAtual> buscarHistorico(Cidade cidade) {
        // Verificar se o histórico da cidade está na memória (cache)
        if (historicoCidadeCache.containsKey(cidade.getId())) {
            return historicoCidadeCache.get(cidade.getId()); // Retorna da memória
        }

        // Caso a cidade não tenha sido consultada antes, buscamos do banco de dados
        List<ClimaAtual> historico = buscarHistoricoDoBanco(cidade);

        // Armazenamos o histórico no cache para futuras consultas
        if (historico != null && !historico.isEmpty()) {
            historicoCidadeCache.put(cidade.getId(), historico);
        }

        return historico; // Retorna os dados obtidos
    }

    // Método para buscar o histórico de clima de uma cidade no banco de dados
    private static List<ClimaAtual> buscarHistoricoDoBanco(Cidade cidade) {
        List<ClimaAtual> historico = new ArrayList<>();

        String sql = "SELECT temperatura, vento, descricao FROM clima_atual WHERE cidade_id = ? ORDER BY data_hora DESC"; // Consulta para buscar dados históricos da cidade

        try (Connection conn = Conexao.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Definir o parâmetro da consulta (ID da cidade)
            ps.setInt(1, cidade.getId());

            // Executa a consulta e obtém o resultado
            ResultSet rs = ps.executeQuery();

            // Itera sobre os resultados e cria objetos ClimaAtual para cada linha
            while (rs.next()) {
                // Criando objeto ClimaAtual com os dados obtidos
                ClimaAtual clima = new ClimaAtual(
                        rs.getDouble("temperatura"),
                        rs.getDouble("vento"),
                        rs.getString("descricao")
                );

                // Adiciona o objeto ClimaAtual à lista de histórico
                historico.add(clima);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historico; // Retorna a lista de ClimaAtual
    }
}
