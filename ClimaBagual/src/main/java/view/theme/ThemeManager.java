package view.theme;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import view.panel.GradientPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Centraliza a configuração de Look & Feel e temas.
 */
public class ThemeManager {
    /**
     * Inicializa o tema padrão (Light) e aplica fontes e cores globais.
     */
    public static void initTheme() {
        try {

            // Define o Look & Feel FlatLaf Dark
            UIManager.setLookAndFeel(new FlatDarkLaf());

            // Fonte base para todos os componentes
            Font base = new Font("Segoe UI", Font.PLAIN, 14);
            UIManager.put("Label.font", base);
            UIManager.put("Menu.font", base);
            UIManager.put("TitledBorder.titleFont", base.deriveFont(Font.BOLD));
            // Exemplo de cor para títulos de borda
            UIManager.put("TitledBorder.titleColor", new java.awt.Color(0x444444));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyGradientBackground(JFrame frame) {
        // Cria o painel com degradê e preserva o layout principal
        GradientPanel gradient = new GradientPanel();
        gradient.setLayout(frame.getContentPane().getLayout());
        // Transfere os componentes existentes para o GradientPanel
        for (Component c : frame.getContentPane().getComponents()) {
            gradient.add(c);
        }
        frame.setContentPane(gradient);
        frame.invalidate();
        frame.validate();
    }

}
