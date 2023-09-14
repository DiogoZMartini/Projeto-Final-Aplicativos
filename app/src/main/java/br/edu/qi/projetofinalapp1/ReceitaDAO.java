package br.edu.qi.projetofinalapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {
    private ConexaoBD conexaoBD;
    private SQLiteDatabase bdReceita;

    public ReceitaDAO(Context contexto){
        this.conexaoBD = new ConexaoBD(contexto);
        this.bdReceita = this.conexaoBD.getWritableDatabase();
    }

    public void cadastrarReceita(Receita objReceita){
        ContentValues campos = new ContentValues();
        campos.put("nome",objReceita.getNome());
        campos.put("ingredientes",objReceita.getIngredientes());
        campos.put("preparo",objReceita.getPreparo());
        this.bdReceita.insert("tb_receita",null,campos);
    }

    public List<Receita> listarReceitas(){
        List<Receita> todasReceitas = new ArrayList<>();
        String[] campos = {"id","nome","ingredientes","preparo"};
        Cursor cursor = bdReceita.query("tb_receita",campos,null,null,null,null,null);
        while (cursor.moveToNext()){
            Receita objReceita = new Receita();
            objReceita.setId(cursor.getInt(0));
            objReceita.setNome(cursor.getString(1));
            objReceita.setIngredientes(cursor.getString(2));
            objReceita.setPreparo(cursor.getString(3));
            todasReceitas.add(objReceita);
        }
        return todasReceitas;
    }

    public void alterarReceita(Receita objReceita){
        ContentValues campos = new ContentValues();
        campos.put("nome",objReceita.getNome());
        campos.put("ingredientes",objReceita.getIngredientes());
        campos.put("preparo",objReceita.getPreparo());
        String[] id = {String.valueOf(objReceita.getId())};
        this.bdReceita.update("tb_receita",campos,"id = ?",id);
    }

    public void excluirReceita(Receita objReceita){
        String[] id = {String.valueOf(objReceita.getId())};
        this.bdReceita.delete("tb_receita","id = ?",id);
    }
}
