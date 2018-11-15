package com.sgdn.sgdn.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.R;

import java.util.List;

public class ModeloAdapterMaquina extends ArrayAdapter<Maquina> {

    private Activity context;
    private List<Maquina> objects;

    public ModeloAdapterMaquina(Activity context, List<Maquina> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Maquina p = objects.get(position);
        View v = context.getLayoutInflater().inflate(R.layout.modelo_adapter_maquina, parent, false);
        TextView nome = v.findViewById(R.id.txtNomeMaquina);
        nome.setText(p.getNome());
        return v;
    }
}
