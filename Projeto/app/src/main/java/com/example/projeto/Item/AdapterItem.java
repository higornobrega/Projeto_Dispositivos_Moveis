package com.example.projeto.Item;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projeto.Lista.Lista;
import com.example.projeto.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterItem extends ArrayAdapter<Item> {
    private TextView tv_nomeItem;
    private TextView tv_idLista;
    private final Context context;
    private final List<Item> listaItem;

    public AdapterItem(Context context, List<Item> listaItem) {
        super(context, R.layout.linha_item, listaItem);
        this.context = context;
        this.listaItem = listaItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha_item = inflater.inflate(R.layout.linha_item, parent, false);
        tv_idLista = linha_item.findViewById(R.id.tv_idLista);


//        String nome_item = et_nome_item.getText().toString();
//        String quantStr = nb_quant.getText().toString();
//        String valStr = nb_val.getText().toString();
//        Float val = Float.parseFloat(valStr);
//        Integer quantInt = Integer.parseInt(quantStr);

        tv_nomeItem = linha_item.findViewById(R.id.tv_nomeItem);
        Integer a = listaItem.get(position).getId_lista();
        String b = a.toString();
        tv_idLista.setText(b);
        tv_nomeItem.setText(listaItem.get(position).getNome_item());
        return linha_item;
    }
}

//        Item i = new Item();
//        Integer a = listaItem.get(position).getId_lista();
//        String b = Integer.toString(a);
//
//        tv_nomeItem.setText(b);