package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroLista extends AppCompatActivity {
    private EditText et_nome_lista;
    private Button bt_salvar_lista;
    private ListaDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lista);
        setup();
        configButao();
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
        dao = new ListaDAO(this);
    }
    public void salvar(){
        Lista a = new Lista();
        a.setNome(et_nome_lista.getText().toString());
        long id = dao.inserir(a);
        Toast.makeText(this, "Lista inserida com id: " + id, Toast.LENGTH_SHORT)
                .show();
    }

}
