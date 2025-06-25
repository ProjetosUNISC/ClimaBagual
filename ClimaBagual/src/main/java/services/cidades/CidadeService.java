
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

import dal.Conexao;

public class CidadeService {


/*
  public Coordenada buscarCoordenadas(String nomeCidade) {
    try {
      String cidadeURL = URLEncoder.encode(nomeCidade, StandardCharsets.UTF_8);
      String url = String.format("https://geocoding-api.open-meteo.com/v1/search?name=%s&count=1", cidadeURL);

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("User-Agent", "Java ClimaApp")
              .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

      if (json.has("results")) {
        JsonObject result = json.getAsJsonArray("results").get(0).getAsJsonObject();
        double latitude = result.get("latitude").getAsDouble();
        double longitude = result.get("longitude").getAsDouble();
        return new Coordenada(latitude, longitude);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

      */

}


      
    
    

