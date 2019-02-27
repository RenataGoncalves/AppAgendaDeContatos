package br.edu.ifro.vilhena.novaagendadecontatos;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifro.vilhena.novaagendadecontatos.dao.ContatoDAO;
import br.edu.ifro.vilhena.novaagendadecontatos.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    private Button formularioBtn;
    private TextInputEditText formularioNome;
    private TextInputEditText formularioEmail;
    private TextInputEditText formularioTelefone;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formularioBtn = findViewById(R.id.formulario_btn);
        formularioNome = findViewById(R.id.formulario_nome);
        formularioEmail = findViewById(R.id.formulario_email);
        formularioTelefone = findViewById(R.id.formulario_telefone);

        Intent intent = getIntent();
        if (intent.hasExtra("contato")){
            contato = (Contato) intent.getSerializableExtra("contato");
        }else {
            contato = new Contato();
        }

        if (contato != null){
            formularioNome.setText(contato.getNome());
            formularioTelefone.setText(contato.getTelefone());
            formularioEmail.setText(contato.getEmail());
        }

        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contato.setNome(formularioNome.getText().toString());
                contato.setEmail(formularioEmail.getText().toString());
                contato.setTelefone(formularioTelefone.getText().toString());

                ContatoDAO contatoDAO = new ContatoDAO(FormularioActivity.this);

                if (contato.getId() != 0){
                    contatoDAO.alterar(contato);
                } else {
                    contatoDAO.inserir(contato);
                }


                contatoDAO.close();

                Toast.makeText(FormularioActivity.this, "Contato salvo com sucesso", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
