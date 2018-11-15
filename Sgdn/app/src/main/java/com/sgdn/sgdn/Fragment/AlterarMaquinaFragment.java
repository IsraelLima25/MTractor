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
import android.widget.Toast;

import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlterarMaquinaFragment extends Fragment {

    private EditText nome;
    private EditText fabricante;
    private Button alterar;
    private ImageView iconeAlterar;

    public AlterarMaquinaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alterar_maquina, container, false);
        Bundle mBundle = new Bundle();
        String id = "vazio";
        if (mBundle != null) {
            mBundle = getArguments();
            id = mBundle.getString("id");
        }

        final DataBase dataBase = new DataBase(getActivity());

        //Capturar componentes
        nome = view.findViewById(R.id.txtNomeAlterado);
        fabricante = view.findViewById(R.id.txtFabricanteAlterado);
        alterar = view.findViewById(R.id.btnAlterar);
        iconeAlterar = view.findViewById(R.id.imagemAlterar);
        desabilitarCampos();
        Maquina maquinaBusca = new Maquina();
        maquinaBusca.setIdMaquina(Integer.parseInt(id));
        final Maquina maquina = dataBase.getMaquinaById(maquinaBusca);
        nome.setText(maquina.getNome().toString());
        fabricante.setText(maquina.getFabricante().toString());

        iconeAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilitarModificacao();
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nome.getText().toString().equals("") && !fabricante.getText().toString().equals("")) {
                    Maquina maquinaAlterada = new Maquina();
                    maquinaAlterada.setIdMaquina(maquina.getIdMaquina());
                    maquinaAlterada.setNome(nome.getText().toString());
                    maquinaAlterada.setFabricante(fabricante.getText().toString());
                    dataBase.updateMaquina(maquinaAlterada);
                    Toast.makeText(getActivity(), "Alterado com Sucesso", Toast.LENGTH_LONG).show();
                    //Chamando fragment principal
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

    public void habilitarModificacao() {
        nome.setEnabled(true);
        fabricante.setEnabled(true);
        alterar.setVisibility(View.VISIBLE);
    }

    public void desabilitarCampos() {
        alterar.setVisibility(View.INVISIBLE);
        nome.setEnabled(false);
        fabricante.setEnabled(false);
    }

}
