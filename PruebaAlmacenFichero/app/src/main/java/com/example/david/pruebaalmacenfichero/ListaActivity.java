package com.example.david.pruebaalmacenfichero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.david.pruebaalmacenfichero.modelo.almacen.AlmacenNombres;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = (ListView)findViewById(R.id.lvNombres);

        AlmacenNombres almacenNombres = new AlmacenNombres(this);
        ArrayList<String> listaNombres = almacenNombres.obtenerNombres();
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaNombres);

        listView.setAdapter(arrayAdapter);
    }
}
