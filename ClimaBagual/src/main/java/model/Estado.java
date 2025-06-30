package model;

public class Estado extends EntidadeBase {

    private String sigla;



    public Estado() {
        // construtor vazio para uso com setters
    }


    @Override
    public String toString() {
        return getNome(); // Isso que vai aparecer na combo
    }

    public Estado(int id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    
}
