package com.example.casino.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.casino.R;
import com.example.casino.SQLite_OpenHelper.SQLite_OpenHelper;
import com.example.casino.actividades.Usuarios;
import com.example.casino.actividades.Utilidades;

import java.util.ArrayList;

public class Clientes extends AppCompatActivity {

    private ListView lista_personas;

    ArrayList<String> listaInformacion;
    ArrayList<Usuarios> listaUsuarios;

    SQLite_OpenHelper cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        cliente = new SQLite_OpenHelper(getApplicationContext(), "casino", null, 1);

        lista_personas = (ListView) findViewById(R.id.lista_personas);

        consultarListaPeronas();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        lista_personas.setAdapter(adapter);
    }

    public void regresar (View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

    private void consultarListaPeronas() {
        SQLiteDatabase db = cliente.getReadableDatabase();

        Usuarios usuarios = null;
        listaUsuarios= new ArrayList<Usuarios>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()){
            usuarios= new Usuarios();
            usuarios.setUsuario(cursor.getString(0));
            usuarios.setEdad(cursor.getString(1));
            usuarios.setCredito(cursor.getString(2));
            listaUsuarios.add(usuarios);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0; i<listaUsuarios.size(); i++){
            listaInformacion.add(listaUsuarios.get(i).getUsuario()+"          "
            +listaUsuarios.get(i).getEdad()+"          " +listaUsuarios.get(i).getCredito());
        }
    }

}
