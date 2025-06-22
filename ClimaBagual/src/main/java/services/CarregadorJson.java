package services;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import model.Estado;
import model.Cidade;

public class CarregadorJson {



    public static class EstadosCidades {
            //guarda id e nome estado
        private Map<String, String> states;
            //guarda cidade
        private List<Cidade> cities;
            //tranforma em dicionarios
        public Map<String, String> getStates() {
            return states; }
            //transforma em lista
        public List<Cidade> getCities() {
            return cities; }
    }

        //funcao para carregar estados
    public static List<Estado> carregarEstados() {

        List<Estado> lista = new ArrayList<>();
            //faz a leitura do arquivo
        try (InputStream arquivo = CarregadorJson.class.getResourceAsStream("/estados-cidades.json");
             InputStreamReader leitura = new InputStreamReader(arquivo)) {
                //usa a biblioteca gson para a leitura de json
            Gson gson = new Gson();
            EstadosCidades estadosCidades = gson.fromJson(leitura, EstadosCidades.class);
            
            
                //loop para percorrer e preencher os estados
            for (Map.Entry<String, String> entrada : estadosCidades.getStates().entrySet()) {
                int id = Integer.parseInt(entrada.getKey());
                String nome = entrada.getValue();
                lista.add(new Estado(id, nome));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

        //funcao para carregador cidade
    public static List<Cidade> carregarCidades() {
        
        try (InputStream arquivo = CarregadorJson.class.getResourceAsStream("/estados-cidades.json");
             InputStreamReader leitura = new InputStreamReader(arquivo)) {

            Gson gson = new Gson();
            EstadosCidades estadosCidades = gson.fromJson(leitura, EstadosCidades.class);
            
            return estadosCidades.getCities();
        } catch (Exception e) {
            e.printStackTrace();
            
            //caso de erro volta vazio
            return Collections.emptyList();
        }
    }
}