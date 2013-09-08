package br.com.caelum.cadastro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
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
import android.widget.ListView;
import br.com.caelum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.model.AlunoModel;
import br.com.caelum.cadastro.receiver.BateriaReceiver;
import br.com.caelum.task.EnviaContatosTask;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;
	private ComumDAO dao;
	private AlunoDAO alunoDAO;
	private AlunoModel alunoSel;

	private BateriaReceiver bateria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		bateria = new BateriaReceiver();
		registerReceiver(bateria, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));

		this.dao = new ComumDAO(this);
		this.alunoDAO = new AlunoDAO(dao);

		listaAlunos = (ListView) findViewById(R.id.lista);
		listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int posicao, long id) {

				alunoSel = (AlunoModel) adapter.getItemAtPosition(posicao);

				Intent intent = new Intent(ListaAlunosActivity.this,
						FormularioAlunosActivity.class);
				intent.putExtra(Extras.ALUNO_SELECIONADO, alunoSel);
				startActivity(intent);
			}
		});

		registerForContextMenu(listaAlunos);

		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adp, View v,
					int posicao, long id) {

				alunoSel = (AlunoModel) adp.getItemAtPosition(posicao);

				return false;
			}

		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		// getMenuInflater().inflate(R.menu.menu_edt_alunos, menu);

		Intent iLigar = new Intent(Intent.ACTION_CALL);
		iLigar.setData(Uri.parse("tel:" + alunoSel.getTelefone()));

		Intent iSMS = new Intent(Intent.ACTION_VIEW);
		iSMS.setData(Uri.parse("sms:" + alunoSel.getTelefone()));
		iSMS.putExtra("sms_body", "Caro aluno " + alunoSel.getNome() + " ");

		Intent iMap = new Intent(Intent.ACTION_VIEW);
		iMap.setData(Uri.parse("geo:0,0?z=14&q=" + alunoSel.getEndereco()));

		String site;
		if (alunoSel.getSite().trim().length() == 0) {
			site = "http://www.mvbos.com.br";
		} else if (!alunoSel.getSite().startsWith("http:")) {
			site = "http://" + alunoSel.getSite();
		} else {
			site = alunoSel.getSite();
		}

		Intent iSite = new Intent(Intent.ACTION_VIEW);
		iSite.setData(Uri.parse(site));

		Intent iMail = new Intent(Intent.ACTION_SEND);
		iMail.setType("message/rtc822");
		iMail.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "meumundojava@gmail.com" });
		iMail.putExtra(Intent.EXTRA_SUBJECT, "Testando subject do email");
		iMail.putExtra(Intent.EXTRA_TEXT, "Testando corpo do email");

		menu.add("Ligar").setIntent(iLigar);
		menu.add("Enviar SMS").setIntent(iSMS);
		menu.add("Achar no Mapa").setIntent(iMap);
		menu.add("Navegar no site").setIntent(iSite);
		menu.add("Deletar").setOnMenuItemClickListener(
				new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						new AlertDialog.Builder(ListaAlunosActivity.this)
								.setIcon(android.R.drawable.ic_dialog_alert)
								.setTitle("Deletar")
								.setMessage("Deseja mesmo deletar?")
								.setPositiveButton("Quero",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int which) {

												alunoDAO.deletar(alunoSel);
												carregaLista();
											}
										}).setNegativeButton("Não", null)
								.show();

						return false;
					}
				});
		menu.add("Enviar E-mail").setIntent(iMail);
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
		ListaAlunosAdapter adapter = new ListaAlunosAdapter(this,
				this.alunoDAO.getLista());
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

		case R.id.menu_enviar_alunos:
			 new EnviaContatosTask(this).execute();
			break;

		default:
			break;
		}

		return true;
	}
}
