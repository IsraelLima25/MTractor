package com.sgdn.sgdn.Negocio;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.Dominio.Maquina;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManagerDesgaste {

    private Inspecao inspecao;
    private final int PRAZO_HORAS = 120;

    public ManagerDesgaste() {
        //calcularTempoDesgaste();
    }

    private void gerarAlerta() {

    }

    public void calcularTempoDesgaste(Inspecao inspecao, Activity activity) {
        Integer horaComponenteFabrica = inspecao.getComponente().getQtdHorasTrabalho();
        Integer horaComponenteAtual = inspecao.getQtdHorasTrabalho();
        Integer qtdHorasRestantes = horaComponenteFabrica - horaComponenteAtual;
        Double alturaAreaDesgaste = inspecao.getAlturaDesgaste();
        Double larguraAreDesgaste = inspecao.getLarguraDesgaste();
        Double alturaAreaDesgasteFabrica = inspecao.getComponente().getAlturaMinimaDesgaste();
        Double larguraAreaDesgasteFabrica = inspecao.getComponente().getLarguraMinimaDesgaste();

        if (qtdHorasRestantes <= 0 || qtdHorasRestantes <= 24 || alturaAreaDesgaste <= alturaAreaDesgasteFabrica
                || larguraAreDesgaste <= larguraAreaDesgasteFabrica || larguraAreDesgaste <= 0 ||
                alturaAreaDesgaste <= 0) {
            gerarDataAproximada(24, inspecao, activity);
        } else {
            gerarDataAproximada(qtdHorasRestantes, inspecao, activity);
        }
    }

    public void gerarDataAproximada(Integer qtdHoras, Inspecao inspecao, Activity activity) {

        if (qtdHoras <= 24) {
            int dia = 1;
            //Gerar data para possibilidade
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Alerta alerta = new Alerta();
            alerta.setInspecao(inspecao);
            alerta.setDataAlerta(calendar.getTime().toString());
            ManagerNotification managerNotification = new ManagerNotification();
            managerNotification.gerarAlerta(alerta, activity);

        } else {

            int qtdDiasSemana = inspecao.getQtdDiasSemana();
            int qtdHorasDia = inspecao.getQtdHorasDia();
            int totalHorasSemana = qtdDiasSemana * qtdHorasDia;
            int horasRestantesSemana = qtdHoras / 7;
            int totalSemanal = horasRestantesSemana * totalHorasSemana;
            int dias = totalSemanal / qtdDiasSemana;
            dias -= PRAZO_HORAS;

            Alerta alerta = new Alerta();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            calendar.add(Calendar.DATE, dias);
            alerta.setDataAlerta(calendar.getTime().toString());
            alerta.setInspecao(inspecao);
            Log.i("retorno", String.valueOf(dias));
            Log.i("data", String.valueOf(calendar.getTime()));
            ManagerNotification managerNotification = new ManagerNotification();
            managerNotification.gerarAlerta(alerta, activity);
        }
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }
}
