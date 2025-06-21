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
        private Map<String, String> estados;
            //guarda cidade
        private List<Cidade> cidades;

            //tranforma em dicionarios
        public Map<String, String> getEstados() { 
            return estados; }
        
            //transforma em lista
        public List<Cidade> getCidades() { 
            return cidades; }
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
            for (Map.Entry<String, String> entrada : estadosCidades.getEstados().entrySet()) {
                int id = Integer.parseInt(entrada.getKey());
                String nome = entrada.getValue();
                lista.add(new Estado(id, nome));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static List<Cidade> carregarCidades() {
        
        try (InputStream arquivo = CarregadorJson.class.getResourceAsStream("/estados-cidades.json");
             InputStreamReader leitura = new InputStreamReader(arquivo)) {

            Gson gson = new Gson();
            EstadosCidades estadosCidades = gson.fromJson(leitura, EstadosCidades.class);
            
            return estadosCidades.getCidades();
        } catch (Exception e) {
            e.printStackTrace();
            
            //caso de erro volta vazio
            return Collections.emptyList();
        }
    }
}