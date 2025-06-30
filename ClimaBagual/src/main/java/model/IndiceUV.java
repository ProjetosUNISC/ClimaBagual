package model.Clima;

public class IndiceUV {
    private String data;
    private double uvMax;
    private double uvMaxCeoLimpo;

    public IndiceUV(String data, double uvMax, double uvMaxCeoLimpo) {
        this.data = data;
        this.uvMax = uvMax;
        this.uvMaxCeoLimpo = uvMaxCeoLimpo;
    }

    public String getData() {
        return data;
    }

    public double getUvMax() {
        return uvMax;
    }

    public double getUvMaxCeoLimpo() {
        return uvMaxCeoLimpo;
    }
}
