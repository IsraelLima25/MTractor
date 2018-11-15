package com.sgdn.sgdn.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sgdn.sgdn.Adapter.ModeloAdapterComponente;
import com.sgdn.sgdn.Adapter.ModeloAdapterMaquina;
import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.Negocio.ManagerDesgaste;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InspecaoFragment extends Fragment {

    private Spinner spnMaquinas;
    private Spinner spnComponentes;
    private Button botao;
    private EditText txtHorasTrabalhadas;
    private TextView viewHorasTrabalhadasDia;
    private SeekBar skBarHorasTrabalhadasDia;
    private TextView viewDiasTrabalhadosSemana;
    private SeekBar skBarDiasTrabalhadosSemana;
    //private EditText txtHorasTrabalhadasDia;
    //private EditText txtDiasSemana;
    private EditText txtAlturaMinima;
    private EditText txtLarguraMinima;
    private EditText txtCelularCliente;
    private EditText txtCliente;
    private EditText txtCidade;

    public InspecaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inspecao, container, false);

        //Capturar Componentes
        spnMaquinas = view.findViewById(R.id.spnMaquinasInspecao);
        spnComponentes = view.findViewById(R.id.spnComponentesInspecao);
        botao = view.findViewById(R.id.btnRegistrarInspecao);
        txtHorasTrabalhadas = view.findViewById(R.id.txtQtdHorasTrabalhadas);
        viewHorasTrabalhadasDia = view.findViewById(R.id.viewhorasTrabalhadasDia);
        skBarHorasTrabalhadasDia = view.findViewById(R.id.seekBarhorasTrabalhadasDia);
        skBarHorasTrabalhadasDia.setMax(24);
        viewDiasTrabalhadosSemana = view.findViewById(R.id.viewDiasTrabalhadosSemana);
        skBarDiasTrabalhadosSemana = view.findViewById(R.id.seekBarDiasTrabalhadosSemana);
        skBarDiasTrabalhadosSemana.setMax(7);
        //txtHorasTrabalhadasDia = view.findViewById(R.id.txtQtdHorasTrabalhadasDia);
        //txtDiasSemana = view.findViewById(R.id.txtQtdDiasSemana);
        txtAlturaMinima = view.findViewById(R.id.txtAlturaMinimaDesgasteTrabalhado);
        txtLarguraMinima = view.findViewById(R.id.txtLarguraMinimaDesgasteTrabalhado);
        txtCelularCliente = view.findViewById(R.id.txtCelularCliente);
        txtCidade = view.findViewById(R.id.txtCidade);
        txtCliente = view.findViewById(R.id.txtCliente);


        carregarSpinnerMaquina(getActivity(), spnMaquinas);
        spnMaquinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Maquina maquina = (Maquina) parent.getItemAtPosition(position);
                carregarSpinnerComponente(getActivity(), spnComponentes, maquina);
                //Toast.makeText(getActivity(), "Registros Vinculados" + maquina.getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(getActivity(), "Vazio", Toast.LENGTH_LONG).show();

            }
        });

        //Metodos SeekBar
        skBarDiasTrabalhadosSemana.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewDiasTrabalhadosSemana.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skBarHorasTrabalhadasDia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHorasTrabalhadasDia.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Registrar Inspeção
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtHorasTrabalhadas.getText().toString().equals("") ||
                        txtAlturaMinima.getText().toString().equals("") ||
                        txtLarguraMinima.getText().toString().equals("") ||
                        txtCelularCliente.getText().toString().equals("") ||
                        txtCidade.getText().toString().equals("") ||
                        txtCliente.getText().toString().equals("") ||
                        skBarHorasTrabalhadasDia.getProgress() == 0 ||
                        skBarDiasTrabalhadosSemana.getProgress() == 0) {

                    Toast.makeText(getActivity(), "Os campos são de preenchimento obrigatório", Toast.LENGTH_LONG).show();

                } else {
                    DataBase dataBase = new DataBase(getActivity());
                    Componente componente = (Componente) spnComponentes.getSelectedItem();
                    Inspecao inspecao = new Inspecao();
                    inspecao.setQtdHorasTrabalho(Integer.parseInt(txtHorasTrabalhadas.getText().toString()));
                    inspecao.setQtdHorasDia(skBarHorasTrabalhadasDia.getProgress());
                    inspecao.setQtdDiasSemana(skBarDiasTrabalhadosSemana.getProgress());
                    inspecao.setAlturaDesgaste(Double.parseDouble(txtAlturaMinima.getText().toString()));
                    inspecao.setLarguraDesgaste(Double.parseDouble(txtLarguraMinima.getText().toString()));
                    inspecao.setCliente(txtCliente.getText().toString());
                    inspecao.setCidade(txtCidade.getText().toString());
                    inspecao.setCelularCliente(txtCelularCliente.getText().toString());
                    inspecao.setComponente(componente);
                    long linhasAfetadas = dataBase.salvarInspecao(inspecao);
                    Toast.makeText(getActivity(), "Inspeção registrada com sucesso", Toast.LENGTH_LONG).show();
                    limparCampos(txtHorasTrabalhadas, txtAlturaMinima, txtLarguraMinima);
                    ArrayList<Inspecao> listaInspecao = dataBase.getinspecoes();
                    int tamanhoLista = listaInspecao.size();
                    if (listaInspecao.size() > 0) {
                        //Enviar a ultima inspecao Cadastrada
                        ManagerDesgaste managerDesgaste = new ManagerDesgaste();
                        managerDesgaste.calcularTempoDesgaste(listaInspecao.get(tamanhoLista - 1), getActivity());
                    }
                    //Direcionar para Dashboard
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, new DashboardFragment()).commit();
                }
            }
        });
        return view;
    }

    //Metodo para carregar o SpinnerMaquina
    private void carregarSpinnerMaquina(Activity activity, Spinner spinner) {
        //Carregar Spinner
        DataBase dataBase = new DataBase(activity);
        ArrayList<Maquina> maquinas = dataBase.getMaquinas();
        ModeloAdapterMaquina adapter = new ModeloAdapterMaquina(activity, maquinas);
        spinner.setAdapter(adapter);
    }

    //Metodo para carregar o SpinnerComponente
    private void carregarSpinnerComponente(Activity activity, Spinner spinner, Maquina maquina) {
        DataBase dataBase = new DataBase(activity);
        ArrayList<Componente> componentes = dataBase.getComponenteByMaquina(maquina);
        ModeloAdapterComponente adapter = new ModeloAdapterComponente(activity, componentes);
        spinner.setAdapter(adapter);
    }

    //Metodo para limpar os campos da tela
    private void limparCampos(EditText txt1, EditText txt2, EditText txt3) {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
    }
}
