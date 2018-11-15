package com.sgdn.sgdn.Dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Alerta implements Serializable {

    private int idAlerta;
    private Inspecao inspecao;
    private String dataAlerta;
    private String estado;

    public Alerta() {
    }

    public Alerta(int idAlerta, Inspecao inspecao, String dataAlerta, String estado) {
        this.idAlerta = idAlerta;
        this.inspecao = inspecao;
        this.dataAlerta = dataAlerta;
        this.estado = estado;
    }


    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getDataAlerta() {
        return dataAlerta;
    }

    public void setDataAlerta(String dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
