package com.example.projeto.Lista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projeto.Conexao;

import java.util.ArrayList;
import java.util.List;

public class ListaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;
    private SQLiteDatabase dbInstance = null;

    public ListaDAO(Context contexto){
        conexao = new Conexao(contexto);
        banco = conexao.getWritableDatabase();
    }
    public long inserir(Lista lista){
        ContentValues values = new ContentValues();
        values.put("nome", lista.getNome());
        //values.put("nota", lista.getNota());
        return banco.insert("lista",null, values);
    }


    public List<Lista> obterTodos(){
        List<Lista> listas = new ArrayList<>();
        Cursor cursor = banco.query("lista", new  String[]{"id","nome"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Lista a = new Lista();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
//            a.setNota(cursor.getString(2));
            listas.add(a);
        }
        return listas;
    }

//    public List<Lista> obterTodos() {
//        List<Lista> listas = new ArrayList<>();
//        dbInstance = conexao.getWritableDatabase();
//        Cursor cursor = dbInstance.rawQuery("SELECT * FROM lista", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Lista n = new Lista();
//            n.setId(cursor.getInt(0));
//            n.setNome(cursor.getString(1));
//            n.setNota(cursor.getString(2));
//            listas.add(n);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return listas;
//    }


//    public List<Lista> obterTodos(){
//        List<Lista> listas = new ArrayList<>();
//        Cursor cursor = banco.query("lista", new String[]{"id","nome","nota"},
//                null, null, null, null, null);
//        while (cursor.moveToNext()){
//            Lista a = new Lista();
//            a.setId(cursor.getInt(0));
//            a.setNome(cursor.getString(1));
//            a.setNota(cursor.getString(2));
//        }
//        return listas;
//    }

    public void excluir(Lista a) {
        banco.delete("lista","id = ?", new String[]{a.getId().toString()});

    }

    public void atulizar(Lista lista){
        ContentValues values = new ContentValues();
        values.put("nome", lista.getNome());
//        values.put("nota", lista.getNota());
        banco.update("lista", values, "id = ?", new String[]{lista.getId().toString()});

    }
}
