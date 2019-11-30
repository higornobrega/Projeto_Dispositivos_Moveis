package com.example.projeto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AdapterLista extends ArrayAdapter<Lista> {
    private TextView tv_nomeLista;
    private ImageButton bti_editar;
    private ImageButton bti_compartilhar;
    private ImageButton bti_excluir;
    private final Context context;
    private final List<Lista> listaListas;


    public AdapterLista(Context context, List<Lista> listaListas) {
        super(context, R.layout.linha_lista, listaListas);
        this.context = context;
        this.listaListas = listaListas;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha_lista = inflater.inflate(R.layout.linha_lista, parent, false);
//        setup(linha_lista);
//        configClic();

        TextView tv_nomeLista = linha_lista.findViewById(R.id.tv_nomeLista);
        tv_nomeLista.setText(listaListas.get(position).getNome());
        return linha_lista;
    }


//    public void setup(View linha_lista){
//        tv_nomeLista = linha_lista.findViewById(R.id.tv_nomeLista);
//        bti_editar = linha_lista.findViewById(R.id.bti_editar);
//        bti_compartilhar = linha_lista.findViewById(R.id.bti_compartilhar);
//        bti_excluir = linha_lista.findViewById(R.id.bti_excluir);

//    }
//    public void configClic(){
//        tv_nomeLista.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("->>>>>>>PASSOU AQUI<<<<<<<<<<<<<<-");
//            }
//        });
//        bti_editar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("->>>>>>>PASSOU AQUI EDITAR<<<<<<<<<<<<<<-");
//            }
//        });
//        bti_compartilhar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("->>>>>>>PASSOU AQUI COMPARTILHAR<<<<<<<<<<<<<<-");
//            }
//        });
////        bti_excluir.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////            }
////        });
//    }
//    public
//    private void excluir(MenuItem item) {
//
//    }

}
