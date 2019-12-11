package com.example.projeto.Lista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projeto.R;

public class CadastroLista extends AppCompatActivity {
    private EditText et_nome_lista;
    private Button bt_salvar_lista;
//    private EditText et_nota;
    private ListaDAO dao;
    private Lista lista = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lista);

        setup();
        configButao();
        Intent i = getIntent();
        if (i.hasExtra("lista")){
            lista = (Lista) i.getSerializableExtra("lista");
            et_nome_lista.setText(lista.getNome());
//            et_nota.setText(lista.getNota());
        }
    }

    public void configButao() {
        bt_salvar_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }

    private void setup() {
        et_nome_lista = findViewById(R.id.et_nome_lista);
        bt_salvar_lista = findViewById(R.id.bt_salvar_lista);
        //et_nota = findViewById(R.id.et_nota);
        dao = new ListaDAO(this);
    }
    public void salvar(){
        if (lista == null) {
            Lista lista = new Lista();
            lista.setNome(et_nome_lista.getText().toString());
//            lista.setNota(et_nota.getText().toString());
            long id = dao.inserir(lista);
            Toast.makeText(this, "Lista inserida com Sucesso  ", Toast.LENGTH_SHORT)
                    .show();
            finish();
        }else {
            lista.setNome(et_nome_lista.getText().toString());
//            lista.setNota(et_nota.getText().toString());
            if (lista.getNome().equals("")) {
                Toast.makeText(this, "Parametro inválido", Toast.LENGTH_SHORT)
                        .show();
            }else{
                dao.atulizar(lista);
                Toast.makeText(this, "Lista Atualizada com Sucesso ", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }

        }
    }

}
