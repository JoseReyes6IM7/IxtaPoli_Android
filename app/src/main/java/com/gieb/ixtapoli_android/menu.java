package com.gieb.ixtapoli_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void irCalendario(View v){
        Intent i = new Intent(v.getContext(), calendario.class);
        startActivity(i);
        finish();
    }

    public void irMapa(View v){
        Intent i = new Intent(v.getContext(), MapsActivity.class);
        startActivity(i);
        finish();
    }
}
