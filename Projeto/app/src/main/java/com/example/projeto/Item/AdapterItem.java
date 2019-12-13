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

public class AdapterItem extends ArrayAdapter<Item> {
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



    public AdapterItem(Context context, List<Item> listaItem) {
        super(context, R.layout.linha_item, listaItem);
        this.context = context;
        this.listaItem = listaItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha_item = inflater.inflate(R.layout.linha_item, parent, false);
        tv_quant = linha_item.findViewById(R.id.tv_idLista);
        tv_val = linha_item.findViewById(R.id.tv_val);


//        String nome_item = et_nome_item.getText().toString();
//        String quantStr = nb_quant.getText().toString();
//        String valStr = nb_val.getText().toString();
//        Float val = Float.parseFloat(valStr);
//        Integer quantInt = Integer.parseInt(quantStr);

        tv_nomeItem = linha_item.findViewById(R.id.tv_nomeItem);

        Float v = listaItem.get(position).getVal();

        String l = v.toString();
        Integer a = listaItem.get(position).getQuant();
        Float total = v * a;

        String b = a.toString();
        String totalsrt = total.toString();

        totalLista = total + totalLista;
        funcaoa(totalLista);

        DecimalFormat formatter = new DecimalFormat("#.00");

        tv_val.setText("R$" + formatter.format(v));
        tv_quant.setText(b);
        tv_nomeItem.setText(listaItem.get(position).getNome_item());
//        tv_val.setText("R$" + formatter.format(v));
//        tv_quant.setText(b);
//        tv_nomeItem.setText(listaItem.get(position).getNome_item());
        return linha_item;
    }

    public double funcaoa(double a){
        setTotalLista(a);
        return a;

    }

    public void setTotalLista(double totalLista) {
        this.totalLista = totalLista;
    }


}