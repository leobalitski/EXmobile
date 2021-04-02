package com.example.android_media_30_alunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Aluno implements Parcelable {
    public String nome;
    public String dtNascimento;
    public String endereco;
    public String[] notas;
    public String media;

    public Aluno(String nome, String dtNascimento, String endereco, String[] notas, String media) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
        this.notas = notas;
        this.media = media;
    }

    protected Aluno(Parcel in) {
        nome = in.readString();
        dtNascimento = in.readString();
        endereco = in.readString();
        notas = in.createStringArray();
        media = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(dtNascimento);
        dest.writeString(endereco);
        dest.writeStringArray(notas);
        dest.writeString(media);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };
}

public class MainActivity extends AppCompatActivity {
    ArrayList<String[]> notasAlunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLogin = (Button) findViewById(R.id.btnCalcular);
        btLogin.setOnClickListener(onClickLogin());
    }

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tNome = (TextView) findViewById(R.id.inputNome);
                TextView tDtNascimento = (TextView) findViewById(R.id.inputDtNascimento);
                TextView tEndereco = (TextView) findViewById(R.id.inputEndereco);
                TextView tNota1 = (TextView) findViewById(R.id.inputNota1);
                TextView tNota2 = (TextView) findViewById(R.id.inputNota2);
                TextView tNota3 = (TextView) findViewById(R.id.inputNota3);
                TextView tNota4 = (TextView) findViewById(R.id.inputNota4);

                String nome = tNome.getText().toString();
                String dtNascimento = tDtNascimento.getText().toString();
                String endereco = tEndereco.getText().toString();

                float nota1 = Float.parseFloat(tNota1.getText().toString());
                float nota2 = Float.parseFloat(tNota2.getText().toString());
                float nota3 = Float.parseFloat(tNota3.getText().toString());
                float nota4 = Float.parseFloat(tNota4.getText().toString());
                float media = ((nota1 + nota2 + nota3 + nota4) / 4);

                String[] notas = {
                        String.valueOf(nota1),
                        String.valueOf(nota2),
                        String.valueOf(nota3),
                        String.valueOf(nota4),
                        String.valueOf(media)
                };
                Aluno aluno = new Aluno(nome, endereco, dtNascimento, notas, String.valueOf(media));

                int length = notasAlunos.size() + 1;
                notasAlunos.add(aluno);
                // arrayList que armazena todos os alunos com suas respectivas informações
                if (length <= 30) {
                    Intent intent = new Intent(MainActivity.this, DataActivity.class);
                    Bundle params = new Bundle();
                    params.putParcelableArrayList("aluno", notasAlunos);
                    intent.putExtras(params);
                    startActivityForResult(intent, 2);
                } else {
                    throw new Error("apenas 30 alunos");
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            Intent it = data;
            if (it != null) {
                TextView tNome = (TextView) findViewById(R.id.inputNome);
                TextView tDtNascimento = (TextView) findViewById(R.id.inputDtNascimento);
                TextView tEndereco = (TextView) findViewById(R.id.inputEndereco);
                TextView tNota1 = (TextView) findViewById(R.id.inputNota1);
                TextView tNota2 = (TextView) findViewById(R.id.inputNota2);
                TextView tNota3 = (TextView) findViewById(R.id.inputNota3);
                TextView tNota4 = (TextView) findViewById(R.id.inputNota4);
                tNome.setText("");
                tDtNascimento.setText("");
                tEndereco.setText("");
                tNota1.setText("");
                tNota2.setText("");
                tNota3.setText("");
                tNota4.setText("");
                Bundle args = it.getExtras();
                notasAlunos = args.getParcelableArrayList("aluno");
            }
        }
    }
}