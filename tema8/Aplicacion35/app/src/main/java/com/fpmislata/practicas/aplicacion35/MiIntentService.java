package com.fpmislata.practicas.aplicacion35;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by loren on 4/11/15.
 */
public class MiIntentService extends IntentService {

    public static final String ACTION_PROGRESO =
            "com.fpmislata.practicas.aplicacion35.PROGRESO";
    public static final String ACTION_FIN =
            "com.fpmislata.practicas.aplicacion35.FIN";

    public MiIntentService() {
        super("MiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        int iter = intent.getIntExtra("iteraciones", 0);

        for(int i=1; i<=iter; i++) {
            tareaLarga();

            //Comunicamos el progreso
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            sendBroadcast(bcIntent);
        }

        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        sendBroadcast(bcIntent);
    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }
}