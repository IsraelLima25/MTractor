package com.sgdn.sgdn.Service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.sgdn.sgdn.Activity.MainActivity;
import com.sgdn.sgdn.Activity.MenuLateralActivity;
import com.sgdn.sgdn.Activity.SplashScreen;
import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.Fragment.DashboardFragment;
import com.sgdn.sgdn.Negocio.ManagerNotification;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

public class ServicoAlarme extends JobService {

    public void dispararNotificação() {
        NotificationManager nm = (NotificationManager) getBaseContext().
                getSystemService(getBaseContext().NOTIFICATION_SERVICE);
        Intent inten = new Intent(getBaseContext(), MenuLateralActivity.class);
        inten.putExtra("mensagem", "Alarme disparado");
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

    @Override
    public boolean onStartJob(JobParameters params) {


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}
