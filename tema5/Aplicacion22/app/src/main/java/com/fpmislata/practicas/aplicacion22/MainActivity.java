package com.fpmislata.practicas.aplicacion22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDefecto= (Button) findViewById(R.id.btnDefecto);
        Button btnGravity= (Button) findViewById(R.id.btnGravity);
        Button btnPersonalizado= (Button) findViewById(R.id.btnPersonalizado);

        btnDefecto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Se ha pulsado el botón", Toast.LENGTH_SHORT).show();
            }
        });

        btnGravity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "Toast con gravity", Toast.LENGTH_SHORT);

                toast2.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                toast2.show();
            }
        });

        btnPersonalizado.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast toast3 = new Toast(getApplicationContext());

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.lytLayout));

                TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
                txtMsg.setText("Toast Personalizado");

                toast3.setDuration(Toast.LENGTH_SHORT);
                toast3.setView(layout);
                toast3.show();
            }
        });
    }
}
