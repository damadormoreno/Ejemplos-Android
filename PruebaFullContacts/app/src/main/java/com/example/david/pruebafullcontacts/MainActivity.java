package com.example.david.pruebafullcontacts;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    HttpURLConnection urlConnection;
    StringBuilder stringBuilder;

    static final String API_KEY = "YOUR_KEY";
    static final String API_URL = "https://api.fullcontact.com/v2/person.json?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.etEmail);
        textView = (TextView)findViewById(R.id.tvResultado);



    }
    public void buscar(View v){
        ConectarServidor conectarServidor = new ConectarServidor();
        conectarServidor.execute(editText.getText().toString());
        //TODO controlar los nulos en el EditText
        //TODO Añadir un ProgressDialog en la consulta a web.
    }

    private class ConectarServidor extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            textView.setText("");
        }

        @Override
        protected String doInBackground(String... params) {
            String sb = null;
            try {
                URL url = new URL(API_URL + "email=" + params[0] + "&apiKey=" + API_KEY);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                stringBuilder = new StringBuilder();
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    stringBuilder.append(linea).append("\n");
                }
                bufferedReader.close();
                sb = stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                urlConnection.disconnect();
            }

            return sb;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject= new JSONObject(s);
                //String id = jsonObject.getString("requestId");
                JSONObject job = new JSONObject(jsonObject.getString("contactInfo"));
                //Devolvemos dentro del campo contactInfo el nombre completo.
                String nombre = job.getString("fullName");
                //textView.setText(nombre);
                textView.setText(s);//representamos el JSON entero.

                //TODO Trabajar mejor el JSON devuelto, pudiendo representar mejor los campos devueltos
                //TODO añadir Scroll.


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
