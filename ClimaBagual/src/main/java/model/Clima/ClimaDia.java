package model.Clima;

import model.*;

import java.time.*;

public class ClimaDia extends EntidadeBase {

    private Cidade cidade;
    private int codigo;
    private double tempMax;
    private double tempMin;
    private String nascer;
    private String por;
    private double vento;
    private String descricao;
    private LocalDate data;
    private double velocidadeVento;
    private double precipitacao;


    public ClimaDia() {

    }


    public ClimaDia(String data, int codigo, double tempMax, double tempMin, String nascer, String por, double vento, String descricao) {
        this.data = LocalDate.parse(data);
        this.codigo = codigo;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.nascer = nascer;
        this.por = por;
        this.vento = vento;
        this.descricao = descricao;
    }


    public String getNascer() { return nascer; }
    public String getPor() { return por; }
    public double getVento() { return vento; }


    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public double getTempMin() { return tempMin; }
    public void setTempMin(double tempMin) { this.tempMin = tempMin; }

    public double getTempMax() { return tempMax; }
    public void setTempMax(double tempMax) { this.tempMax = tempMax; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getVelocidadeVento() { return velocidadeVento; }
    public void setVelocidadeVento(double velocidadeVento) { this.velocidadeVento = velocidadeVento; }

    public double getPrecipitacao() { return precipitacao; }
    public void setPrecipitacao(double precipitacao) { this.precipitacao = precipitacao; }

}
