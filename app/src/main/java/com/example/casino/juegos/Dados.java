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
import com.example.casino.actividades.Usuarios;

import java.util.Random;

public class Dados extends AppCompatActivity {

    private Button lanzar;
    private ImageView campod1,campo2d;
    private EditText creditaje,et_apuest;
    private RelativeLayout rolativo;
    private int suma;
    private EditText et_usuario;
    private Random numran;
    private int ncampd1,ncampd2;
    private int ncredit,dinero=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        init();
        logic();
    }
    private void logic() {
        lanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jugars();
            }
        });
    }

    public void jugars(){
        ncredit=Integer.parseInt(creditaje.getText().toString());
        dinero=Integer.parseInt(et_apuest.getText().toString());
        if (dinero>ncredit && ncredit>=0 ){
            Toast.makeText(getApplicationContext(),"no tienes credito suficiente",Toast.LENGTH_LONG).show();
        }
        else {
            campod1.setImageResource(R.drawable.animationdado);
            final AnimationDrawable campod1anim = (AnimationDrawable) campod1.getDrawable();
            campod1anim.start();

            campo2d.setImageResource(R.drawable.animationdado);
            final AnimationDrawable campod2anim = (AnimationDrawable) campo2d.getDrawable();
            campod2anim.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    campod1anim.stop();
                    campod2anim.stop();
                    ponerImagenes();
                }
            }, 1000);
        }
    }
    private void ponerImagenes() {
        ncredit=Integer.parseInt(creditaje.getText().toString());
        dinero=Integer.parseInt(et_apuest.getText().toString());
        numran=new Random();

        Dado d1=new Dado();
        Dado d2=new Dado();
        ncampd1=d1.LanzarDado();
        ncampd2=d2.LanzarDado();
        switch (ncampd1){
            case 1:
                campod1.setImageResource(R.drawable.du);
                break;
            case 2:
                campod1.setImageResource(R.drawable.dd);
                break;
            case 3:
                campod1.setImageResource(R.drawable.dt);
                break;
            case 4:
                campod1.setImageResource(R.drawable.dc);
                break;
            case 5:
                campod1.setImageResource(R.drawable.dci);
                break;
            case 6:
                campod1.setImageResource(R.drawable.ds);
                break;

        }

        switch (ncampd2){
            case 1:
                campo2d.setImageResource(R.drawable.du);
                break;
            case 2:
                campo2d.setImageResource(R.drawable.dd);
                break;
            case 3:
                campo2d.setImageResource(R.drawable.dt);
                break;
            case 4:
                campo2d.setImageResource(R.drawable.dc);
                break;
            case 5:
                campo2d.setImageResource(R.drawable.dci);
                break;
            case 6:
                campo2d.setImageResource(R.drawable.ds);
                break;
        }
        suma=ncampd1+ncampd2;

        Usuarios usuarios=new Usuarios();
        if (suma == 7){
            Toast.makeText(getApplicationContext(),"Ganaste",Toast.LENGTH_SHORT).show();
            ncredit+=usuarios.IncrementarSaldo(dinero);
        }
        else{
            Toast.makeText(getApplicationContext(),"Perdiste",Toast.LENGTH_SHORT).show();
            ncredit-=dinero;
        }
        creditaje.setText(String.valueOf(ncredit));


    }

    private void init() {
        campod1=findViewById(R.id.campod1);
        campo2d= findViewById(R.id.campod2);
        lanzar= findViewById(R.id.btn_lanzar);
        creditaje = findViewById(R.id.et_credito);
        et_usuario = findViewById(R.id.et_usuario);
        rolativo=findViewById(R.id.mainActivityRldad);
        et_apuest=findViewById(R.id.et_apues);
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
                creditaje.setText(cursor.getString(1));

                db.close();
            }else {
                Toast.makeText(getApplicationContext(), "Usuario no existente", Toast.LENGTH_SHORT).show();
                et_usuario.setText("");
                creditaje.setText("");
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
        String credito = creditaje.getText().toString();

        if( !usuario.isEmpty() && !credito.isEmpty()){

            ContentValues modifica = new ContentValues();

            modifica.put("credito", credito);
            modifica.put("usuario", usuario);

            int modi = db.update("persona", modifica, "usuario = '"+usuario+"'", null);

            db.close();

            creditaje.setText("");


        }
    }

}