package com.gieb.ixtapoli_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class calendario extends AppCompatActivity {

    CalendarView calendarView;
    JSONArray jsonArr;
    List<EventDay> eve = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        calendarView = findViewById(R.id.calendarView);
        new ws().execute();

        calendarView.setOnDayClickListener(eventDay -> {
            pressFecha(eventDay);
        });
    }


    public void irCalendario(View v){
        Intent i = new Intent(v.getContext(), menu.class);
        startActivity(i);
        finish();

    }

    class ws extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            ixtaws calws = new ixtaws();
            //Obtiene fechas desde webservice
            String fechas = calws.calendario();

            //transforma a objeto JSON
            try {
                jsonArr = new JSONArray(fechas);
            } catch (JSONException e) {
                Log.d("calendario", "error convertir json");
                e.printStackTrace();
            }
            //recorre los eventos
            for(int i=0; i<jsonArr.length(); i++){
                //Obtiene cada evento y lo agrega al calendario
                try {
                    JSONObject evento = jsonArr.getJSONObject(i);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, evento.getInt("anio"));
                    calendar.set(Calendar.MONTH, evento.getInt("mes")-1);
                    calendar.set(Calendar.DAY_OF_MONTH, evento.getInt("dia"));

                    eve.add(new EventDay(calendar, R.drawable.calendaricon));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("calendario", "error agregar calendario");
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            calendarView.setEvents(eve);
        }
    }

    public void pressFecha(EventDay eventDay){
        Calendar evento = eventDay.getCalendar();
        int dia = evento.get(Calendar.DAY_OF_MONTH);
        int mes = evento.get(Calendar.MONTH) + 1;
        int anio = evento.get(Calendar.YEAR);

        for(int i=0; i<jsonArr.length();i++){
            try {
                if(dia == jsonArr.getJSONObject(i).getInt("dia") && mes == jsonArr.getJSONObject(i).getInt("mes") && anio == jsonArr.getJSONObject(i).getInt("anio")){
                    String titulo = jsonArr.getJSONObject(i).getString("title");
                    String fecha = jsonArr.getJSONObject(i).getString("fecha");
                    String hora = jsonArr.getJSONObject(i).getString("hora");
                    String descripcion = jsonArr.getJSONObject(i).getString("descripcion");
                    String materiales = jsonArr.getJSONObject(i).getString("materiales");
                    String ubicacion = jsonArr.getJSONObject(i).getString("ubicacion");
                    String cupo = jsonArr.getJSONObject(i).getString("cupo");

                    Intent act = new Intent(getApplicationContext(), MostrarEventos.class);
                    act.putExtra("titulo", titulo);
                    act.putExtra("fecha", fecha);
                    act.putExtra("hora", hora);
                    act.putExtra("descripcion", descripcion);
                    act.putExtra("materiales", materiales);
                    act.putExtra("ubicacion", ubicacion);
                    act.putExtra("cupo", cupo);
                    startActivity(act);
                }else{

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("calendar", "errorAlConsultarEvento");
            }
        }
    }
}
