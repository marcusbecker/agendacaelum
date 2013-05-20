package br.com.caelum.cadastro;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.model.AlunoModel;

public class FormularioAlunosActivity extends Activity {

	private static final int TIRAR_FOTO = 123;
	private FormularioAlunoHelper helper;
	private ComumDAO dao;

	private AlunoModel aluno;
	private AlunoDAO alunoDAO;

	private String localFoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.formulario_alunos);

		this.dao = new ComumDAO(this);
		this.alunoDAO = new AlunoDAO(dao);
		this.helper = new FormularioAlunoHelper(this);

		Button btnSalvar = (Button) findViewById(R.id.botao);

		ImageView foto = helper.getBotaoFoto();
		foto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				localFoto = Environment.getExternalStorageDirectory() + "/"
						+ System.currentTimeMillis() + ".jpg";
				
				Uri local = Uri.fromFile(new File(localFoto));
				iCamera.putExtra(MediaStore.EXTRA_OUTPUT, local);
				
				startActivityForResult(iCamera, TIRAR_FOTO);
				
			}
		});

		Intent intent = getIntent();
		aluno = (AlunoModel) intent
				.getSerializableExtra(Extras.ALUNO_SELECIONADO);

		if (aluno != null) {
			helper.populaAluno(aluno);
			btnSalvar.setText("Alterar");
		}

		btnSalvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlunoModel novoAluno = helper.getAlunoPopulado();
				if (aluno != null) {
					novoAluno.setId(aluno.getId());
				}

				alunoDAO.inserirOuAtualizar(novoAluno);
				finish();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == TIRAR_FOTO){
			if(resultCode == Activity.RESULT_OK){
				helper.setFoto(localFoto);
			}
		}
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
