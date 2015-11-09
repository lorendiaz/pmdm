package com.fpmislata.practicas.aplicacion33;

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

    class MiAsyncTask extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... n) {
            return factorial(n[0]);
        }

        @Override
        protected void onPostExecute(Integer res) {
            txtResultado.append(res + "\n");
        }
    }
}
