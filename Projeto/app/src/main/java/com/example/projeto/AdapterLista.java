package com.example.projeto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterLista extends ArrayAdapter<Lista> {
    private final Context context;
    private final List<Lista> listaListas;

    public AdapterLista(Context context, List<Lista> listaListas){
        super(context, R.layout.linha_lista, listaListas);
        this.context = context;
        this.listaListas = listaListas;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha_lista = inflater.inflate(R.layout.linha_lista, parent, false);

        TextView tv_nomeLista = linha_lista.findViewById(R.id.tv_nomeLista);
        tv_nomeLista.setText(listaListas.get(position).getNome());
        return linha_lista;
    }

}
