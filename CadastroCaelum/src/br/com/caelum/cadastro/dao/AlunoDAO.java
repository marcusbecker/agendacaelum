package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.cadastro.model.AlunoModel;

public class AlunoDAO {

	private ComumDAO dao;
	private static final String[] COLUNAS = { "id", "nome", "telefone",
			"endereco", "site", "nota", "foto" };

	public AlunoDAO(ComumDAO dao) {
		this.dao = dao;
	}

	public void inserir(AlunoModel aluno) {
		SQLiteDatabase db = dao.getWritableDatabase();
		ContentValues cv = toValues(aluno);

		db.insert(ComumDAO.TABELA, null, cv);

	}

	private ContentValues toValues(AlunoModel aluno) {
		ContentValues cv = new ContentValues();

		cv.put("nome", aluno.getNome());
		cv.put("telefone", aluno.getTelefone());
		cv.put("endereco", aluno.getEndereco());
		cv.put("site", aluno.getSite());
		cv.put("nota", aluno.getNota());

		return cv;
	}

	public List<AlunoModel> getLista() {
		List<AlunoModel> lst = new ArrayList<AlunoModel>(10);
		Cursor c = dao.getReadableDatabase().query(ComumDAO.TABELA, COLUNAS,
				null, null, null, null, null);

		while (c.moveToNext()) {
			AlunoModel a = new AlunoModel();
			a.setId(c.getLong(0));
			a.setNome(c.getColumnName(1));
			a.setNome(c.getString(1));
			a.setTelefone(c.getString(2));
			a.setEndereco(c.getString(3));
			a.setSite(c.getString(4));
			a.setNota(c.getDouble(5));
			a.setFoto(c.getString(6));

			lst.add(a);
		}

		dao.close();
		return lst;
	}
}
