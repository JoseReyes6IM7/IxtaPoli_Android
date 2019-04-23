package com.gieb.ixtapoli_android;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ixtaws {
    static final String IP = "192.168.1.66";
    static final String PORT = "8084";

    static final String NAMESPACE= "http://ws.ixtapoli.com/";
    static final String URL = "http://"+IP+":"+PORT+"/IxtaPoli/ixtaws?wsdl";
    static final String LOGIN= "\"" + NAMESPACE+"login" + "\"";
    static final String CALENDARIO= "\"" + NAMESPACE+"calendar" + "\"";
    static final String REGISTRO = "\"" + NAMESPACE+"registro" + "\"";


    public String login(String usr, String psw){
        String respuesta = null;
        SoapObject request = new SoapObject(NAMESPACE, "login");
        request.addProperty("usuario", usr);
        request.addProperty("contra", psw);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(LOGIN, envelope);
            SoapPrimitive res =(SoapPrimitive)envelope.getResponse();
            Log.d("respuestaws: " , res.toString());
            respuesta = res.toString();
        }
        catch (Exception e)
        {
            Log.d("respuestaws: " , "ERRORLOGIN.WS " + e.getMessage());
        }

        return respuesta;
    }

    public String calendario(){
        String respuesta = null;
        SoapObject request = new SoapObject(NAMESPACE, "calendar");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(CALENDARIO, envelope);
            SoapPrimitive res =(SoapPrimitive)envelope.getResponse();
            Log.d("respuestaws: " , res.toString());
            respuesta = res.toString();
        }
        catch (Exception e)
        {
            Log.d("respuestaws: " , "ERRORCALENDARIRO.WS " + e.getMessage());
        }

        return respuesta;
    }

    public String registro(String nombre, String paterno, String materno,
    String escuela, String domicilio, String promedio, String usuario, String contra, int ruta){

        String respuesta = null;
        SoapObject request = new SoapObject(NAMESPACE, "registro");
        request.addProperty("nombre", nombre);
        request.addProperty("paterno", paterno);
        request.addProperty("materno", materno);
        request.addProperty("escuela", escuela);
        request.addProperty("domicilio", domicilio);
        request.addProperty("promedio", promedio);
        request.addProperty("usuario", usuario);
        request.addProperty("contra", contra);
        request.addProperty("ruta", ruta);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(REGISTRO, envelope);
            SoapPrimitive res =(SoapPrimitive)envelope.getResponse();
            Log.d("respuestaws: " , res.toString());
            respuesta = res.toString();
        }
        catch (Exception e)
        {
            Log.d("respuestaws: " , "ERRORREGISTRO.WS " + e.getMessage());
        }

        return respuesta;
    }


}
