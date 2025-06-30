package services;

import com.google.gson.*;
import model.Clima.ClimaDia;

import java.net.URI;
import java.net.http.*;
import java.util.*;

public class ClimaDiarioService {

    public List<ClimaDia> buscarPrevisaoDiaria(double lat, double lon) {
        List<ClimaDia> dias = new ArrayList<>();

        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" +
                    String.format(Locale.US, "%.6f", lat) +
                    "&longitude=" +
                    String.format(Locale.US, "%.6f", lon) +
                    "&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,wind_speed_10m_max" +
                    "&timezone=auto";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Java ClimaBagual")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject daily = root.getAsJsonObject("daily");

            JsonArray datas = daily.getAsJsonArray("time");
            JsonArray codigos = daily.getAsJsonArray("weather_code");
            JsonArray tempMax = daily.getAsJsonArray("temperature_2m_max");
            JsonArray tempMin = daily.getAsJsonArray("temperature_2m_min");
            JsonArray nascer = daily.getAsJsonArray("sunrise");
            JsonArray por = daily.getAsJsonArray("sunset");
            JsonArray vento = daily.getAsJsonArray("wind_speed_10m_max");

            for (int i = 0; i < datas.size(); i++) {
                int codigo = codigos.get(i).getAsInt();
                dias.add(new ClimaDia(
                        datas.get(i).getAsString(),
                        codigo,
                        tempMax.get(i).getAsDouble(),
                        tempMin.get(i).getAsDouble(),
                        nascer.get(i).getAsString().substring(11),
                        por.get(i).getAsString().substring(11),
                        vento.get(i).getAsDouble(),
                        interpretarCodigoClima(codigo)
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dias;
    }

    private String interpretarCodigoClima(int code) {
        return switch (code) {
            case 0 -> "☀️ Céu limpo";
            case 1, 2, 3 -> "⛅ Parcialmente nublado";
            case 45, 48 -> "🌫️ Neblina";
            case 51, 53, 55 -> "🌦️ Garoa";
            case 61, 63, 65 -> "🌧️ Chuva";
            case 71, 73, 75 -> "❄️ Neve";
            case 80, 81, 82 -> "🌩️ Chuva forte";
            case 95, 96, 99 -> "🌩️⛈️ Tempestade";
            default -> "❓ Desconhecido";
        };
    }
}
