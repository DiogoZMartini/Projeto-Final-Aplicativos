package br.edu.qi.projetofinalapp1;

import java.io.Serializable;

public class Receita implements Serializable {
    private int id;
    private String nome;
    private String ingredientes;
    private String preparo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    @Override
    public String toString() {
        return "Receita: "+nome;
    }
}
