package com.sgdn.sgdn.Dominio;

import java.io.Serializable;

public class Maquina implements Serializable {

    private int idMaquina;
    private String nome;
    private String fabricante;

    public Maquina() {
    }

    public Maquina(int idMaquina, String nome, String fabricante) {
        this.idMaquina = idMaquina;
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return nome;
    }
}
