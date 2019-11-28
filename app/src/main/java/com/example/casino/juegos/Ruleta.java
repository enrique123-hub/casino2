package com.example.casino.juegos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.casino.R;
import com.example.casino.SQLite_OpenHelper.SQLite_OpenHelper;

import org.w3c.dom.Text;

import java.util.Random;

public class Ruleta extends AppCompatActivity {
    private Button boton;
    private TextView txt, et_credito;
    private ImageView ic_rueda;
    private EditText et_usuario,et_apuest,et_numer;
    private int numero=0,apuesta=0,dinero=0,n=0,nn=0,i=0,j=35;


    Random rand;
    int degree=0, degreeold=0;


    private static final float FACTOR=4.86f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);
        init();

        rand=new Random();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado();
            }
        });
    }

    private void init() {
        boton=(Button) findViewById(R.id.btn_girar);
        txt=(TextView) findViewById(R.id.txt);
        ic_rueda=(ImageView) findViewById(R.id.ic_rule);
        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_credito = (TextView) findViewById(R.id.et_credito);
        et_apuest=findViewById(R.id.et_apuest);
        et_numer=findViewById(R.id.et_numero);
    }

    private void juego(){
        degreeold=degree%360;
        degree=rand.nextInt(3600)+720;
        RotateAnimation rotate=new RotateAnimation(degreeold,degree,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                txt.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String res=currentNumber(360-(degree%360));
                txt.setText(res);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ic_rueda.startAnimation(rotate);
    }

    private void resultado(){
        apuesta=Integer.parseInt(et_apuest.getText().toString());
        dinero=Integer.parseInt(et_credito.getText().toString());
        numero=Integer.parseInt(et_numer.getText().toString());

        if(numero<i && numero>j){
            Toast.makeText(getApplicationContext(),"Valor no valido",Toast.LENGTH_LONG).show();
        }
        else {
            if (apuesta > dinero && dinero >= 0) {
                Toast.makeText(getApplicationContext(), "no tienes credito suficiente", Toast.LENGTH_LONG).show();
            } else {
                juego();
                if (numero == nn) {
                    dinero += apuesta;
                } else {
                    dinero -= apuesta;
                }
                et_credito.setText(String.valueOf(dinero));
            }

        }              }

    private String currentNumber(int degree){

        String text="";

        if(degree >=(FACTOR*1) && degree<=(FACTOR*3)){
            text="32 Rojo";
            n=32;
        }
        if(degree >=(FACTOR*3) && degree<=(FACTOR*5)){
            text="15 Negro";
            n=15;
        }
        if(degree >=(FACTOR*5) && degree<=(FACTOR*7)){
            text="19 Rojo";
            n=19;
        }
        if(degree >=(FACTOR*7) && degree<=(FACTOR*9)){
            text="4 Negro";
            n=4;
        }
        if(degree >=(FACTOR*9) && degree<=(FACTOR*11)){
            text="21 Rojo";
            n=21;
        }
        if(degree >=(FACTOR*11) && degree<=(FACTOR*13)){
            text="2 Negro";
            n=2;
        }
        if(degree >=(FACTOR*13) && degree<=(FACTOR*15)){
            text="25 Rojo";
            n=25;
        }
        if(degree >=(FACTOR*15) && degree<=(FACTOR*17)){
            text="17 Negro";
            n=17;
        }
        if(degree >=(FACTOR*17) && degree<=(FACTOR*19)){
            text="34 Rojo";
            n=34;
        }
        if(degree >=(FACTOR*19) && degree<=(FACTOR*21)){
            text="6 Negro";
            n=6;
        }
        if(degree >=(FACTOR*21) && degree<=(FACTOR*23)){
            text="27 Rojo";
            n=27;
        }
        if(degree >=(FACTOR*23) && degree<=(FACTOR*25)){
            text="13 Negro";
            n=13;
        }
        if(degree >=(FACTOR*25) && degree<=(FACTOR*27)){
            text="36 Rojo";
            n=36;
        }
        if(degree >=(FACTOR*27) && degree<=(FACTOR*29)){
            text="11 Negro";
            n=11;
        }
        if(degree >=(FACTOR*29) && degree<=(FACTOR*31)){
            text="30 Rojo";
            n=30;
        }
        if(degree >=(FACTOR*31) && degree<=(FACTOR*33)){
            text="8 Negro";
            n=8;
        }
        if(degree >=(FACTOR*33) && degree<=(FACTOR*35)){
            text="23 Rojo";
            n=23;
        }
        if(degree >=(FACTOR*35) && degree<=(FACTOR*37)){
            text="10 Negro";
            n=10;
        }
        if(degree >=(FACTOR*37) && degree<=(FACTOR*39)){
            text="6 Rojo";
            n=6;
        }
        if(degree >=(FACTOR*39) && degree<=(FACTOR*41)){
            text="24 Negro";
            n=24;
        }
        if(degree >=(FACTOR*41) && degree<=(FACTOR*43)){
            text="16 Rojo";
            n=16;
        }
        if(degree >=(FACTOR*43) && degree<=(FACTOR*45)){
            text="33 Negro";
            n=33;
        }
        if(degree >=(FACTOR*45) && degree<=(FACTOR*47)){
            text="1 Rojo";
            n=1;
        }
        if(degree >=(FACTOR*47) && degree<=(FACTOR*49)){
            text="20 Negro";
            n=20;
        }
        if(degree >=(FACTOR*49) && degree<=(FACTOR*51)){
            text="14 Rojo";
            n=14;
        }
        if(degree >=(FACTOR*51) && degree<=(FACTOR*53)){
            text="31 Negro";
            n=31;
        }
        if(degree >=(FACTOR*53) && degree<=(FACTOR*55)){
            text="9 Rojo";
            n=8;
        }
        if(degree >=(FACTOR*55) && degree<=(FACTOR*57)){
            text="22 Negro";
            n=22;
        }
        if(degree >=(FACTOR*57) && degree<=(FACTOR*59)){
            text="18 Rojo";
            n=18;
        }
        if(degree >=(FACTOR*59) && degree<=(FACTOR*61)){
            text="29 Negro";
            n=29;
        }
        if(degree >=(FACTOR*61) && degree<=(FACTOR*63)){
            text="7 Rojo";
            n=7;
        }
        if(degree >=(FACTOR*63) && degree<=(FACTOR*65)){
            text="28 Negro";
            n=28;
        }
        if(degree >=(FACTOR*65) && degree<=(FACTOR*67)){
            text="12 rojo";
            n=12;
        }
        if(degree >=(FACTOR*67) && degree<=(FACTOR*69)){
            text="35 Negro";
            n=35;
        }
        if(degree >=(FACTOR*69) && degree<=(FACTOR*71)){
            text="3 Rojo";
            n=3;
        }
        if(degree >=(FACTOR*71) && degree<=(FACTOR*73)){
            text="26 Negro";
            n=26;
        }

        if((degree>=(FACTOR*73)&& degree< 360 || (degree>=0 && degree<(FACTOR*1)))){
            text="0 Verde";
            n=0;

        }
        nn=n;
        return text;
    }
    public void regre (View view){
        Intent regre = new Intent(this, Juegos.class);
        startActivity(regre);
        modificador();
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

            int modi = db.update("persona", modifica, "usuario = '"+usuario+"'", null);

            db.close();




        }
    }


}