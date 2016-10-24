package com.example.maana.david_amador;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.maana.david_amador.modelo.almacen.AlmacenLocalizaciones;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    LatLng localizacion;
    private Timer temporizador;
    private boolean isStop;
    Random random =new Random();
    AlmacenLocalizaciones almacenLocalizaciones =new AlmacenLocalizaciones(this);
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        localizacion = new LatLng(40, -3);
        mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
    public void empezar(View v){

        Snackbar.make(v,"Comenzando a pedir puntos de localización",Snackbar.LENGTH_LONG).show();
        //Toast.makeText(this, "Pidiendo al servidor puntos de localización", Toast.LENGTH_SHORT).show();

        temporizador = new Timer();
        TimerTask tarea = new TimerTask() {
            public void run() {
                if(!isStop) {
                    //almacenLocalizaciones = new AlmacenLocalizaciones(MapsActivity.this);
                    PeticionServidor peticionServidor = new PeticionServidor();
                    peticionServidor.execute();
                }
            }
        };
        temporizador.scheduleAtFixedRate(tarea, 0, 5000);

    }
    public void parar(View v){
        temporizador.cancel();
        Toast.makeText(this, "Tarea Parada", Toast.LENGTH_SHORT).show();
        almacenLocalizaciones.borrarLocalizaciones();
        localizacion = new LatLng(40, -3);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));

    }
    private class PeticionServidor extends AsyncTask<Void,Void,String> {
        public static final String IP ="10.0.2.2";
        public static final int PUERTO =6000;


        @Override
        protected String doInBackground(Void... params) {

            BufferedReader bf = null;
            Socket socket = null;
            String resultadoServer = null;

            try {
                socket =  new Socket(IP,PUERTO);


                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                bf = new BufferedReader(inputStreamReader);
                resultadoServer = bf.readLine();


            } catch (Exception e) {
                e.printStackTrace();
            }finally {

                if (socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return resultadoServer;
        }

        @Override
        protected void onPostExecute(String resultadoServer) {
            try {
                JSONObject jsonObject = new JSONObject(resultadoServer);
                double resLatitud = jsonObject.getDouble("latitud");
                double resLongitud = jsonObject.getDouble("longitud");

                localizacion = new LatLng(resLatitud, resLongitud);
                int rand = random.nextInt(5);

                ArrayList<String> listaLocalizaciones = almacenLocalizaciones.obtenerLocalizaciones();
                boolean estaRepetido = almacenLocalizaciones.comprobarLocalizaciones(listaLocalizaciones,resultadoServer);
                if (!estaRepetido){
                    almacenLocalizaciones.guardarLoc(resultadoServer);
                    switch (rand){
                        case 0:
                            mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
                            break;
                        case 1:
                            mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
                            break;
                        case 2:
                            mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
                            break;
                        case 3:
                            mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
                            break;
                        case 4:
                            mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
                            break;
                        case 5:
                            mMap.addMarker(new MarkerOptions().position(localizacion).title(localizacion.toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));

                    }
                }else {
                    Toast.makeText(MapsActivity.this, "Repetido", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MapsActivity.this, "Error al parsear el JSON", Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        isStop = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isStop = false;
    }
}
