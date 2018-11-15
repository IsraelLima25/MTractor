package com.sgdn.sgdn.Negocio;

import android.app.Activity;
import android.util.Log;

import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.io.CharArrayReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ManagerNotification {

    public void gerarAlerta(Alerta alerta, Activity activity) {

        DataBase dataBase = new DataBase(activity);
        long linhasAfetadas = dataBase.salvarAlerta(alerta);
    }

    public ArrayList<Alerta> alertasHoje(ArrayList<Alerta> lista) {

        ArrayList<Alerta> hoje = new ArrayList<>();
        Calendar dataHoje = Calendar.getInstance();
        Calendar dataAlerta = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < lista.size(); i++) {

            Date daAlerta = new Date(lista.get(i).getDataAlerta());
            dataAlerta.setTime(daAlerta);
            if (formato.format(dataAlerta.getTime()).equals(formato.format(dataHoje.getTime()))
                    && lista.get(i).getEstado().equals("pendente")
                    ) {
                hoje.add(lista.get(i));
            }
        }
        return hoje;
    }

    //Retorna Apenas Alertas Atrasados(Caso o Usuário fique com o dispositivo desligado)
    public ArrayList<Alerta> alertasAtrasado(ArrayList<Alerta> lista) {
        ArrayList<Alerta> atrasado = new ArrayList<>();
        Calendar dataHoje = Calendar.getInstance();
        Calendar dataAlerta = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < lista.size(); i++) {
            Date daAlerta = new Date(lista.get(i).getDataAlerta());
            dataAlerta.setTime(daAlerta);
            if (dataAlerta.getTime().before(dataHoje.getTime())
                    && lista.get(i).getEstado().equals("pendente")
                    && !formato.format(dataAlerta.getTime()).equals(formato.format(dataHoje.getTime()))
                    ) {
                atrasado.add(lista.get(i));
            }
        }
        return atrasado;
    }


}

