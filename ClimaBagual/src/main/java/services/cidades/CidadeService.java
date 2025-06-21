
package services.cidades;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    private String cidade;
    private String url;    
        



    /*    //declara a api que vai ser usada e como vai ser usada
      public CidadeService(String cidade) throws IOException, InterruptedException {
        this.cidade = cidade;
        this.url = String.format("https://geocoding-api.open-meteo.com/v1/search?name=%s&count=1", cidade);
      }
*/



      /*public void buscarESalvar() {
        double latitude = 0;
        double longitude = 0;
    
        // 1 Requisicao para API
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
                //pega como objeto json atraves do gson
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject result = json.getAsJsonArray("results").get(0).getAsJsonObject();
    
            latitude = result.get("latitude").getAsDouble();
            longitude = result.get("longitude").getAsDouble();
    
        } catch (IOException e) {
            Logger.getLogger(CidadeService.class.getName()).log(Level.SEVERE, "Erro de rede", e);
            JOptionPane.showMessageDialog(null, "Erro de conexão com a internet.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (InterruptedException e) {
            Logger.getLogger(CidadeService.class.getName()).log(Level.SEVERE, "Requisição interrompida", e);
            JOptionPane.showMessageDialog(null, "A operação foi interrompida.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (Exception e) {
            Logger.getLogger(CidadeService.class.getName()).log(Level.SEVERE, "Erro ao processar a resposta da API", e);
            JOptionPane.showMessageDialog(null, "Erro ao processar dados da cidade.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
              // 2 Insercao no banco
        try {
            Connection conn = new Conexao().getConexao();
            String sql = "INSERT INTO cidades (nome, latitude, longitude) VALUES (?, ?, ?)";
    
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cidade);
            stmt.setDouble(2, latitude);
            stmt.setDouble(3, longitude);
    
            stmt.executeUpdate();
            stmt.close();
            conn.close();
    
            JOptionPane.showMessageDialog(null, "Cidade salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (SQLException e) {
            Logger.getLogger(CidadeService.class.getName()).log(Level.SEVERE, "Erro no banco de dados", e);
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados.", "Erro SQL", JOptionPane.ERROR_MESSAGE);
        }
    }*/
      public String getCidade() {
        return cidade;
      }
      public void setCidade(String cidade) {
        this.cidade = cidade;
      }
      public String getUrl() {
        return url;
      }
      public void setUrl(String url) {
        this.url = url;
      }

      

}


      
    
    

