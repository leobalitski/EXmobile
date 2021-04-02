package com.example.android_media_alunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView nota1, nota2, nota3, nota4;
    TextView resultado;
    private String nome, dtNascimento, endereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // R classe que extende ao Objeto
        TextView inputNome = (TextView) findViewById(R.id.inputNome);
        inputNome.setText(nome);
        TextView inputDtNasc = (TextView) findViewById(R.id.inputDtNasc);
        inputDtNasc.setText(dtNascimento);
        TextView inputEndereco = (TextView) findViewById(R.id.inputEndereco);
        inputEndereco.setText(endereco);

        Button btnCalculo = (Button) findViewById(R.id.btnCalculo);
        btnCalculo.setOnClickListener(onClickCalcular());
    }

    //botão para INSERIR dados e CALCULAR as médias
    private View.OnClickListener onClickCalcular() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtNome = (TextView) findViewById(R.id.inputNome);
                nome = txtNome.getText().toString();
                TextView txtDtNasc = (TextView) findViewById(R.id.inputDtNasc);
                dtNascimento = txtDtNasc.getText().toString();
                TextView txtEndereco = (TextView) findViewById(R.id.inputEndereco);
                endereco = txtEndereco.getText().toString();

                nota1 = (TextView) findViewById(R.id.inputNota1);
                nota2 = (TextView) findViewById(R.id.inputNota2);
                nota3 = (TextView) findViewById(R.id.inputNota3);
                nota4 = (TextView) findViewById(R.id.inputNota4);
                float tNota1 = Float.parseFloat(nota1.getText().toString());
                float tNota2 = Float.parseFloat(nota2.getText().toString());
                float tNota3 = Float.parseFloat(nota3.getText().toString());
                float tNota4 = Float.parseFloat(nota4.getText().toString());
                resultado = (TextView) findViewById(R.id.dataResultado);
                resultado.setText(String.valueOf((tNota1 + tNota2 + tNota3 + tNota4) / 4));


                Intent myIntent = new Intent(MainActivity.this, DataActivity.class);
                startActivityForResult(myIntent, 2);

                Bundle params = new Bundle();
                params.putString("nome", nome);
                params.putString("nascimento", dtNascimento);
                params.putString("endereco", endereco);

                myIntent.putExtras(params);
                startActivity(myIntent);
            }
        };
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}