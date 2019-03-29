package com.gieb.ixtapoli_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void login(View v){
        Intent i = new Intent(v.getContext(), menu.class);
        startActivity(i);
        finish();
    }
}
