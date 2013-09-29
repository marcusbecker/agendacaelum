package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.cadastro.fragment.MapaFragment;



public class AlunosMapaActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.map_layout);

		FragmentManager manager = getSupportFragmentManager();

		FragmentTransaction t = manager.beginTransaction();
		t.replace(R.id.mapa, new MapaFragment());
		t.commit();

	}

}
