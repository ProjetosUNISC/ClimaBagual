
package services.cidades;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dal.*;
import model.*;

public class CidadeService {


    public Coordenada buscarCoordenadas(String nomeCidade) {
        try {
            String cidadeEncoded = URLEncoder.encode(nomeCidade, StandardCharsets.UTF_8);
            String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + cidadeEncoded + "&count=1";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Java ClimaApp")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if (json.has("results")) {
                JsonObject result = json.getAsJsonArray("results").get(0).getAsJsonObject();
                double lat = result.get("latitude").getAsDouble();
                double lon = result.get("longitude").getAsDouble();
                return new Coordenada(lat, lon);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Coordenada buscarOuCarregarCoordenadas(Cidade cidade) {
        if (cidade.getLatitude() != 0 && cidade.getLongitude() != 0) {
            // Já tem dados no banco
            return new Coordenada(cidade.getLatitude(), cidade.getLongitude());
        }

        // Não tem busca na API
        Coordenada coordenada = buscarCoordenadas(cidade.getNome());

        if (coordenada != null) {
            // Salva no banco
            new CidadeDAO().atualizarCoordenadas(cidade.getId(), coordenada);

            // Atualiza o objeto em memória (opcional, mas útil)
            cidade.setLatitude(coordenada.getLatitude());
            cidade.setLongitude(coordenada.getLongitude());
        }

        return coordenada;
    }

}


      
    
    

