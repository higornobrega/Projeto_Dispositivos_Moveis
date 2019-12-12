package com.example.projeto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table lista(id integer primary key autoincrement, " +
                "nome varchar(50))");
        db.execSQL("create table item(id integer primary key autoincrement, " +
                "quant interger, nome_item varchar(50), val REAL, id_lista integer, FOREIGN KEY (id_lista) REFERENCES lista(id))");

    }
//, FOREIGN KEY (id_lista) REFERENCES lista(id)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}