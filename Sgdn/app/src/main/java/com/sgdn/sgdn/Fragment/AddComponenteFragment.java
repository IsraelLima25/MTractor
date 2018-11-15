package com.sgdn.sgdn.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sgdn.sgdn.Adapter.ModeloAdapterMaquina;
import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddComponenteFragment extends Fragment {


    private Spinner sppiner;
    private EditText nome;
    private EditText qtdHorasTrabalho;
    private EditText alturaMinimaDesgaste;
    private EditText larguraMinimaDesgaste;
    private Button botaoSalvar;


    public AddComponenteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_componente, container, false);

        //Capturar Componentes
        sppiner = view.findViewById(R.id.spnMaquinas);
        nome = view.findViewById(R.id.txtNome);
        qtdHorasTrabalho = view.findViewById(R.id.txtQtdHorasTrabalho);
        alturaMinimaDesgaste = view.findViewById(R.id.txtAlturaMinimaDesgaste);
        larguraMinimaDesgaste = view.findViewById(R.id.txtLarguraMinimaDesgaste);
        botaoSalvar = view.findViewById(R.id.btnSalvarComponente);

        //Carrega Spinner
        final DataBase dataBase = new DataBase(getActivity());
        ArrayList<Maquina> listaMaquinas = dataBase.getMaquinas();
        ModeloAdapterMaquina modelo = new ModeloAdapterMaquina(getActivity(), listaMaquinas);
        sppiner.setAdapter(modelo);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nome.getText().toString().equals("") || String.valueOf
                        (qtdHorasTrabalho.getText().toString().equals("")).equals("") ||
                        String.valueOf(alturaMinimaDesgaste.getText()).equals("") ||
                        String.valueOf(larguraMinimaDesgaste.getText())
                                .equals("")) {
                    Toast.makeText(getActivity(), "Os campos são de preenchimento " +
                            "obrigatório.", Toast.LENGTH_LONG).show();
                } else {
                    //Instancia persistir
                    Componente componente = new Componente();
                    componente.setNome(nome.getText().toString());
                    componente.setQtdHorasTrabalho(Integer.parseInt(qtdHorasTrabalho.getText().toString()));
                    componente.setAlturaMinimaDesgaste(Double.parseDouble(alturaMinimaDesgaste.getText().toString()));
                    componente.setLarguraMinimaDesgaste(Double.parseDouble(larguraMinimaDesgaste.getText().toString()));
                    Maquina maquinaSelecionada = (Maquina) sppiner.getSelectedItem();
                    componente.setMaquina(maquinaSelecionada);
                    long linhasAfetadas = dataBase.salvarComponente(componente);
                    Toast.makeText(getActivity(), "Salvo com Sucesso", Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, new ComponenteFragment())
                            .commit();
                }
            }
        });

        return view;

    }

}
