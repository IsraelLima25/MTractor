package com.sgdn.sgdn.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sgdn.sgdn.Adapter.ModeloAdapterAlerta;
import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificacaoFragment extends Fragment {

    private ListView listaInspecoes;

    public NotificacaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificacao, container, false);

        //Capturar Componentes
        listaInspecoes = view.findViewById(R.id.listViewNotificacao);

        //Adicionando na lista usando o adapter
        DataBase dataBase = new DataBase(getActivity());
        ArrayList<Alerta> lista = dataBase.getAlertasNotificado();
        ModeloAdapterAlerta adapter = new ModeloAdapterAlerta(getActivity(), lista);
        listaInspecoes.setAdapter(adapter);

        //Evento de clique em um item da lista
        listaInspecoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alerta alerta = (Alerta) listaInspecoes.getItemAtPosition(position);
                DetalhesNotificacaoFragment fragment = new DetalhesNotificacaoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(alerta.getIdAlerta()));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        return view;


    }

}
