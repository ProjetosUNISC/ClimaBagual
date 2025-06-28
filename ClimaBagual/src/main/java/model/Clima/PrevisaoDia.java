package model.Clima;

public class PrevisaoDia {

    private String data;
    private double tempMin;
    private double tempMax;
    private double chuva;
    private String condicao;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getChuva() {
        return chuva;
    }

    public void setChuva(double chuva) {
        this.chuva = chuva;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }
}
