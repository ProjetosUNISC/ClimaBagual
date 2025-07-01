package model.Clima;

import model.*;

public class ClimaAtual extends EntidadeBase {

    private double temperatura;
    private double vento;
    private String descricao;
    private int umidade;
    private double indiceUV;
    private double precipitacao;
    private Cidade cidade;

    public ClimaAtual() {

    }

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


    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getUmidade() {
        return umidade;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public double getIndiceUV() {
        return indiceUV;
    }

    public void setIndiceUV(double indiceUV) {
        this.indiceUV = indiceUV;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public double getVelocidadeVento() {
        return vento; // se vento == velocidade do vento
    }
}
