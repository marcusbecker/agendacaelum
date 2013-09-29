package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import br.com.caelum.cadastro.R;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);

		SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapa);

		GoogleMap mapa = fragment.getMap();

		mapa.addMarker(new MarkerOptions().title("Caelum")
				.snippet("Ensino e Inovação")
				.position(new LatLng(-23.588305, -46.632395)));

	}
}
