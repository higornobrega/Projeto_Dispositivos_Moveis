package com.example.projeto.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projeto.Lista.Lista;
import com.example.projeto.R;

import java.util.ArrayList;
import java.util.List;

public class ListaDosComValor0 extends AppCompatActivity {
    private ListView listView;
    private ItemDAO dao;
    private List<Item> itens;
    private List<Item> itemsFiltrados = new ArrayList<>();
    private Integer id_lista;
    AdapterItem adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dos_com_valor0);
        setup();
        carregarDados();

        dao = new ItemDAO(this);

        itens = dao.obterTodosCom0();

        itemsFiltrados.addAll(itens);

        adapter = new AdapterItem(this, itemsFiltrados);
        listView.setAdapter(adapter);
    }
    private void setup() {
        listView = findViewById(R.id.listView_val0);
    }
    private void carregarDados() {
        Intent intent = getIntent();

        Integer id = intent.getIntExtra("id", -1);
        id_lista = id;
        dao = new ItemDAO(this);

        itens = dao.obterTodosCom0();

        itemsFiltrados.addAll(itens);

        adapter = new AdapterItem(this, itemsFiltrados);
        listView.setAdapter(adapter);

    }
    protected void onResume() {

        super.onResume();
        itens = dao.obterTodosCom0();
        itemsFiltrados.clear();
        itemsFiltrados.addAll(itens);
        listView.invalidateViews();
    }

}
