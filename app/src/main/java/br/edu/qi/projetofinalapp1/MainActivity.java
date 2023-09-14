package br.edu.qi.projetofinalapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMinhasReceitas, btnNovaReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnMinhasReceitas = findViewById(R.id.btnMinhasReceitas);
        btnNovaReceita = findViewById(R.id.btnNovaReceita);
        btnMinhasReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MinhasReceitasActivity.class));
                finish();
            }
        });
        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NovaReceitaActivity.class));
                finish();
            }
        });
    }
}