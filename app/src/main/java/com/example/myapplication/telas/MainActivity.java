package com.example.myapplication.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.adapter.ItemAdapter;
import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.model.Pessoa;
import com.example.myapplication.database.PessoaDao;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSemRegistro;
    private final ItemAdapter adapter = new ItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pega a referenciados elementos
        textViewSemRegistro = findViewById(R.id.textView_semRegistro);

        //configura o recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        //configura o botão de cadastro
        MaterialButton buttonCadastrar = findViewById(R.id.button_cadastrar);
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CasastroActivity.class));
            }
        });

        //congigura o click do item da lista para abrir o detalhe
        adapter.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa pessoa = adapter.getPessoaSelecionada();

                if (pessoa != null) {
                    Intent intent = CasastroActivity.newIntent(getApplicationContext(),
                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getSobrenome(),
                            pessoa.getIdade()
                    );
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        atualizarLista();
    }

    private void atualizarLista() {
        PessoaDao pessoaDao = DatabaseClient
                .getInstance(getApplicationContext())
                .pessoaDao();

        List<Pessoa> pessoas = pessoaDao.getAll();
        adapter.setPessoaList(pessoas);

        //mostra ou oculta a msg que não tem nenhum registro
        if (pessoas.isEmpty()) {
            textViewSemRegistro.setVisibility(View.VISIBLE);
        } else {
            textViewSemRegistro.setVisibility(View.GONE);
        }
    }
}