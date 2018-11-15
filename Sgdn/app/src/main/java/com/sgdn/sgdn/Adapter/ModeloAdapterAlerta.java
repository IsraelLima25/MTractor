package com.sgdn.sgdn.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.R;

import java.util.List;

public class ModeloAdapterAlerta extends ArrayAdapter<Alerta> {


    private Activity context;
    private List<Alerta> objects;

    public ModeloAdapterAlerta(Activity context, List<Alerta> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Alerta p = objects.get(position);
        View v = context.getLayoutInflater().inflate(R.layout.modelo_adapter_alerta, parent, false);
        TextView nome = v.findViewById(R.id.txtNome);
        nome.setText(p.getInspecao().getCliente());
        return v;
    }
}
