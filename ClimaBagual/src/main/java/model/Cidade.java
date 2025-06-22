package model;


import com.google.gson.annotations.SerializedName;

public class Cidade extends EntidadeBase {
    
    private int state_id;

    public Cidade() {}

    public Cidade(int id, String nome, int state_id) {
        setId(id);
        setNome(nome);
        this.state_id = state_id;
    }


    @SerializedName("name")
    private String nome;

    // getters e setters
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
