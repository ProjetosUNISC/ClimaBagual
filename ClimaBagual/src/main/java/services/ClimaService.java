package services;

import com.google.gson.*;
import model.Clima.ClimaAtual;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class ClimaService {

    public ClimaAtual buscarClimaAtual(double latitude, double longitude) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" +
                    String.format(Locale.US, "%.6f", latitude) +
                    "&longitude=" +
                    String.format(Locale.US, "%.6f", longitude) +
                    "&current=temperature_2m,wind_speed_10m,weather_code&timezone=auto";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Java ClimaBagual")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject current = json.getAsJsonObject("current");

            if (current == null) {
                System.err.println("❌ 'current' não encontrado na resposta.");
                return null;
            }

            double temperatura = current.get("temperature_2m").getAsDouble();
            double vento = current.get("wind_speed_10m").getAsDouble();
            int codigo = current.get("weather_code").getAsInt();

            return new ClimaAtual(temperatura, vento, interpretarCodigoClima(codigo));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
