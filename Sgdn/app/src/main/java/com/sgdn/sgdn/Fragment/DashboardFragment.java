package com.sgdn.sgdn.Fragment;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sgdn.sgdn.Activity.AutenticarActivity;
import com.sgdn.sgdn.Activity.MainActivity;
import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.Negocio.ManagerNotification;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.Service.ServicoAlarme;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private TextView nMaquina;
    private TextView nComponente;
    private TextView nInspecao;
    private View view;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_novo_fragmento, container, false);
        DataBase dataBase = new DataBase(getActivity());

        //Metodo temporário apenas para exibir a data do proximo alerta
        ArrayList<Alerta> alertas = dataBase.getAlertas();
        if (!alertas.isEmpty()) {
            Alerta alerta = alertas.get(alertas.size() - 1);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Toast.makeText(getContext(), formato.format(Date.parse(alerta.getDataAlerta())), Toast.LENGTH_LONG).show();
        }

        //Capturar Componentes
        nMaquina = view.findViewById(R.id.txtNumeroMaquina);
        nComponente = view.findViewById(R.id.txtNumeroComponente);
        nInspecao = view.findViewById(R.id.txtNumeroInspecao);


        //Buscar Numeros Base de Dados
        int numeroMaquinas = dataBase.getMaquinas().size();
        int numeroComponentes = dataBase.getComponentes().size();
        int numeroInspecoes = dataBase.getinspecoes().size();
        int numeroAlertas = dataBase.getAlertas().size();

        //Setar Valores
        nMaquina.setText(String.valueOf(numeroMaquinas));
        nComponente.setText(String.valueOf(numeroComponentes));
        nInspecao.setText(String.valueOf(numeroInspecoes));

        return view;
    }


}
