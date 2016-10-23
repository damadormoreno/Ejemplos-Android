package com.example.david.pruebaalmacenfichero.modelo.almacen;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by David on 23/10/2016.
 */

public class AlmacenNombres {
    private static final String FICHERO = "nombres.txt";
    private Context context;

    public AlmacenNombres(Context context){
        this.context = context;
    }

    public void guardarNombre(String nombre){
        FileOutputStream fos = null;
        PrintStream out = null;
        try {
            fos = context.openFileOutput(FICHERO, Context.MODE_APPEND);
            out = new PrintStream(fos);
            out.println(nombre);
            out.flush();
        }catch (Exception e){
            Log.e("AlamacenNombre","Error al abrir el archivo");
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    public void borrarNombres(){
        FileOutputStream fos = null;
        try {
            context.openFileOutput(FICHERO, Context.MODE_PRIVATE);
        }catch (Exception e){
            Log.e("AlmacenNombres","Error al borrar los nombres");
        }
    }

    public ArrayList<String> obtenerNombres(){
        ArrayList<String> arrayList = new ArrayList<>();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            //Canuto de entrada
            fis = context.openFileInput(FICHERO);
            //Objeto para leer del canuto, caracter a caracter
            isr = new InputStreamReader(fis);
            //Objeto para leer frases enteras, linea a linea
            br = new BufferedReader(isr);
            //A partir de aqui seria lo cambiante en nuestra logica de negocio
            String nombre = br.readLine();
            while(nombre != null){
                arrayList.add(nombre);
                nombre = br.readLine();
            }
            //Fin de a partir de aqui

        }catch (Exception e){
            Log.e("AlmacenNombres","Error al leer nombres");
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

}
