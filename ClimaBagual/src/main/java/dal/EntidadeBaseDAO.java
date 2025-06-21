package dal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EntidadeBase;
import java.sql.*;
import model.*;

public class EntidadeBaseDAO <T extends EntidadeBase> {
    
    
        //instancia as variaves
    public abstract String getNomeTabela();
    private ObjetoFactory<T> objeto;   
    
    
    //função para retirar dados do banco
    public List<T> consultar() {
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getNomeTabela() + " ORDER BY nome";

        try {
            ResultSet rs = Conexao.getInstance().consultar(sql);
            while (rs.next()) {
                T obj = ConstruirObjeto(rs);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    
    
    
}
