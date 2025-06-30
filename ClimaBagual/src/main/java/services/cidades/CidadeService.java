
package services.cidades;


import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dal.*;
import model.*;

public class CidadeService {


    public Coordenada buscarCoordenadas(Cidade cidade) {
        try {
            String nome = cidade.getNome();
            String estado = cidade.getEstado().getSigla();
            String appid = "6cc3e2044c7515ef224b7079e5cb8e0d"; // 🔑 substitui aqui pela chave que você copiou

            String url = String.format(
                    "http://api.openweathermap.org/geo/1.0/direct?q=%s,%s,BR&limit=1&appid=%s",
                    URLEncoder.encode(nome, StandardCharsets.UTF_8),
                    estado,
                    appid
            );


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray array = JsonParser.parseString(response.body()).getAsJsonArray();

            if (!array.isEmpty()) {
                JsonObject obj = array.get(0).getAsJsonObject();
                double lat = obj.get("lat").getAsDouble();
                double lon = obj.get("lon").getAsDouble();
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


        Coordenada coordenada = buscarCoordenadas(cidade);

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


      
    
    

