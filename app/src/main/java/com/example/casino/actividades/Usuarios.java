package com.example.casino.actividades;

import java.io.Serializable;

public class Usuarios implements Serializable {

    private String usuario;
    private String edad;
    private String credito;
    private double dineros;

    public Usuarios(String usuario, String edad, String credito) {
        this.usuario = usuario;
        this.edad = edad;
        this.credito = credito;
    }

    public Usuarios() {

    }

    public String getUsuario() {

        return usuario;
    }

    public void setUsuario(String usuario) {

        this.usuario = usuario;
    }

    public String getEdad() {

        return edad;
    }

    public void setEdad(String edad) {

        this.edad = edad;
    }

    public String getCredito() {

        return credito;
    }

    public void setCredito(String credito) {

        this.credito = credito;
    }

    public double IncrementarSaldo(double cantidad){

        dineros+=cantidad;
        credito=String.valueOf(dineros);

        return Double.parseDouble(credito);
    }

    public double DecrementarSaldo(double cantidad){
        dineros-=cantidad;
        credito=String.valueOf(dineros);

        return Double.parseDouble(credito);
    }

    public boolean SaldoDisponible(double cantidad){
        return (cantidad<=Double.parseDouble(credito));
    }
}
