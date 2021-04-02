package com.example.android_media_30_alunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    ArrayList<Aluno> alunosNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Bundle args = getIntent().getExtras();
        alunosNotas = args.getParcelableArrayList("aluno");

        TextView tNome = (TextView) findViewById(R.id.dataNome);
        TextView tDtNasciemento = (TextView) findViewById(R.id.dataDtNascimento);
        TextView tEndereco = (TextView) findViewById(R.id.dataEndereco);
        TextView tNota1 = (TextView) findViewById(R.id.dataNota1);
        TextView tNota2 = (TextView) findViewById(R.id.dataNota2);
        TextView tNota3 = (TextView) findViewById(R.id.dataNota3);
        TextView tNota4 = (TextView) findViewById(R.id.dataNota4);
        TextView tMedia = (TextView) findViewById(R.id.dataMedia);

        alunosNotas.forEach(aluno -> {
            tNome.setText(aluno.nome);
            tDtNasciemento.setText(aluno.dtNascimento);
            tEndereco.setText(aluno.endereco);
            tNota1.setText(aluno.notas[0]);
            tNota2.setText(aluno.notas[1]);
            tNota3.setText(aluno.notas[2]);
            tNota4.setText(aluno.notas[3]);
            tMedia.setText(aluno.media);
        });

        Button btAdd = (Button) findViewById(R.id.btnAdd);
        btAdd.setOnClickListener(onClickAdicionar());
    }

    private View.OnClickListener onClickAdicionar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = alunosNotas.size();
                if (length <= 30) {
                    Intent myIntent = new Intent();
                    Bundle params = new Bundle();
                    params.putParcelableArrayList("aluno", alunosNotas);
                    myIntent.putExtras(params);
                    setResult(2, myIntent);
                    finish();
                } else {
                    throw new Error("apenas 30 Alunos!");
                }
            }
        };
    }
}