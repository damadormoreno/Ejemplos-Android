package com.example.david.pruebaalmacenfichero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.david.pruebaalmacenfichero.modelo.almacen.AlmacenNombres;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.etNombre);

    }
    public void add(View v){
        AlmacenNombres almacenNombres = new AlmacenNombres(this);
        almacenNombres.guardarNombre(editText.getText().toString());
        editText.setText("");

    }
    public void list(View v){
        Intent intent = new Intent(this,ListaActivity.class);
        startActivity(intent);
    }
}
