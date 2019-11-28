package com.example.casino.SQLite_OpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.casino.actividades.Utilidades;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(@Nullable Context context,
                             @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory,
                             int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREATE_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        onCreate(db);
    }
}
