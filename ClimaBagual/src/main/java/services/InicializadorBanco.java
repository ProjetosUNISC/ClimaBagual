package services;



import dal.*;
import model.*;
import services.*;

import javax.swing.*;
import java.util.List;

public class InicializadorBanco {


    public static void verificarOuImportar() {
        EstadoDAO estadoDAO = new EstadoDAO();
        CidadeDAO cidadeDAO = new CidadeDAO();

        if (estadoDAO.estaVazio()) {
            //JOptionPane.showMessageDialog(null,"Banco de dados está vazio.","Aviso",JOptionPane.WARNING_MESSAGE);

            List<Estado> estados = CarregadorJson.carregarEstados();
            List<Cidade> cidades = CarregadorJson.carregarCidades();

            for (Estado e : estados) {
                estadoDAO.inserir(e);
            }

            for (Cidade c : cidades) {
                cidadeDAO.inserir(c);
            }

            new AtualizadorSiglasEstadoDAO().atualizar();
            //JOptionPane.showMessageDialog(null,"Importação finalizada com sucesso.","Aviso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            //JOptionPane.showMessageDialog(null,"Banco já completo.","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
