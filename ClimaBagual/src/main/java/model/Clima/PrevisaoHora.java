package model.Clima;

public class PrevisaoHora {

    private String hora;
    private double temperatura;
    private int chanceChuva;
    private String condicao;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getChanceChuva() {
        return chanceChuva;
    }

    public void setChanceChuva(int chanceChuva) {
        this.chanceChuva = chanceChuva;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }
}
