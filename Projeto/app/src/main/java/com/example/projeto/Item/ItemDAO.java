package com.example.projeto.Item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.projeto.Conexao;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Conexao conexao;
    private final Context context;
    private SQLiteDatabase banco = null;

    public ItemDAO(Context context){
        conexao = new Conexao(context);
        this.context = context;
    }
    public void inserir(Item item) throws SQLException {
        openDB();
        banco.insert("item", null, item.getContentValues());
        Toast.makeText(context, "Item inserida com sucesso!", Toast.LENGTH_SHORT).show();
        closeDB();
    }

//    private Integer id;
//    private Integer id_lista;
//    private  Integer quant;
//    private String nome_item;
//    private float val;
//
    public List<Item> obterTodos(Integer id_listac){
        List<Item> itens = new ArrayList<>();
        banco = conexao.getWritableDatabase();
        Cursor cursor = banco.rawQuery("SELECT * FROM item WHERE id_lista = " + id_listac, null);
//        Cursor cursor = banco.query("item", new String[]{"id", "quant", "nome_item", "val", "comprado","id_Lista"},
//                null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Item i = new Item();
            i.setId(cursor.getInt(0));
            i.setQuant(cursor.getInt(1));
            i.setNome_item(cursor.getString(2));
            i.setVal(cursor.getFloat(3));
            i.setId_lista(cursor.getInt(4));
            itens.add(i);
            cursor.moveToNext();
        }
        cursor.close();
        return itens;
    }
    public void excluir(Item i){
        banco.delete("item", "id = ?", new String[]{i.getId().toString()} );
    }
    private void openDB() throws SQLException {
        if (this.banco == null) {
            this.banco = conexao.getWritableDatabase();
        }
    }

    public void atualizar(Item item){
        ContentValues values = new ContentValues();
        //"id", "quant", "nome_item", "val", "comprado","id_Lista"
        values.put("quant", item.getQuant());
        values.put("nome_item", item.getNome_item());
        values.put("val", item.getVal());
        values.put("id_Lista", item.getId_lista());
//        values.put("nota", lista.getNota());
        banco.update("lista", values, "id = ?", new String[]{item.getId().toString()});
;
    }

    private void closeDB() throws SQLException {
        if (this.banco != null) {
            if (this.banco.isOpen()) {
                this.banco.close();
            }
        }
    }
}
