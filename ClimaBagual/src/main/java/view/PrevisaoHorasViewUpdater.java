package view;

import model.Clima.ClimaHora;

import javax.swing.*;
import java.util.List;

public class PrevisaoHorasViewUpdater {

    /**
     * Atualiza o JTextArea com as previsões horárias formatadas.
     *
     * @param area JTextArea da view onde será exibida a previsão
     * @param previsoes Lista de previsões por hora
     */
    public static void atualizar(JTextArea area, List<ClimaHora> previsoes) {
        if (previsoes == null || previsoes.isEmpty()) {
            area.setText("Erro ao carregar previsão horária.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int limite = Math.min(previsoes.size(), 6); // mostra só as 6 primeiras horas
        for (int i = 0; i < limite; i++) {
            ClimaHora hora = previsoes.get(i);
            sb.append(String.format(
                    "%s - %.1f °C, %.1f km/h, %s\n",
                    hora.getDataHora().toLocalTime().toString(),
                    hora.getTemperatura(),
                    hora.getVento(),
                    hora.getDescricao()
            ));
        }

        area.setText(sb.toString());
    }
}
