package com.sgdn.sgdn.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.R;

import java.util.List;

public class ModeloAdapterComponente extends ArrayAdapter<Componente> {
    private Activity context;
    private List<Componente> objects;

    public ModeloAdapterComponente(Activity context, List<Componente> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Componente p = objects.get(position);
        View v = context.getLayoutInflater().inflate(R.layout.modelo_adapter_componente, parent, false);
        TextView nome = v.findViewById(R.id.txtNome);
        nome.setText(p.getNome());
        return v;
    }
}
