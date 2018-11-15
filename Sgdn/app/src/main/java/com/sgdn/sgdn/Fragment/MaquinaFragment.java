package com.sgdn.sgdn.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sgdn.sgdn.Adapter.ModeloAdapterMaquina;
import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;
import com.sgdn.sgdn.SrcDataBase.DataBaseSimulator;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaquinaFragment extends Fragment {

    private Context context;

    public MaquinaFragment() {
        // Required empty public constructor
    }


    private ListView listaMaquinas;
    private Button botaoAdicionar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maquina, container, false);
        listaMaquinas = view.findViewById(R.id.listViewMaquinas);

        DataBase dataBase = new DataBase(getActivity());
        ArrayList<Maquina> lista = dataBase.getMaquinas();
        ModeloAdapterMaquina modeloAdapterMaquina = new ModeloAdapterMaquina(getActivity(), lista);
        listaMaquinas.setAdapter(modeloAdapterMaquina);

        botaoAdicionar = view.findViewById(R.id.btnAdicionarMaquina);

        //Evento botão adicionar
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chamando novamente o fragment anterior
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, new AddMaquinaFragment()).commit();

            }
        });

        //Evento item lista
        listaMaquinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Maquina maquina = (Maquina) listaMaquinas.getItemAtPosition(position);
                AlterarMaquinaFragment fragment = new AlterarMaquinaFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(maquina.getIdMaquina()));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        return view;
    }

}
