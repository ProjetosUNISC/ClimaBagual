package model;


import com.google.gson.annotations.SerializedName;

public class Cidade extends EntidadeBase {
    
    private int state_id;
    private double latitude;
    private double longitude;



    public Cidade() {}

    public Cidade(int id, String nome, int state_id) {
        setId(id);
        setNome(nome);
        this.state_id = state_id;
    }

    @Override
    public String toString() {
        return getNome(); // isso que aparece no comboCidade
    }

    @SerializedName("name")
    private String nome;

    // getters e setters

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
