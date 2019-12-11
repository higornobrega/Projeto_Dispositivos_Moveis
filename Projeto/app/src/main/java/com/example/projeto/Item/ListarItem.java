package com.example.projeto.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.projeto.Lista.AdapterLista;
import com.example.projeto.Lista.Lista;
import com.example.projeto.Lista.ListaDAO;
import com.example.projeto.R;

import java.util.ArrayList;
import java.util.List;

public class ListarItem extends AppCompatActivity {
    private ListView listView;
    private ItemDAO dao;
    private List<Item> itens;
    private List<Item> itemsFiltrados = new ArrayList<>();
    private Integer id_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_item);
        setup();
        carregarDados();
        dao = new ItemDAO(this);

        itens = dao.obterTodos(id_lista);

        itemsFiltrados.addAll(itens);

        AdapterItem adapter = new AdapterItem(this, itemsFiltrados);
        listView.setAdapter(adapter);
        //registerForContextMenu(listView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        itens = dao.obterTodos(id_lista);
        itemsFiltrados.clear();
        itemsFiltrados.addAll(itens);
        listView.invalidateViews();
    }
    private void setup() {
        listView = findViewById(R.id.listView_Item);
    }
    private void carregarDados() {
        Intent intent = getIntent();

        Integer id = intent.getIntExtra("id", -1);
        id_lista = id;
    }
}
