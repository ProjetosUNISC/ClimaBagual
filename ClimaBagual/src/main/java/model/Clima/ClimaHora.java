package model.Clima;

import model.*;

import java.time.LocalDateTime;

public class ClimaHora extends EntidadeBase {

    private LocalDateTime hora;
    private double temperatura;
    private double vento;
    private String descricao;


    private Cidade cidade;
    private int umidade;
    private double velocidadeVento;
    private double indiceUV;
    private double precipitacao;

    public ClimaHora() {

    }


    public ClimaHora(LocalDateTime hora, double temperatura, double vento, String descricao) {
        this.hora = hora;
        this.temperatura = temperatura;
        this.vento = vento;
        this.descricao = descricao;
    }

    public Cidade getCidade() {return cidade;}

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public LocalDateTime getDataHora() {return hora;}
    public void setDataHora(LocalDateTime hora) {this.hora = hora;
    }
    public double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(double temperatura) {this.temperatura = temperatura;}

    public double getVento() {return vento;}
    public void setVento(double vento) {this.vento = vento;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public int getUmidade() {return umidade;}
    public void setUmidade(int umidade) {this.umidade = umidade;}

    public double getVelocidadeVento() {return velocidadeVento;}
    public void setVelocidadeVento(double velocidadeVento) {this.velocidadeVento = velocidadeVento;}

    public double getIndiceUV() {return indiceUV;}
    public void setIndiceUV(double indiceUV) {this.indiceUV = indiceUV;}

    public double getPrecipitacao() {return precipitacao;}
    public void setPrecipitacao(double precipitacao) {this.precipitacao = precipitacao;}

    @Override
    public String toString() {
        return String.format("%s - %.1f°C, %.1f km/h, %s",
                hora.toLocalTime(), temperatura, vento, descricao);
    }
}
