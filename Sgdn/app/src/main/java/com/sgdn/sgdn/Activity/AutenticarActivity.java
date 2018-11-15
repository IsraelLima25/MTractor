package com.sgdn.sgdn.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sgdn.sgdn.R;

public class AutenticarActivity extends AppCompatActivity {
    EditText usuario;
    EditText senha;
    Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar);

        //Capturar Componentes
        usuario = findViewById(R.id.txtNomeUsuario);
        senha = findViewById(R.id.txtSenhaUsuario);


        entrar = findViewById(R.id.btnEntrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuario.getText().toString().equals("") || senha.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Os campos são de preenchimento obrigatório",
                            Toast.LENGTH_LONG).show();
                } else {

                    if ((usuario.getText().toString().equals("admin")) &&
                            senha.getText().toString().equals("admin")) {
                        Intent i = new Intent(AutenticarActivity.this, MenuLateralActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário Inválido",
                                Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
