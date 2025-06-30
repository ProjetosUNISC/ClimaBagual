package model.Clima;

public class ClimaDia {
    private String data;
    private int codigo;
    private double tempMax;
    private double tempMin;
    private String nascer;
    private String por;
    private double vento;
    private String descricao;

    public ClimaDia(String data, int codigo, double tempMax, double tempMin, String nascer, String por, double vento, String descricao) {
        this.data = data;
        this.codigo = codigo;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.nascer = nascer;
        this.por = por;
        this.vento = vento;
        this.descricao = descricao;
    }

    public String getData() { return data; }
    public int getCodigo() { return codigo; }
    public double getTempMax() { return tempMax; }
    public double getTempMin() { return tempMin; }
    public String getNascer() { return nascer; }
    public String getPor() { return por; }
    public double getVento() { return vento; }
    public String getDescricao() { return descricao; }
}
