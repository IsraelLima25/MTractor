package com.sgdn.sgdn.Dominio;

import android.content.ComponentName;

import java.io.Serializable;

public class Inspecao implements Serializable {

    private int idInspecao;
    private Integer qtdHorasTrabalho;
    private Integer qtdHorasDia;
    private Integer qtdDiasSemana;
    private Double alturaDesgaste;
    private Double larguraDesgaste;
    private String cliente;
    private String cidade;
    private String celularCliente;
    private Componente componente;


    public Inspecao() {
    }

    public Inspecao(int idInspecao, Integer qtdHorasTrabalho, Integer qtdHorasDia, Integer qtdDiasSemana, Double alturaDesgaste, Double larguraDesgaste, String cliente, String cidade, String celularCliente, Componente componente) {
        this.idInspecao = idInspecao;
        this.qtdHorasTrabalho = qtdHorasTrabalho;
        this.qtdHorasDia = qtdHorasDia;
        this.qtdDiasSemana = qtdDiasSemana;
        this.alturaDesgaste = alturaDesgaste;
        this.larguraDesgaste = larguraDesgaste;
        this.cliente = cliente;
        this.cidade = cidade;
        this.celularCliente = celularCliente;
        this.componente = componente;
    }

    public int getIdInspecao() {
        return idInspecao;
    }

    public void setIdInspecao(int idInspecao) {
        this.idInspecao = idInspecao;
    }

    public Integer getQtdHorasTrabalho() {
        return qtdHorasTrabalho;
    }

    public void setQtdHorasTrabalho(Integer qtdHorasTrabalho) {
        this.qtdHorasTrabalho = qtdHorasTrabalho;
    }

    public Double getAlturaDesgaste() {
        return alturaDesgaste;
    }

    public void setAlturaDesgaste(Double alturaDesgaste) {
        this.alturaDesgaste = alturaDesgaste;
    }

    public Double getLarguraDesgaste() {
        return larguraDesgaste;
    }

    public void setLarguraDesgaste(Double larguraDesgaste) {
        this.larguraDesgaste = larguraDesgaste;
    }


    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String email) {
        this.celularCliente = email;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getQtdHorasDia() {
        return qtdHorasDia;
    }

    public void setQtdHorasDia(Integer qtdHorasDia) {
        this.qtdHorasDia = qtdHorasDia;
    }

    public Integer getQtdDiasSemana() {
        return qtdDiasSemana;
    }

    public void setQtdDiasSemana(Integer qtdDiasSemana) {
        this.qtdDiasSemana = qtdDiasSemana;
    }
}
