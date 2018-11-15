package com.sgdn.sgdn.Dominio;

import java.io.Serializable;

public class Componente implements Serializable {

    private int idComponente;
    private String nome;
    private int qtdHorasTrabalho;
    private Double alturaMinimaDesgaste;
    private Double larguraMinimaDesgaste;
    private Maquina maquina;

    public Componente() {
    }

    public Componente(int idComponente, String nome, int qtdHorasTrabalho, Double alturaMinimaDesgaste, Double larguraMinimaDesgaste, String terreno, Maquina maquina) {
        this.idComponente = idComponente;
        this.nome = nome;
        this.qtdHorasTrabalho = qtdHorasTrabalho;
        this.alturaMinimaDesgaste = alturaMinimaDesgaste;
        this.larguraMinimaDesgaste = larguraMinimaDesgaste;
        this.maquina = maquina;
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }

    public Integer getQtdHorasTrabalho() {
        return qtdHorasTrabalho;
    }

    public void setQtdHorasTrabalho(int qtdHorasTrabalho) {
        this.qtdHorasTrabalho = qtdHorasTrabalho;
    }

    public double getAlturaMinimaDesgaste() {
        return alturaMinimaDesgaste;
    }

    public void setAlturaMinimaDesgaste(Double alturaMinimaDesgaste) {
        this.alturaMinimaDesgaste = alturaMinimaDesgaste;
    }

    public Double getLarguraMinimaDesgaste() {
        return larguraMinimaDesgaste;
    }

    public void setLarguraMinimaDesgaste(Double larguraMinimaDesgaste) {
        this.larguraMinimaDesgaste = larguraMinimaDesgaste;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
