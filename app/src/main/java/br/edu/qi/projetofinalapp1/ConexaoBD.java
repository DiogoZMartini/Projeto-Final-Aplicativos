package br.edu.qi.projetofinalapp1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String NAME = "bdReceita";
    private static final int VERSION = 1;
    public ConexaoBD(Context contexto) {
        super(contexto, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase bdReceita) {
        bdReceita.execSQL("create table tb_receita(" +
                "id integer primary key autoincrement," +
                "nome varchar(100)," +
                "ingredientes varchar(250)," +
                "preparo vachar(300))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase bdReceita, int i, int i1) {
    }
}
