package services;

import com.google.gson.*;
import model.Clima.IndiceUV;

import java.net.URI;
import java.net.http.*;
import java.util.*;

public class IndiceUVService {

    public List<IndiceUV> buscarIndiceUV(double latitude, double longitude) {
        List<IndiceUV> lista = new ArrayList<>();

        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" +
                    String.format(Locale.US, "%.6f", latitude) +
                    "&longitude=" +
                    String.format(Locale.US, "%.6f", longitude) +
                    "&daily=uv_index_max,uv_index_clear_sky_max&timezone=auto";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Java ClimaBagual")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject daily = root.getAsJsonObject("daily");

            JsonArray datas = daily.getAsJsonArray("time");
            JsonArray uvMax = daily.getAsJsonArray("uv_index_max");
            JsonArray uvLimpo = daily.getAsJsonArray("uv_index_clear_sky_max");

            for (int i = 0; i < datas.size(); i++) {
                lista.add(new IndiceUV(
                        datas.get(i).getAsString(),
                        uvMax.get(i).getAsDouble(),
                        uvLimpo.get(i).getAsDouble()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
