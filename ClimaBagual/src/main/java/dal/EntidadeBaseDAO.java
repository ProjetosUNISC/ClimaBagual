package dal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.EntidadeBase;

import java.sql.*;

import model.*;

public abstract class EntidadeBaseDAO<T extends EntidadeBase> {


    //instancia as variaves
    public abstract String getNomeTabela();

    private ConstruirObjeto<T> objeto;


    public EntidadeBaseDAO(ConstruirObjeto<T> objeto) {
        this.objeto = objeto;
    }

        //pawra verificar se o banco ou tabela está vazia
    public boolean estaVazio() {
        String sql = "SELECT COUNT(*) FROM " + getNomeTabela();
        try {
            ResultSet rs = Conexao.getInstance().consultar(sql);
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; // assume vazio se erro
    }


    //função para retirar dados do banco
    public List<T> consultar() {
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getNomeTabela() + " ORDER BY nome";

        try {
            ResultSet rs = Conexao.getInstance().consultar(sql);
            while (rs.next()) {

                T objetoMontado = objeto.construir(rs);
                lista.add(objetoMontado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }


}
