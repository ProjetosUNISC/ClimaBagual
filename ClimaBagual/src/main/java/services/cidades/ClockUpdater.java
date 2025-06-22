package main.java.services.cidades;


import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility para preencher um JLabel com a hora atual
 * e mantê-lo atualizado a cada segundo.
 */
public class ClockUpdater {
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Inicializa o label com a hora atual e dispara um Timer
     * que atualiza a cada segundo.
     *
     * @param label o JLabel que receberá o texto da hora
     */
    public static void start(JLabel label) {
        // atualiza imediatamente
        label.setText(LocalDateTime.now().format(FORMAT));

        // dispara Timer que chama a cada 1s
        Timer t = new Timer(1000, e ->
                label.setText(LocalDateTime.now().format(FORMAT))
        );
        t.setRepeats(true);
        t.start();
    }
}

