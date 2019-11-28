package com.example.casino.actividades;

public class Utilidades {

    public static final String TABLA_USUARIO = "persona";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_EDAD = "edad";
    public static final String CAMPO_CREDITO = "credito";

    public static final String CREATE_TABLA_USUARIO = "CREATE TABLE "+
            ""+TABLA_USUARIO+"("+CAMPO_USUARIO+" " +
            "TEXT, "+CAMPO_EDAD+" TEXT, " +CAMPO_CREDITO+" TEXT)";

}
