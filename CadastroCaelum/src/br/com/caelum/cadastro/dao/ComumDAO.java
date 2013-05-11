package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ComumDAO extends SQLiteOpenHelper {

	private static final int VERSAO = 1;
	public static final String TABELA = "FJ57";
	private static final String BANCO = "CadastroCaelum";
	
	public ComumDAO(Context context) {
		super(context, BANCO, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABELA +  
			" (id INTEGER PRIMARY KEY,  nome TEXT UNIQUE NOT NULL," +
			" telefone TEXT, endereco TEXT, site TEXT, nota REAL, foto TEXT);";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}

}
