package com.sgdn.sgdn.SrcDataBase;

import com.sgdn.sgdn.Dominio.Maquina;

import java.util.ArrayList;

public class DataBaseSimulator {

    public static ArrayList<Maquina> listaDeMaquinas() {

        ArrayList<Maquina> listaCarregada = new ArrayList<>();
        Maquina maquina1 = new Maquina();
        maquina1.setIdMaquina(1);
        maquina1.setNome("Cat 320");
        maquina1.setFabricante("Caterpillar");
        Maquina maquina2 = new Maquina();
        maquina2.setIdMaquina(2);
        maquina2.setNome("D4E");
        maquina2.setFabricante("Caterpillar");

        listaCarregada.add(maquina1);
        listaCarregada.add(maquina2);

        return listaCarregada;
    }
}
