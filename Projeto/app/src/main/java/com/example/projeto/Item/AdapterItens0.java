package com.example.projeto.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projeto.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterItens0  extends ArrayAdapter<Item> {
    private TextView tv_nomeItem;
    private TextView tv_quant;
    private TextView tv_val;
    private final Context context;
    private final List<Item> listaItem;

    private double totalLista = 0;

    public double getTotalLista() {
        //setTotalLista(50);
        return totalLista;
    }
    public AdapterItens0 (Context context, List<Item> listaItem) {
        super(context, R.layout.linha_val0, listaItem);
        this.context = context;
        this.listaItem = listaItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha_item = inflater.inflate(R.layout.linha_val0, parent, false);

        tv_nomeItem = linha_item.findViewById(R.id.tv_val0);
        tv_nomeItem.setText(listaItem.get(position).getNome_item());
//        tv_val.setText("R$" + formatter.format(v));
//        tv_quant.setText(b);
//        tv_nomeItem.setText(listaItem.get(position).getNome_item());
        return linha_item;
    }

}
