package main.java.view.panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenuFactory {
    public static void decorate(JComponent comp, int thickness, int padding) {
        Border line = BorderFactory.createLineBorder(new Color(150,150,150), thickness);
        Border pad  = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        comp.setBorder(BorderFactory.createCompoundBorder(line, pad));
    }
}
