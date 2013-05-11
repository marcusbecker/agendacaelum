package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.model.AlunoModel;

public class FormularioAlunosActivity extends Activity {

	private FormularioAlunoHelper helper;
	private ComumDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.formulario_alunos);

		this.dao = new ComumDAO(this);
		this.helper = new FormularioAlunoHelper(this);

		Button botao = (Button) findViewById(R.id.botao);
		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(FormularioAlunosActivity.this, "Nome: " +
				// aluno.getNome(), Toast.LENGTH_LONG).show();
				AlunoModel aluno = helper.getAlunoPopulado();
				new AlunoDAO(dao).inserir(aluno);
				finish();
			}
		});
	}

	@Override
	protected void onStop() {
		dao.close();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);

	}

}
