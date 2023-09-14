package br.edu.qi.projetofinalapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {

    private TextView txtTitulo, txtIngredientes, txtPreparo;
    private Receita objReceita;
    private MenuItem icVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtIngredientes = findViewById(R.id.txtIndregientes);
        txtPreparo = findViewById(R.id.txtPreparo);
        Intent i = getIntent();
        if(i.hasExtra("detalhe")){
            objReceita = (Receita) i.getSerializableExtra("detalhe");
            txtTitulo.setText(objReceita.getNome());
            txtIngredientes.setText(objReceita.getIngredientes());
            txtPreparo.setText(objReceita.getPreparo());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_superior_detalhes,menu);
        icVoltar = menu.findItem(R.id.icVoltar);
        icVoltar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(DetalhesActivity.this,MinhasReceitasActivity.class));
                finish();
                return false;
            }
        });
        return false;
    }
}