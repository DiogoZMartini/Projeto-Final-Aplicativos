package br.edu.qi.projetofinalapp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MinhasReceitasActivity extends AppCompatActivity {

    private ListView lstReceita;
    private ReceitaDAO objReceitaDAO;
    private MenuItem icCadastrar, icVoltar, moAlterar, moExcluir, moDetalhes;
    private SearchView icPesquisar;
    private List<Receita> todasReceitas;
    private List<Receita> filtraReceita = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_receitas);
        lstReceita = findViewById(R.id.lstReceitas);
        objReceitaDAO = new ReceitaDAO(MinhasReceitasActivity.this);
        todasReceitas = objReceitaDAO.listarReceitas();
        filtraReceita.addAll(todasReceitas);
        ArrayAdapter<Receita> adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,filtraReceita);
        lstReceita.setAdapter(adaptador);
        registerForContextMenu(lstReceita);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_superior,menu);
        icVoltar = menu.findItem(R.id.icVoltar);
        icCadastrar = menu.findItem(R.id.icCadastrar);
        icPesquisar = (SearchView) menu.findItem(R.id.icPesquisar).getActionView();
        icVoltar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MinhasReceitasActivity.this,MainActivity.class));
                finish();
                return false;
            }
        });
        icCadastrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MinhasReceitasActivity.this,NovaReceitaActivity.class));
                finish();
                return false;
            }
        });
        icPesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filtraReceita.clear();
                for(int i = 0; i < todasReceitas.size(); i++){
                    if(todasReceitas.get(i).getNome().toLowerCase().contains(s.toLowerCase())){
                        filtraReceita.add(todasReceitas.get(i));
                    }
                }
                lstReceita.invalidateViews();
                return false;
            }
        });
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_opcoes,menu);

        moDetalhes = menu.findItem(R.id.moDetalhes);
        moAlterar = menu.findItem(R.id.moAlterar);
        moExcluir = menu.findItem(R.id.moExcluir);
        moDetalhes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo receita = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
                Receita objReceitaDetalhar = filtraReceita.get(receita.position);
                Intent i = new Intent(MinhasReceitasActivity.this,DetalhesActivity.class);
                i.putExtra("detalhe",objReceitaDetalhar);
                startActivity(i);
                finish();
                return false;
            }
        });
        moAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo receita = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
                Receita objReceitaAlterar = filtraReceita.get(receita.position);
                Intent i = new Intent(MinhasReceitasActivity.this,NovaReceitaActivity.class);
                i.putExtra("receita",objReceitaAlterar);
                startActivity(i);
                finish();
                return false;
            }
        });
        moExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo receita = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
                Receita objReceitaExcluir = filtraReceita.get(receita.position);
                AlertDialog confirmar = new AlertDialog.Builder(MinhasReceitasActivity.this)
                        .setMessage("Deseja excluir a Receita ?")
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                todasReceitas.remove(objReceitaExcluir);
                                filtraReceita.remove(objReceitaExcluir);
                                objReceitaDAO.excluirReceita(objReceitaExcluir);
                                lstReceita.invalidateViews();
                            }
                        }).create();
                confirmar.show();
                return false;
            }
        });
    }
}