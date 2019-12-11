package com.example.projeto.Item;

import android.content.ContentValues;
import android.widget.EditText;

import java.io.Serializable;

public class Item implements Serializable {
    private Integer id;
    private Integer id_lista;
    private  Integer quant;
    private String nome_item;
    private float val;
    private boolean comprado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public String getNome_item() {
        return nome_item;
    }

    public void setNome_item(String nome_item) {
        this.nome_item = nome_item;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }
    public String toString(){
        return nome_item;
    }
    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("quant", getQuant());
        cv.put("nome_item", getNome_item());
        cv.put("val", getVal());
        cv.put("id_Lista", getId_lista());
        return cv;
    }


}
