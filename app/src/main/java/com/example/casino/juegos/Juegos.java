package com.example.casino.juegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.casino.R;
import com.example.casino.actividades.MainActivity;

public class Juegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);
    }

    public void ruleta (View view){
        Intent ruleta = new Intent(this, Ruleta.class);
        startActivity(ruleta);
    }

    public void dados (View view){
        Intent dados = new Intent(this, Dados.class);
        startActivity(dados);
    }

    public void traga (View view){
        Intent traga = new Intent(this, Tragaperras.class);
        startActivity(traga);
    }

    public void regre (View view){
        Intent regre = new Intent(this, MainActivity.class);
        startActivity(regre);
    }
}
