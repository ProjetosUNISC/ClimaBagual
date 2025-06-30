package view.updater;

import model.Clima.ClimaDia;

import javax.swing.*;
import java.util.List;

public class PrevisaoDiasViewUpdater {

    public static void atualizar(JTextArea area, List<ClimaDia> dias) {
        if (dias == null || dias.isEmpty()) {
            area.setText("❌ Erro ao carregar previsão diária.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(dias.size(), 5); i++) {
            ClimaDia d = dias.get(i);
            sb.append(String.format(
                    "%s\n🌡️ %.1f°C / %.1f°C  💨 %.1f km/h\n☀️ %s → %s  %s\n\n",
                    d.getData(),
                    d.getTempMin(),
                    d.getTempMax(),
                    d.getVento(),
                    d.getNascer(),
                    d.getPor(),
                    d.getDescricao()
            ));
        }

        area.setText(sb.toString());
    }
}
