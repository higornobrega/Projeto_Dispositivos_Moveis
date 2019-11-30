package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ListaDAO(Context contexto){
        conexao = new Conexao(contexto);
        banco = conexao.getWritableDatabase();
    }
    public long inserir(Lista lista){
        ContentValues values = new ContentValues();
        values.put("nome", lista.getNome());
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
            listas.add(a);
        }
        return listas;
    }

    public void excluir(Lista a) {
        banco.delete("lista","id = ?", new String[]{a.getId().toString()});

    }
}
