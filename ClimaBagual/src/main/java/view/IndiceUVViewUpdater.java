package view.updater;

import model.Clima.IndiceUV;

import javax.swing.*;
import java.util.List;

public class IndiceUVViewUpdater {

    public static void atualizar(JTextArea area, List<IndiceUV> lista) {
        if (lista == null || lista.isEmpty()) {
            area.setText("❌ Erro ao carregar índice UV.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(lista.size(), 3); i++) {
            IndiceUV dia = lista.get(i);
            sb.append(String.format(
                    "%s\n🔆 UV Máx: %.1f\n🌤️ UV com céu limpo: %.1f\n\n",
                    dia.getData(),
                    dia.getUvMax(),
                    dia.getUvMaxCeoLimpo()
            ));
        }

        area.setText(sb.toString());
    }
}
