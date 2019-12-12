package com.example.projeto.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projeto.Lista.CadastroLista;
import com.example.projeto.Lista.Lista;
import com.example.projeto.Lista.ListaDAO;
import com.example.projeto.R;

public class CadastrarItem extends AppCompatActivity {
    private EditText nb_quant;
    private TextView tv_id_lista;
    private EditText et_nome_item;
    private EditText nb_val;
    private CheckBox cb_comprado;
    private Button salvar_it;
    private Item itemAtualizar = null;
    private Item item = new Item();
    private ItemDAO dao;
    private ListaDAO dao_lista;
    private Lista lista = null;
    private Integer idDefault = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item);
        setup();
        configBut();
        carregarID();
        dao = new ItemDAO(this);
        Intent i = getIntent();
        if (i.hasExtra("lista")){
            lista = (Lista) i.getSerializableExtra("lista");
            Integer a = lista.getId();
            String b = Integer.toString(a);
            tv_id_lista.setText(lista.getNome().toString());
        }

    }

    private void configBut() {
        salvar_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {
        try {

                //Item item = new Item();
                //itemAtualizar = new Item();
                String nome_item = et_nome_item.getText().toString();
                String quantStr = nb_quant.getText().toString();
                String valStr = nb_val.getText().toString();
                Float val = Float.parseFloat(valStr);
                Integer quantInt = Integer.parseInt(quantStr);
                String cp = cb_comprado.getText().toString();
                boolean cp2 = Boolean.getBoolean(cp);
                //nome_item varchar(50), val varchar(50), id_lista integer, FOREIGN KEY (id_lista) REFERENCES lista(id))
                item.setQuant(quantInt);
                item.setNome_item(nome_item);
                item.setVal(val);
                //item.setComprado(cp2);

                Intent i = getIntent();
                lista = (Lista) i.getSerializableExtra("lista");
                item.setId_lista(lista.getId());
                dao.inserir(item);
                finish();

//            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao Salvar Item", Toast.LENGTH_LONG).show();
        }

//        dao = new ItemDAO(this);
//        Toast.makeText(this, "Aluno inserido com Sucesso: " + id, Toast.LENGTH_SHORT)
//                .show();
    }

    private void setup() {
        nb_quant = findViewById(R.id.nb_quant);
        et_nome_item = findViewById(R.id.et_nome_item);
        nb_val = findViewById(R.id.nb_val);
        cb_comprado = findViewById(R.id.cb_comprado);
        salvar_it = findViewById(R.id.salvar_it);
        tv_id_lista = findViewById(R.id.tv_id_lista);
        //dao_lista = new ListaDAO(CadastroLista.class);
        //dao = new ItemDAO(this);
    }
    private void carregarID() {
        Intent intent = getIntent();

        Integer id = intent.getIntExtra("id_lista", -1);
        idDefault = id;
    }
}
