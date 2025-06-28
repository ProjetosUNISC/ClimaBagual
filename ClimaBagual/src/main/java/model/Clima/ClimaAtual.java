package model.Clima;

public class ClimaAtual {

    private double temperatura;
    private double vento;
    private String descricao;

    public ClimaAtual(double temperatura, double vento, String descricao) {
        this.temperatura = temperatura;
        this.vento = vento;
        this.descricao = descricao;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getVento() {
        return vento;
    }

    public void setVento(double vento) {
        this.vento = vento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
