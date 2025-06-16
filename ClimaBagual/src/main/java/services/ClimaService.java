package services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;




public class ClimaService {
  public static void main(String[] args) throws Exception {
      String url = "https://api.open-meteo.com/v1/forecast?latitude=-29.72&longitude=-52.43&hourly=temperature_2m,relative_humidity_2m&daily=temperature_2m_max,temperature_2m_min&timezone=auto";

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      System.out.println("Resposta da API:");
      System.out.println(response.body());
  }
}