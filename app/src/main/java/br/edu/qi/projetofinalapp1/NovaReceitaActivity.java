package br.edu.qi.projetofinalapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NovaReceitaActivity extends AppCompatActivity {

    private TextView txtCancelar;
    private EditText edtNome, edtIngredientes, edtModoDePreparo;
    private Button btnAddReceita;
    private Receita objReceita = null;
    private ReceitaDAO objReceitaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita);
        getSupportActionBar().hide();
        objReceitaDAO = new ReceitaDAO(NovaReceitaActivity.this);
        edtNome = findViewById(R.id.edtNome);
        edtIngredientes = findViewById(R.id.edtIngredientes);
        edtModoDePreparo = findViewById(R.id.edtModoPreparo);
        btnAddReceita = findViewById(R.id.btnAddReceita);
        txtCancelar = findViewById(R.id.txtCancelar);
        Intent i = getIntent();
        if(i.hasExtra("receita")){
            objReceita = (Receita) i.getSerializableExtra("receita");
            edtNome.setText(objReceita.getNome());
            edtIngredientes.setText(objReceita.getIngredientes());
            edtModoDePreparo.setText(objReceita.getPreparo());
            btnAddReceita.setText("Alterar");
        }
        btnAddReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objReceita == null){
                    objReceita = new Receita();
                    objReceita.setNome(edtNome.getText().toString());
                    objReceita.setIngredientes(edtIngredientes.getText().toString());
                    objReceita.setPreparo(edtModoDePreparo.getText().toString());
                    objReceitaDAO.cadastrarReceita(objReceita);
                    startActivity(new Intent(NovaReceitaActivity.this,MinhasReceitasActivity.class));
                    finish();
                }else{
                    objReceita.setNome(edtNome.getText().toString());
                    objReceita.setIngredientes(edtIngredientes.getText().toString());
                    objReceita.setPreparo(edtModoDePreparo.getText().toString());
                    objReceitaDAO.alterarReceita(objReceita);
                    startActivity(new Intent(NovaReceitaActivity.this,MinhasReceitasActivity.class));
                    finish();
                }
            }
        });
        txtCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NovaReceitaActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}