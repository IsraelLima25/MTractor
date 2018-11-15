package com.sgdn.sgdn.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sgdn.sgdn.Adapter.ModeloAdapterComponente;
import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComponenteFragment extends Fragment {

    private Button botao;
    private ListView listView;

    public ComponenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_componente, container, false);
        //Capturar Componentes
        botao = view.findViewById(R.id.btnAdicionarComponente);
        listView = view.findViewById(R.id.listViewComponente);
        DataBase dataBase = new DataBase(getActivity());
        ArrayList<Componente> listaComponentes = dataBase.getComponentes();
        ModeloAdapterComponente modeloAdapterComponente = new ModeloAdapterComponente(getActivity(), listaComponentes);
        listView.setAdapter(modeloAdapterComponente);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setando fragmento adicionar Componente
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new AddComponenteFragment())
                        .commit();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Componente componente = (Componente) listView.getItemAtPosition(position);
                AlterarComponenteFragment fragment = new AlterarComponenteFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(componente.getIdComponente()));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        return view;
    }

}
