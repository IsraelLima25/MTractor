package com.sgdn.sgdn.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMaquinaFragment extends Fragment {

    private EditText nome;
    private EditText fabricante;
    private Button salvar;

    public AddMaquinaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_maquina, container, false);

        View view = inflater.inflate(R.layout.fragment_add_maquina, container, false);

        //Capturando Componentes
        nome = view.findViewById(R.id.txtNome);
        fabricante = view.findViewById(R.id.txtFabricante);
        salvar = view.findViewById(R.id.btnSalvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nome.getText().toString().equals("") && !fabricante.getText().toString().equals("")) {
                    DataBase dataBase = new DataBase(getActivity());
                    Maquina maquina = new Maquina();
                    maquina.setNome(nome.getText().toString());
                    maquina.setFabricante(fabricante.getText().toString());
                    dataBase.salvarMaquina(maquina);
                    Toast.makeText(getActivity(), "Salvo com Sucesso", Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, new MaquinaFragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Os campos são de preenchimento " +
                            "obrigatório.", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

}
