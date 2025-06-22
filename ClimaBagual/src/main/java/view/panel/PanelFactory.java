package main.java.view.panel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class PanelFactory {
    /**
     * Cria um JPanel com BorderLayout, envolvido em um TitledBorder
     * e já coloca o area dentro de um JScrollPane.
     */

    public static JPanel criarPainelComBorda(String titulo, JTextArea area) {
        JPanel painel = new JPanel(new BorderLayout());

        // cria o TitledBorder com o título e cor customizada
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),  // borda externa
                titulo,                                         // texto do título
                TitledBorder.DEFAULT_JUSTIFICATION,             // alinhamento do texto
                TitledBorder.DEFAULT_POSITION,                  // posição do texto
                painel.getFont(),                               // fonte usada
                Color.LIGHT_GRAY                                // cor do título
        );
        painel.setBorder(border);

        // adiciona a área de texto dentro de um scroll pane
        painel.add(new JScrollPane(area), BorderLayout.CENTER);
        return painel;
    }
}