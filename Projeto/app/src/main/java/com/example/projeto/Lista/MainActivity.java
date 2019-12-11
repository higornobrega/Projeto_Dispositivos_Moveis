package com.example.projeto.Lista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.projeto.Item.CadastrarItem;
import com.example.projeto.Item.ListarItem;
import com.example.projeto.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListaDAO dao;
    private ListView listView;
    private List<Lista> listas;
    private List<Lista> listasFiltradas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        dao = new ListaDAO(this);

        listas = dao.obterTodos();

        listasFiltradas.addAll(listas);

        AdapterLista adapter = new AdapterLista(this, listasFiltradas);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lista listaSelecionada = (Lista) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ListarItem.class);
                intent.putExtra("id", listaSelecionada.getId());
                startActivity(intent);
            }
        });

    }

    private void setup() {
        listView = findViewById(R.id.listView_Lista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.it_sair){
            sair();
            return true;
        }else if (id == R.id.it_adicionar_lista){
            novaLista();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void novaLista() {
        Intent i = new Intent(this,CadastroLista.class);
        startActivity(i);
    }

//    public void mostrarItem(){
//        Intent i = new Intent(this,ListarItem.class);
//        startActivity(i);
//    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void sair() {
        finish();
    }

    public void onResume(){
        super.onResume();
        listas = dao.obterTodos();
        listasFiltradas.clear();
        listasFiltradas.addAll(listas);
        listView.invalidateViews();
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto,menu);
    }

//    public void adicionarItem(MenuItem item){
//        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        final Lista listaAtualizar = listasFiltradas.get(menuInfo.position);
//        Intent i = new Intent(this, nota.class);
//        i.putExtra("lista", listaAtualizar);
//        startActivity(i);
//    }
    public void listaItem(MenuItem item){
    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    final Lista listaAtualizar = listasFiltradas.get(menuInfo.position);
    Intent i = new Intent(this, CadastrarItem.class);
    i.putExtra("lista", listaAtualizar);
    startActivity(i);
}

    public void mostrarItem(MenuItem item){
        Intent i = new Intent(this,ListarItem.class);
        startActivity(i);
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Lista listaAtualizar = listasFiltradas.get(menuInfo.position);
        Intent i = new Intent(this, CadastroLista.class);
        i.putExtra("lista", listaAtualizar);
        startActivity(i);
    }
    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Lista listaExcluir = listasFiltradas.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Tem certesa que deseja excluir essa lista?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listasFiltradas.remove(listaExcluir);
                        listas.remove(listaExcluir);
                        dao.excluir(listaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }
//    private List<Lista> gerarDados() {
//        //ArrayList<Lista> item = new ArrayList<Lista>();
//        dao = new ListaDAO(this);
//        listas = dao.obterTodos();
//        listasFiltradas.addAll(listas);
//        return listas;
//    }
}
