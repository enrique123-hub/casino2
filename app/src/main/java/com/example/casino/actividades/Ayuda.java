package com.example.casino.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.casino.R;
import com.example.casino.actividades.MainActivity;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }
    public void ayuda (View view){
        Intent ayuda = new Intent(this, MainActivity.class);
        startActivity(ayuda);
    }
}
