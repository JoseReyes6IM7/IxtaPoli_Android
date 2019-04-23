package com.gieb.ixtapoli_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class index extends AppCompatActivity {

    EditText usr, contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        usr = findViewById(R.id.usr);
        contra = findViewById(R.id.psw);
    }

    public void login(View v){
        new ws().execute();
    }

    public void entrarsinlogin(View v){
        Intent i = new Intent(getApplicationContext(), menu.class);
        startActivity(i);
        finish();
    }

    public void registro(View v){
        Intent i = new Intent(getApplicationContext(), registro.class);
        startActivity(i);
        finish();
    }


    private class ws extends AsyncTask<String, String,String>{
        @Override
        protected String doInBackground(String... strings) {
             ixtaws iws = new ixtaws();
             String res = iws.login(usr.getText().toString().trim(), contra.getText().toString().trim());

             if(res.equalsIgnoreCase("Espera")){
                 Intent i = new Intent(getApplicationContext(), Espera.class);
                 startActivity(i);
                 finish();
             }else{
                 if(res.equalsIgnoreCase("Aceptado")){
                     Intent i = new Intent(getApplicationContext(), menu.class);
                     startActivity(i);
                     finish();
                 }else{
                     if(res.equalsIgnoreCase("Rechazado")){
                         Intent i = new Intent(getApplicationContext(), Rechazado.class);
                         startActivity(i);
                         finish();
                     }else{
                        //NoRegistrado o datos incorrectos
                     }
                 }
             }

            return null;
        }
    }
}
