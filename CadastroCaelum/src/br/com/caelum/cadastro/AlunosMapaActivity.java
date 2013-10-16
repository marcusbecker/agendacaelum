package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.fragment.MapaFragmentAlunos;

public class AlunosMapaActivity extends FragmentActivity {

	private ComumDAO dao;
	private AlunoDAO alunoDAO;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.map_layout);

		this.dao = new ComumDAO(this);
		this.alunoDAO = new AlunoDAO(dao);

		FragmentManager manager = getSupportFragmentManager();

		FragmentTransaction t = manager.beginTransaction();
		// t.replace(R.id.mapa, new MapaFragment());
		MapaFragmentAlunos mapaFragmentAlunos = new MapaFragmentAlunos();
		
		mapaFragmentAlunos.setAlunos(alunoDAO.getLista());
		
		t.replace(R.id.mapa, mapaFragmentAlunos);
		
		t.commit();

	}

}
