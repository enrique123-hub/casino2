package com.example.casino.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.casino.R;
import com.example.casino.SQLite_OpenHelper.SQLite_OpenHelper;
import com.example.casino.juegos.Juegos;

public class MainActivity extends AppCompatActivity {

    private EditText usuario_et, edad_et, credito_et;
    private Toolbar toolbar;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        insta();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Intent about = new Intent(this, About.class);
                startActivity(about);
            break;

            case R.id.lista:
                Intent lista = new Intent(this, Clientes.class);
                startActivity(lista);
            break;

            case R.id.ayuda:
                Intent ayuda = new Intent(this, Ayuda.class);
                startActivity(ayuda);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        usuario_et = (EditText) findViewById (R.id.usuario_et);
        edad_et = (EditText) findViewById (R.id.edad_et);
        credito_et = (EditText) findViewById (R.id.credito_et);
        toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void insta() {
        SQLite_OpenHelper base = new SQLite_OpenHelper(this, "casino", null, 1);
    }

    public void agregar (View view){
        SQLite_OpenHelper agregar = new  SQLite_OpenHelper(this, "casino", null, 1);
        SQLiteDatabase db = agregar.getWritableDatabase();

        String usuario = usuario_et.getText().toString();
        String edad = edad_et.getText().toString();
        String credito = credito_et.getText().toString();

        if(!usuario.isEmpty() && !edad.isEmpty() && !credito.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("usuario", usuario);
            registro.put("edad", edad);
            registro.put("credito", credito);

            db.insert("persona", null, registro);

            db.close();

            usuario_et.setText("");
            edad_et.setText("");
            credito_et.setText("");

            Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Debes de llenar todos los campos primero", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar (View view){
        SQLite_OpenHelper buscar = new SQLite_OpenHelper(this, "casino", null, 1);
        SQLiteDatabase db = buscar.getWritableDatabase();

        String usuario = usuario_et.getText().toString();

        if(!usuario.isEmpty()){
            Cursor cursor = db.rawQuery("select edad, credito from persona where usuario ='"+usuario+"'", null);

            if(cursor.moveToFirst()){
                edad_et.setText(cursor.getString(0));
                credito_et.setText(cursor.getString(1));

                db.close();
            }else {
                Toast.makeText(getApplicationContext(), "Usuario no existente", Toast.LENGTH_SHORT).show();
                usuario_et.setText("");
                edad_et.setText("");
                credito_et.setText("");
                db.close();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debes de introducir un usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar (View view){
        SQLite_OpenHelper modificar = new SQLite_OpenHelper(this, "casino", null, 1);
        SQLiteDatabase db = modificar.getWritableDatabase();

        String usuario = usuario_et.getText().toString();
        String edad = edad_et.getText().toString();
        String credito = credito_et.getText().toString();

        if(!usuario.isEmpty() && !edad.isEmpty() && !credito.isEmpty()){

            ContentValues modifica = new ContentValues();

            modifica.put("usuario", usuario);
            modifica.put("edad", edad);
            modifica.put("credito", credito);

            int modi = db.update("persona", modifica, "usuario = '"+usuario+"'", null);

            db.close();

            usuario_et.setText("");
            edad_et.setText("");
            credito_et.setText("");

            if(modi == 1){
                Toast.makeText(getApplicationContext(), "Modificacion exitosa", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Dato no existente", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debes de llenar los datos primero", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar (View view){
        SQLite_OpenHelper eliminar = new SQLite_OpenHelper(this, "casino", null, 1);
        SQLiteDatabase db = eliminar.getWritableDatabase();

        String usuario = usuario_et.getText().toString();

        if(!usuario.isEmpty()){

            int elim = db.delete("persona", "usuario = '"+usuario+"'", null);

            db.close();

            usuario_et.setText("");
            edad_et.setText("");
            credito_et.setText("");

            if(elim == 1){
                Toast.makeText(getApplicationContext(), "Dato eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Dato no existente", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "No ha seleccionado el dato a eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    public void juegos (View view){
        Intent juegos = new Intent(this, Juegos.class);
        startActivity(juegos);
    }

}

