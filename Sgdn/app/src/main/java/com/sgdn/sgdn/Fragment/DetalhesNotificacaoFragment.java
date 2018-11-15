package com.sgdn.sgdn.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalhesNotificacaoFragment extends Fragment {

    private EditText maquina;
    private EditText cliente;
    private EditText contato;
    private EditText cidade;
    private EditText dataInspecao;

    public DetalhesNotificacaoFragment() {
        // Required empty public constructor
    }

    public void desabilitarCampos(EditText maquina, EditText cliente, EditText cidade, EditText dataInspecao,
                                  EditText contato) {
        maquina.setEnabled(false);
        cliente.setEnabled(false);
        cidade.setEnabled(false);
        dataInspecao.setEnabled(false);
        contato.setEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhes_notificacao, container, false);

        //Capturar componentes
        maquina = view.findViewById(R.id.txtMaquinaInspecionada);
        cliente = view.findViewById(R.id.txtCliente);
        cidade = view.findViewById(R.id.txtCidade);
        dataInspecao = view.findViewById(R.id.txtDataInspecao);
        contato = view.findViewById(R.id.txtContato);

        Bundle mBundle = new Bundle();
        String id = "vazio";
        if (mBundle != null) {
            mBundle = getArguments();
            id = mBundle.getString("id");
        }


        DataBase dataBase = new DataBase(getActivity());
        Alerta alertaBusca = new Alerta();
        alertaBusca.setIdAlerta(Integer.parseInt(id));
        Alerta alerta = dataBase.getAlertaById(alertaBusca);

        //Desabilitar os campos
        desabilitarCampos(maquina, cliente, cidade, dataInspecao, contato);

        //Formatar Data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(alerta.getDataAlerta());
        String dataFormatada = dateFormat.format(data).toString();

        //Preencher os campos
        maquina.setText(alerta.getInspecao().getComponente().getMaquina().getNome());
        cliente.setText(alerta.getInspecao().getCliente().toString());
        contato.setText(alerta.getInspecao().getCelularCliente());
        cidade.setText(alerta.getInspecao().getCidade().toString());
        dataInspecao.setText(dataFormatada);

        return view;
    }

}
