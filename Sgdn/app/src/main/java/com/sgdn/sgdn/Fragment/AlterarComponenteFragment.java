package com.sgdn.sgdn.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
public class AlterarComponenteFragment extends Fragment {

    private View view;
    private ImageView alterar;
    private EditText alterarNome;
    private EditText alterarQtdHorasTrabalho;
    private EditText alterarAlturaMinimaDesgaste;
    private EditText alterarLarguraMinimaDesgaste;
    private Spinner spListaMaquinas;
    private Button botaoAlterar;

    public AlterarComponenteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alterar_componente, container, false);

        Bundle mBundle = new Bundle();
        String id = "vazio";
        if (mBundle != null) {
            mBundle = getArguments();
            id = mBundle.getString("id");
        }
        //Toast.makeText(getActivity(), "id" + id, Toast.LENGTH_LONG).show();

        DataBase dataBase = new DataBase(getActivity());

        //Capturar Componentes
        alterar = view.findViewById(R.id.imgAlterar);
        alterarNome = view.findViewById(R.id.txtAlterarNome);
        alterarQtdHorasTrabalho = view.findViewById(R.id.txtAlterarQtdHorasTrabalho);
        alterarAlturaMinimaDesgaste = view.findViewById(R.id.txtAlterarAlturaMinimaDesgaste);
        alterarLarguraMinimaDesgaste = view.findViewById(R.id.txtAlterarLarguraMinimaDesgaste);
        spListaMaquinas = view.findViewById(R.id.spnAlterarMaquinas);
        botaoAlterar = view.findViewById(R.id.btnAlterarComponente);

        //Setar Valores
        Componente componenteBusca = new Componente();
        componenteBusca.setIdComponente(Integer.parseInt(id));
        final Componente componente = dataBase.getComponenteById(componenteBusca);
        alterarNome.setText(componente.getNome().toString());
        alterarQtdHorasTrabalho.setText(String.valueOf(componente.getQtdHorasTrabalho()));
        alterarAlturaMinimaDesgaste.setText(String.valueOf(componente.getAlturaMinimaDesgaste()));
        alterarLarguraMinimaDesgaste.setText(String.valueOf(componente.getLarguraMinimaDesgaste()));

        //Carregar Sppiner
        ArrayList<Maquina> listaMaquinas = dataBase.getMaquinas();
        ModeloAdapterMaquina modelo = new ModeloAdapterMaquina(getActivity(), listaMaquinas);
        spListaMaquinas.setAdapter(modelo);
        //Toast.makeText(getActivity(), "" + componenteBusca.getIdComponente(), Toast.LENGTH_LONG).show();
        //Selecionar Sppiner
        spListaMaquinas.setSelection(componente.getMaquina().getIdMaquina() - 1);

        desabilitarCampos();

        //Evento botão Alterar
        botaoAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alterarNome.getText().toString().equals("") || String.valueOf
                        (alterarQtdHorasTrabalho.getText().toString()).equals("") ||
                        String.valueOf(alterarAlturaMinimaDesgaste.getText()).equals("") ||
                        String.valueOf(alterarLarguraMinimaDesgaste.getText())
                                .equals("")) {
                    Toast.makeText(getActivity(), "Os campos são de preenchimento " +
                            "obrigatório.", Toast.LENGTH_LONG).show();
                } else {
                    DataBase dataBaseAlterar = new DataBase(getActivity());
                    Componente componenteAlterado = new Componente();
                    componenteAlterado.setIdComponente(componente.getIdComponente());
                    componenteAlterado.setNome(alterarNome.getText().toString());
                    componenteAlterado.setQtdHorasTrabalho(Integer.parseInt(alterarQtdHorasTrabalho.getText().toString()));
                    componenteAlterado.setLarguraMinimaDesgaste(Double.parseDouble(alterarLarguraMinimaDesgaste.getText().toString()));
                    componenteAlterado.setAlturaMinimaDesgaste(Double.parseDouble(alterarAlturaMinimaDesgaste.getText().toString()));
                    Maquina novaMaquina = new Maquina();
                    novaMaquina.setIdMaquina(spListaMaquinas.getSelectedItemPosition() + 1);
                    novaMaquina = dataBaseAlterar.getMaquinaById(novaMaquina);
                    componenteAlterado.setMaquina(novaMaquina);
                    dataBaseAlterar.updateComponente(componenteAlterado);
                    Toast.makeText(getActivity(), "Alterado com Sucesso", Toast.LENGTH_LONG).show();
                    //Chamando fragment principal
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, new ComponenteFragment()).commit();
                }

            }
        });

        //Evento da imagem do lápis
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilitarModificacao();
            }
        });

        return view;
    }

    public void habilitarModificacao() {
        botaoAlterar.setVisibility(View.VISIBLE);
        alterarNome.setEnabled(true);
        alterarQtdHorasTrabalho.setEnabled(true);
        alterarLarguraMinimaDesgaste.setEnabled(true);
        alterarAlturaMinimaDesgaste.setEnabled(true);
        spListaMaquinas.setEnabled(true);
    }

    public void desabilitarCampos() {
        botaoAlterar.setVisibility(View.INVISIBLE);
        alterarNome.setEnabled(false);
        alterarQtdHorasTrabalho.setEnabled(false);
        alterarLarguraMinimaDesgaste.setEnabled(false);
        alterarAlturaMinimaDesgaste.setEnabled(false);
        spListaMaquinas.setEnabled(false);
    }

}
