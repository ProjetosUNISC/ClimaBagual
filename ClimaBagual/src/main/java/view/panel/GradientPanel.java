package view.panel;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    public GradientPanel() {
        setOpaque(true); // garante que pintamos o fundo totalmente
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 1) gradiente
        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth(), h = getHeight();
        g2.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, h, new Color(0xEAEAEA)));
        g2.fillRect(0, 0, w, h);
        g2.dispose();
        // 2) agora pintamos os componentes filhos (botões, labels etc)
        super.paintComponent(g);
    }
}
