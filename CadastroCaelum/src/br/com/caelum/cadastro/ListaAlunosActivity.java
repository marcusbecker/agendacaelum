package br.com.caelum.cadastro;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.model.AlunoModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;
	private ComumDAO dao;
	private AlunoDAO alunoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		this.dao = new ComumDAO(this);
		this.alunoDAO = new AlunoDAO(dao);

		listaAlunos = (ListView) findViewById(R.divisao.nome);
		listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int posicao, long id) {

				Toast.makeText(ListaAlunosActivity.this, "Clicado",
						Toast.LENGTH_LONG).show();
			}

		});

		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adp, View v,
					int posicao, long id) {

				Toast.makeText(ListaAlunosActivity.this,
						"Clicado em: " + adp.getItemAtPosition(posicao),
						Toast.LENGTH_SHORT).show();

				return true;
			}

		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		carregaLista();
	}

	@Override
	protected void onResume() {
		super.onResume();

		
	}

	private void carregaLista() {
		ArrayAdapter<AlunoModel> adapter = new ArrayAdapter<AlunoModel>(this,
				android.R.layout.simple_list_item_1, this.alunoDAO.getLista());
		this.listaAlunos.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_lst_alunos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_novo:
			startActivity(new Intent(this, FormularioAlunosActivity.class));
			break;

		default:
			break;
		}

		return true;
	}
}
