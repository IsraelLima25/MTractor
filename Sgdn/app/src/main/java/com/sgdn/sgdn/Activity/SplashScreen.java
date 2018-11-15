package com.sgdn.sgdn.Activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Fragment.AlterarMaquinaFragment;
import com.sgdn.sgdn.Fragment.DashboardFragment;
import com.sgdn.sgdn.Negocio.ManagerNotification;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.Service.ServicoAlarme;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {
    // Timer da splash screen
    private static int SPLASH_TIME_OUT = 6000;

    private Handler handler = new Handler();

    //Utilizar o Runnable sempre que quiser rodar um processo em uma thread
    private Runnable runnableCodigo;

    public void dispararNotificação(Alerta alerta) {
        NotificationManager nm = (NotificationManager) getBaseContext().
                getSystemService(getBaseContext().NOTIFICATION_SERVICE);
        Intent inten = new Intent(getBaseContext(), MenuLateralActivity.class);
        inten.putExtra("isNotification", String.valueOf(alerta.getIdAlerta()));
        int id = (int) (Math.random() * 1000);
        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), id,
                inten, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification noti = new Notification.Builder(getBaseContext())
                .setContentTitle("De: MTractor")
                .setContentText("Nova Máquina")
                .setSmallIcon(R.drawable.ic_maquina)
                .setContentIntent(pi).build();
        NotificationManager notificationManager = (NotificationManager)
                getBaseContext().
                        getSystemService(getBaseContext().NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(id, noti);
    }

    private boolean enviarSms(Alerta alerta) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("5554", null, "Senhor(a) " + alerta.getInspecao().getCliente() +
                    " Novo Alerta Identificado para o item " + alerta.getInspecao().getComponente().getNome() + "" +
                    " da máquina " + alerta.getInspecao().getComponente().getMaquina().getNome() + ", " +
                    " favor monitorar o estado de inspeção ou entrar em contato conosco. Atenciosamente" +
                    " equipe MTractor", null, null);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*
        //Serviço sendo requisitado
        ComponentName serviceName = new ComponentName(getBaseContext(),
                ServicoAlarme.class);
        JobInfo jobInfo = new JobInfo.Builder(0, serviceName)
                //.setMinimumLatency(Integer.parseInt(segundos.getText().toString()) * 1000)
                .setPeriodic(5000)
                .setRequiresCharging(true)
                .build();
        JobScheduler scheduler = (JobScheduler) getBaseContext()
                .getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS)
            Log.d("MainActivity", "Serviço agendado!");

        */

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(SplashScreen.this, AutenticarActivity.class);
                startActivity(i);
                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);

        runnableCodigo = new Runnable() {
            @Override
            public void run() {

                //Verifica as notificações do dia
                DataBase dataBase = new DataBase(SplashScreen.this);
                ManagerNotification managerNotification = new ManagerNotification();
                ArrayList<Alerta> alertasHoje = managerNotification.alertasHoje(dataBase.getAlertas());


                if (alertasHoje != null && alertasHoje.size() > 0) {

                    for (int i = 0; i < alertasHoje.size(); i++) {

                        if (alertasHoje.get(i).getEstado().equals("pendente")) {
                            dispararNotificação(alertasHoje.get(i));
                            enviarSms(alertasHoje.get(i));
                            dataBase.updateAlerta(alertasHoje.get(i));
                        }
                    }
                }
                //Verifica as notificações atrasadas pendentes(caso o dispositivo ficou desligado nesse periodo)

                ArrayList<Alerta> alertasAtrasados = managerNotification.alertasAtrasado(dataBase.getAlertas());
                if (alertasAtrasados != null && alertasAtrasados.size() > 0) {
                    for (int i = 0; i < alertasAtrasados.size(); i++) {
                        if (alertasAtrasados.get(i).getEstado().equals("pendente")) {
                            dispararNotificação(alertasAtrasados.get(i));
                            enviarSms(alertasAtrasados.get(i));
                            dataBase.updateAlerta(alertasAtrasados.get(i));
                        }
                    }
                }

                //executa o objeto runnableCodigo a cada 10 segundo, configure aqui o tempo
                handler.postDelayed(runnableCodigo, 10000);
            }

        };

        handler.post(runnableCodigo);


    }
}
