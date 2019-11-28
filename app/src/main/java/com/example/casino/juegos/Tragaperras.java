package com.example.casino.juegos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.casino.R;
import com.example.casino.SQLite_OpenHelper.SQLite_OpenHelper;

import java.util.Random;

public class Tragaperras extends AppCompatActivity {

    private ImageView campo1,campo2,campo3;
    private Button boton;
    private TextView ganancia, et_credito;
    private RelativeLayout relative;
    private EditText et_usuario,et_apuesta;
    private Random nran;
    private int ncampo1, ncampo2, ncampo3, nganancia=0,dinero=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tragaperras);
        init();
        logic();

    }

    private void logic() {
        nran=new Random();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jugar();
            }
        });
    }

    private void init() {
        campo1=findViewById(R.id.campod1);
        campo2=findViewById(R.id.campod2);
        campo3=findViewById(R.id.campo3);
        boton=findViewById(R.id.btn_lanzar);
        relative=findViewById(R.id.mainActivityRldad);
        et_credito = findViewById(R.id.et_credito);
        et_usuario = findViewById(R.id.et_usuario);
        et_apuesta=findViewById(R.id.et_apuesta);
    }

    private void dineroAcumulado() {
        nganancia=Integer.parseInt(et_apuesta.getText().toString());
        dinero=Integer.parseInt(et_credito.getText().toString());
        if ((ncampo1==ncampo2)&&(ncampo1==ncampo3)){
            dinero+=(nganancia*2);
        }
        else if((ncampo1 == ncampo2)||(ncampo1 == ncampo3)||(ncampo3 == ncampo2)){
            dinero+=nganancia;
        }else {
            dinero -= nganancia;
              }
        et_credito.setText(String.valueOf(dinero));
    }


    private void jugar(){
        nganancia=Integer.parseInt(et_apuesta.getText().toString());
        dinero=Integer.parseInt(et_credito.getText().toString());
        if (nganancia>dinero && dinero>=0){
            Toast.makeText(getApplicationContext(),"no tienes credito suficiente",Toast.LENGTH_LONG).show();
        }
        else{
            campo1.setImageResource(R.drawable.animation);
            final AnimationDrawable campo1anim=(AnimationDrawable) campo1.getDrawable();
            campo1anim.start();

            campo2.setImageResource(R.drawable.animation);
            final AnimationDrawable campo2anim=(AnimationDrawable) campo2.getDrawable();
            campo2anim.start();

            campo3.setImageResource(R.drawable.animation);
            final AnimationDrawable campo3anim=(AnimationDrawable) campo3.getDrawable();
            campo3anim.start();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    campo1anim.stop();
                    campo2anim.stop();
                    campo3anim.stop();

                    ponerImagenes();
                    dineroAcumulado();
                }
            },1000);
        }
    }
    private void ponerImagenes(){
        ncampo1=nran.nextInt(5);
        ncampo2=nran.nextInt(5);
        ncampo3=nran.nextInt(5);
        switch (ncampo1){
            case 0:
                campo1.setImageResource(R.drawable.ic_cascos);
                break;
            case 1:
                campo1.setImageResource(R.drawable.ic_gamepad);
                break;
            case 2:
                campo1.setImageResource(R.drawable.ic_mouse);
                break;
            case 3:
                campo1.setImageResource(R.drawable.ic_teclado);
                break;
            case 4:
                campo1.setImageResource(R.drawable.ic_videogame);
                break;
        }
        switch (ncampo2){
            case 0:
                campo2.setImageResource(R.drawable.ic_cascos);
                break;
            case 1:
                campo2.setImageResource(R.drawable.ic_gamepad);
                break;
            case 2:
                campo2.setImageResource(R.drawable.ic_mouse);
                break;
            case 3:
                campo2.setImageResource(R.drawable.ic_teclado);
                break;
            case 4:
                campo2.setImageResource(R.drawable.ic_videogame);
                break;
        }
        switch (ncampo3){
            case 0:
                campo3.setImageResource(R.drawable.ic_cascos);
                break;
            case 1:
                campo3.setImageResource(R.drawable.ic_gamepad);
                break;
            case 2:
                campo3.setImageResource(R.drawable.ic_mouse);
                break;
            case 3:
                campo3.setImageResource(R.drawable.ic_teclado);
                break;
            case 4:
                campo3.setImageResource(R.drawable.ic_videogame);
                break;
        }
    }
    public void regre (View view){
        modificador();
        Intent regre = new Intent(this, Juegos.class);
        startActivity(regre);
    }

    public void buscador (View view){
        SQLite_OpenHelper busca = new SQLite_OpenHelper(this, "casino", null, 1);
        SQLiteDatabase db = busca.getWritableDatabase();

        String usuario = et_usuario.getText().toString();

        if(!usuario.isEmpty()){
            Cursor cursor = db.rawQuery("select edad, credito from persona where usuario ='"+usuario+"'", null);

            if(cursor.moveToFirst()){
                et_credito.setText(cursor.getString(1));

                db.close();
            }else {
                Toast.makeText(getApplicationContext(), "Usuario no existente", Toast.LENGTH_SHORT).show();
                et_usuario.setText("");
                et_credito.setText("");
                db.close();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debes de introducir un usuario", Toast.LENGTH_SHORT).show();
        }
    }
    public void modificador (){
        SQLite_OpenHelper modificar = new SQLite_OpenHelper(this, "casino", null, 1);
        SQLiteDatabase db = modificar.getWritableDatabase();

        String usuario = et_usuario.getText().toString();
        String credito = et_credito.getText().toString();

        if( !usuario.isEmpty() && !credito.isEmpty()){

            ContentValues modifica = new ContentValues();

            modifica.put("credito", credito);
            modifica.put("usuario", usuario);

            int modi = db.update("persona", modifica, "usuario = '"+usuario+"'", null);

            db.close();
        }
    }



}