package dal;

import com.google.gson.*;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.List;

public class AtualizadorSiglasEstadoDAO {

    public void atualizar() {
        try {
            InputStream is = getClass().getResourceAsStream("/estado-siglas.json");
            InputStreamReader reader = new InputStreamReader(is);

            Gson gson = new Gson();
            List<Map<String, String>> estados = gson.fromJson(reader, List.class);

            Connection conn = Conexao.getInstance().getConexao();

            for (Map<String, String> estado : estados) {
                String nome = estado.get("nome");
                String sigla = estado.get("sigla");

                String sql = "UPDATE estado SET sigla = ? WHERE nome = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, sigla);
                ps.setString(2, nome);
                int atualizado = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
