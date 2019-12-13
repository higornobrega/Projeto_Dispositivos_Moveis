package com.example.projeto.Item;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projeto.Lista.AdapterLista;
import com.example.projeto.Lista.CadastroLista;
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
    AdapterItem adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_item);
        setup();
        carregarDados();

        dao = new ItemDAO(this);

        itens = dao.obterTodos(id_lista);

        itemsFiltrados.addAll(itens);

        adapter = new AdapterItem(this, itemsFiltrados);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);


    }

    public double percorrer(){
        double c = 0.0;
        double a = 0.0;
        int b = 0;
        for (Item it: itens){
            a = it.getVal() * it.getQuant();
            c = a + c;
        }
        return c;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_item, menu);
    }

//    public void atualizarItem(MenuItem item){
//        AdapterView.AdapterContextMenuInfo menuInfo =
//                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        final Item itemAtualizar = itemsFiltrados.get(menuInfo.position);
//        Intent it = new Intent(this, CadastrarItem.class);
//        it.putExtra("item", itemAtualizar);
//        startActivity(it);
//    }

    public void excluirItem(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Item itemExcluir = itemsFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o item?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemsFiltrados.remove(itemExcluir);
                        itens.remove(itemExcluir);
                        dao.excluir(itemExcluir);
                        listView.invalidateViews();
                        finish();

                    }
                }).create();
        dialog.show();
    }


    @Override
    protected void onResume() {

        super.onResume();
        setTitle("Total da lista R$" + percorrer() + "");
        itens = dao.obterTodos(id_lista);
        itemsFiltrados.clear();
        itemsFiltrados.addAll(itens);
        listView.invalidateViews();
    }
    private void setup() {
        listView = findViewById(R.id.listView_Item);
    }
    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Item itemAtualizar = itemsFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastrarItem.class);
        it.putExtra("item", itemAtualizar);
        startActivity(it);
    }
    private void carregarDados() {
        Intent intent = getIntent();

        Integer id = intent.getIntExtra("id", -1);
        id_lista = id;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_gambiarra,menu);
        return true;
    }
    public void procuraItem0(String nome){
        itemsFiltrados.clear();
        for (Item i: itens){
//            Float a = i.getVal();
//            String aa = a.toString();
            if (i.getNome_item().toLowerCase().contains(nome.toLowerCase())){
                itemsFiltrados.add(i);
            }
            listView.invalidateViews();

        }

    }
    public void listaItem0(MenuItem item){
        Intent i = new Intent(this, ListaDosComValor0.class);
        //i.putExtra("item", Item);
        startActivity(i);
    }
}
