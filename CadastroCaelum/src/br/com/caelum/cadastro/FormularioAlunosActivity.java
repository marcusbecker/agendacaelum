package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
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

	private AlunoModel aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.formulario_alunos);

		this.dao = new ComumDAO(this);
		this.helper = new FormularioAlunoHelper(this);

		Button btnSalvar = (Button) findViewById(R.id.botao);

		Intent intent = getIntent();
		aluno = (AlunoModel) intent.getSerializableExtra("alunoSel");

		if (aluno != null) {
			helper.populaAluno(aluno);
			btnSalvar.setText("Alterar");
		}

		btnSalvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(FormularioAlunosActivity.this, "Nome: " +
				// aluno.getNome(), Toast.LENGTH_LONG).show();
				AlunoDAO alunoDAO = new AlunoDAO(dao);

				AlunoModel a = helper.getAlunoPopulado();

				if (aluno != null) {
					a.setId(aluno.getId());
				}

				alunoDAO.inserirOuAtualizar(a);
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
