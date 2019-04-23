package com.gieb.ixtapoli_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MostrarEventos extends AppCompatActivity {

    TextView faena, fecha, hora, descripcion, ubicacion, materiales, cupo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_eventos);
        faena = findViewById(R.id.faena);
        fecha = findViewById(R.id.fecha);
        hora = findViewById(R.id.hora);
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        materiales = findViewById(R.id.materiales);
        cupo = findViewById(R.id.cupo);

        Intent i = getIntent();
        faena.setText(i.getStringExtra("titulo"));
        fecha.setText(i.getStringExtra("fecha"));
        hora.setText(i.getStringExtra("hora"));
        descripcion.setText(i.getStringExtra("descripcion"));
        ubicacion.setText(i.getStringExtra("ubicacion"));
        materiales.setText(i.getStringExtra("materiales"));
        cupo.setText(i.getStringExtra("cupo"));

    }

    public void verCalendario(View v){
        Intent i = new Intent(v.getContext(), calendario.class);
        finish();
        startActivity(i);
        finish();
    }
}
