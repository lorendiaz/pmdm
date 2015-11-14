package com.fpmislata.practicas.aplicacion34;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtNumero;
    private Button btnCalcular;
    private TextView txtResultado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumero = (EditText) findViewById(R.id.edtNumero);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(edtNumero.getText().toString());
        txtResultado.append(n +"! = ");

        MiAsyncTask tarea = new MiAsyncTask();
        tarea.execute(n);

    }

    public int factorial(int n) {
        int res=1;
        for (int i=1; i<=n; i++){
            res=res * i;
            SystemClock.sleep(1000);
        }
        return res;
    }

    class MiAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(MainActivity.this);
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Calculando...");
            progreso.setCancelable(true);

            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiAsyncTask.this.cancel(true);
                }
            });

            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();
        }

        @Override
        protected Integer doInBackground(Integer... n) {
            int res = 1;
            for (int i = 1; i <= n[0] && !isCancelled(); i++) {
                res *= i;
                SystemClock.sleep(1000);
                publishProgress(i*100 / n[0]);
            }

            return res;
        }

        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(Integer res) {
            progreso.dismiss();
            txtResultado.append(res + "\n");
        }

        @Override
        protected void onCancelled() {
            txtResultado.append("cancelado\n");
        }

    }
    
}
