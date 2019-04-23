package com.gieb.ixtapoli_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class registro extends AppCompatActivity{

    EditText nombre, paterno, materno, escuela, domicilio, promedio, usuario, contra;
    Spinner ruta;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.nombre);
        paterno = findViewById(R.id.paterno);
        materno = findViewById(R.id.materno);
        escuela = findViewById(R.id.escuela);
        domicilio = findViewById(R.id.domicilio);
        promedio = findViewById(R.id.promedio);
        usuario = findViewById(R.id.usuario);
        contra = findViewById(R.id.contra);
        ruta = findViewById(R.id.ruta);

        //Spiner [Select]
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcRutas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ruta.setAdapter(adapter);

    }

    public void registro(View v){
        new ws().execute();
    }

    public void regresar(View v){
        Intent i = new Intent(v.getContext(), index.class);
        startActivity(i);
        finish();
    }

    private class ws extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String nom = nombre.getText().toString();
            String pat = paterno.getText().toString();
            String mat = materno.getText().toString();
            String esc = escuela.getText().toString();
            String dom = domicilio.getText().toString();
            String prom = promedio.getText().toString();
            String us = usuario.getText().toString();
            String con = contra.getText().toString();
            String rutaS = ruta.getSelectedItem().toString();
            int rut;
            if(rutaS.equalsIgnoreCase("Zacatenco")){
                rut = 1;
            }else{
                rut = 2;
            }
            ixtaws iws = new ixtaws();
            res = iws.registro(nom,pat,mat,esc,dom,prom,us,con,rut);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), index.class);
            startActivity(i);
            finish();
        }

    }

}
