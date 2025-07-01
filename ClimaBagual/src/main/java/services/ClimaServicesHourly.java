package services;

import com.google.gson.*;
import dal.*;
import model.Clima.ClimaDia;
import model.Clima.ClimaHora;
import model.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClimaServicesHourly {

    public List<ClimaHora> buscarPrevisaoHoraria(double lat, double lon, Cidade cidade) {

        List<ClimaHora> previsoes = new ArrayList<>();

        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" +
                    String.format(Locale.US, "%.6f", lat) +
                    "&longitude=" +
                    String.format(Locale.US, "%.6f", lon) +
                    "&hourly=temperature_2m,wind_speed_10m,weather_code&timezone=auto";


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Java ClimaBagual")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject hourly = json.getAsJsonObject("hourly");

            JsonArray times = hourly.getAsJsonArray("time");
            JsonArray temperatures = hourly.getAsJsonArray("temperature_2m");
            JsonArray winds = hourly.getAsJsonArray("wind_speed_10m");
            JsonArray codes = hourly.getAsJsonArray("weather_code");

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            ClimaHoraDAO dao = new ClimaHoraDAO();

            for (int i = 0; i < times.size(); i++) {
                String timeStr = times.get(i).getAsString();
                LocalDateTime hora = LocalDateTime.parse(timeStr, formatter);

                double temp = temperatures.get(i).getAsDouble();
                double vento = winds.get(i).getAsDouble();
                int codigo = codes.get(i).getAsInt();

                String descricao = interpretarCodigoClima(codigo);

                ClimaHora climaHora = new ClimaHora(hora, temp, vento, descricao);
                climaHora.setCidade(cidade);

                dao.inserir(climaHora); // <-- grava no banco
                previsoes.add(climaHora);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return previsoes;
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
