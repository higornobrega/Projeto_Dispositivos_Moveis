package com.example.projeto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListaDAO dao;
    private List<Lista> listas;
    private List<Lista> listasFiltradas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView_Lista);

        dao = new ListaDAO(this);

        listas = dao.obterTodos();

        listasFiltradas.addAll(listas);

        AdapterLista adapter = new AdapterLista(this, listas);
        listView.setAdapter(adapter);
//        System.out.println("1---->>1PASSOU AQUI1<<-----1");
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                System.out.println("2---->>2PASSOU AQUI2<<-----2");
//                Lista listaSelecionada = (Lista) adapterView.getItemAtPosition(i);
//                Toast.makeText(MainActivity.this, listaSelecionada.getNome(), Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void setup() {

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

//    private List<Lista> gerarDados() {
//        //ArrayList<Lista> item = new ArrayList<Lista>();
//        dao = new ListaDAO(this);
//        listas = dao.obterTodos();
//        listasFiltradas.addAll(listas);
//        return listas;
//    }
}
