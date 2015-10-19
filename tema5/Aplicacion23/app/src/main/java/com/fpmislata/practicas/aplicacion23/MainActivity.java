package com.fpmislata.practicas.aplicacion23;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotificacion = (Button)findViewById(R.id.btnNotif);

        btnNotificacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(android.R.drawable.stat_sys_warning)
                                .setLargeIcon((((BitmapDrawable) getResources()
                                        .getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                                .setContentTitle("Mensaje de Alerta")
                                .setContentText("Ejemplo de notificación.")
                                .setContentInfo("4")
                                .setTicker("Alerta!");

                Intent notIntent =
                        new Intent(MainActivity.this, MainActivity.class);

                PendingIntent contIntent = PendingIntent.getActivity(
                        MainActivity.this, 0, notIntent, 0);

                mBuilder.setContentIntent(contIntent);

                // Patrón de vibración: 1 segundo vibra, 0.5 segundos para, 1 segundo vibra
                long[] pattern = new long[]{1000,500,1000};

                mBuilder.setVibrate(pattern);

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, mBuilder.build());
            }
        });
    }
}
