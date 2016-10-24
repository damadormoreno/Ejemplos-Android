package com.example.maana.david_amador.modelo.almacen;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by MAÑANA on 24/10/2016.
 */

public class AlmacenLocalizaciones {
    Context context;
    private static final String FICHERO = "loc.txt";

    public AlmacenLocalizaciones(Context context){
        super();
        this.context = context;
    }

    public void guardarLoc(String loc){
        FileOutputStream fos = null;
        PrintStream out = null;
        try {
            fos = context.openFileOutput(FICHERO, Context.MODE_APPEND);
            out = new PrintStream(fos);
            out.println(loc);
            out.flush();
        }catch (Exception e){
            Log.e("AlamacenLocalizacion","Error al abrir el archivo");
        }finally {
            if(out != null){
                out.close();
            }
        }
    }
    public ArrayList<String> obtenerLocalizaciones(){
        ArrayList<String> arrayList = new ArrayList<>();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {

            fis = context.openFileInput(FICHERO);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String loca = br.readLine();
            while(loca != null){

                arrayList.add(loca);
                loca = br.readLine();
            }


        }catch (Exception e){
            Log.e("AlmacenNotas","Error al leer notas");
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return  arrayList;
    }
    public void borrarLocalizaciones(){
        FileOutputStream fos = null;
        try {
            context.openFileOutput(FICHERO, Context.MODE_PRIVATE);
        }catch (Exception e){
            Log.e("AlmacenLocalizaciones","Error al borrar las localizaciones");
        }
    }

    /**/
    public boolean comprobarLocalizaciones(ArrayList<String> arrayList, String localizacion){
        boolean ok= false;
        /*for (int i = 0; i <arrayList.size()-1 ; i++) {
            if (localizacion.equals(arrayList.get(i))){
                ok=true;
            }
        }*/
        for (String loc: arrayList) {
            if (localizacion.equals(loc)){
                ok=true;
            }
        }
        return ok;
    }
}
