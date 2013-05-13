package br.com.caelum.cadastro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.model.AlunoModel;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;
	private ComumDAO dao;
	private AlunoDAO alunoDAO;
	private AlunoModel alunoSel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		this.dao = new ComumDAO(this);
		this.alunoDAO = new AlunoDAO(dao);

		listaAlunos = (ListView) findViewById(R.id.lista);
		listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int posicao, long id) {
				
				alunoSel = (AlunoModel) adapter.getItemAtPosition(posicao);
				
				Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunosActivity.class);
				intent.putExtra("alunoSel", alunoSel);
				startActivity(intent);
			}
		});

		registerForContextMenu(listaAlunos);
		
		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adp, View v,
					int posicao, long id) {
			
				return false;
			}

		});
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		//getMenuInflater().inflate(R.menu.menu_edt_alunos, menu);
		menu.add("Ligar");
	    menu.add("Enviar SMS");
	    menu.add("Achar no Mapa");
	    menu.add("Navegar no site");
	    menu.add("Deletar").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				new AlertDialog.Builder(ListaAlunosActivity.this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Deletar")
				.setMessage("Deseja mesmo deletar?")
				.setPositiveButton("Quero",
				        new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog,
				                    int which) {
				            	
				            	alunoDAO.deletar(alunoSel);
								carregaLista();
				            }
				        }).setNegativeButton("Não", null).show();
				

				return false;
			}
		});
	    menu.add("Enviar E-mail");
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
