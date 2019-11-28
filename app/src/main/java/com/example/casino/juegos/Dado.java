package com.example.casino.juegos;

public class Dado {

    public int n;

    public Dado() {
    }

    public int LanzarDado(){
        while(n<1 || n>6){
            n=(int)(Math.random()*10);
        }
        return n;
    }
}