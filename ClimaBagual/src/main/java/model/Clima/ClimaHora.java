package model.Clima;

import java.time.LocalDateTime;

public class ClimaHora {
    private LocalDateTime hora;
    private double temperatura;
    private double vento;
    private String descricao;

    public ClimaHora(LocalDateTime hora, double temperatura, double vento, String descricao) {
        this.hora = hora;
        this.temperatura = temperatura;
        this.vento = vento;
        this.descricao = descricao;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getVento() {
        return vento;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return String.format("%s - %.1f°C, %.1f km/h, %s",
                hora.toLocalTime(), temperatura, vento, descricao);
    }
}
