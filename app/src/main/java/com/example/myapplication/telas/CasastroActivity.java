package com.example.myapplication.telas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.model.Pessoa;
import com.example.myapplication.database.PessoaDao;
import com.google.android.material.button.MaterialButton;

public class CasastroActivity extends AppCompatActivity {

    private static final String INTENT_ID = "id";
    private static final String INTENT_NOME = "nome";
    private static final String INTENT_SOBRENOME = "sobrenome";
    private static final String INTENT_IDADE = "idade";

    private EditText editTextNome;
    private EditText editTextSobrenome;
    private EditText editTextIdade;

    private Integer pessoaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_cadastro);

        //verifica se tem algum id (atualização ou cadastro)
        pessoaId = getIntent().getIntExtra(INTENT_ID, 0);

        //congigura a action bar com o titulo correto
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (pessoaId != 0) {
                actionBar.setTitle(R.string.atualizar);
            } else {
                actionBar.setTitle(R.string.cadastrar);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //pega a referencia dos componentes
        editTextNome = findViewById(R.id.editText_nome);
        editTextSobrenome = findViewById(R.id.editText_sobrenome);
        editTextIdade = findViewById(R.id.editText_idade);

        preencheCampos();

        //configura o botão salvar
        MaterialButton buttonSalvar = findViewById(R.id.button_salvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPessoa();
            }
        });
    }

    private void preencheCampos() {
        //verifica se é um novo ou uma atualização
        String nome = getIntent().getStringExtra(INTENT_NOME);
        String sobrenome = getIntent().getStringExtra(INTENT_SOBRENOME);
        String idade = getIntent().getStringExtra(INTENT_IDADE);

        if (nome != null) {
            editTextNome.setText(nome);
        }

        if (sobrenome != null) {
            editTextSobrenome.setText(sobrenome);
        }

        if (idade != null) {
            editTextIdade.setText(idade);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //acção do botão voltar
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    //valida se todos os campos estão preenchidos
    public boolean validarCadastro() {
        return !editTextIdade.getText().toString().isEmpty()
                && !editTextSobrenome.getText().toString().isEmpty()
                && !editTextIdade.getText().toString().isEmpty();
    }

    public void salvarPessoa() {
        if (validarCadastro()) {

            try {
                PessoaDao pessoaDao = DatabaseClient
                        .getInstance(getApplicationContext())
                        .pessoaDao();

                Pessoa pessoa = new Pessoa(
                        editTextNome.getText().toString(),
                        editTextSobrenome.getText().toString(),
                        editTextIdade.getText().toString()
                );

                boolean jaExiste = (pessoaId != 0);

                //valida se já existe um cadastro ou é um novo
                if (jaExiste) {
                    pessoa.setId(pessoaId);
                    pessoaDao.update(pessoa);
                } else {
                    pessoaDao.insertAll(pessoa);
                }

                finish();

            } catch (Exception exception) {
                Toast.makeText(getApplicationContext(), getString(R.string.cadastro_dao_erro), Toast.LENGTH_SHORT).show();
            }
        } else {
            //Se não for válido mostra uma msg de erro
            Toast.makeText(getApplicationContext(), getString(R.string.cadastro_erro), Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent newIntent(Context context, Integer id, String nome, String sobrenome, String idade) {
        Intent intent = new Intent(context, CasastroActivity.class);

        intent.putExtra(INTENT_ID, id);
        intent.putExtra(INTENT_NOME, nome);
        intent.putExtra(INTENT_SOBRENOME, sobrenome);
        intent.putExtra(INTENT_IDADE, idade);

        return intent;
    }
}