package br.com.caelum.cadastro.fragment;

import br.com.caelum.cadastro.mapa.Localizador;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {

	@Override
	public void onResume() {
		super.onResume();

		LatLng latLng = null;

		latLng = getLatLngByAddress("Av. Dqa. de Goi�s, S�o Paulo");

		centralizar(latLng);

		addMarker(latLng, "ClickIsobar",
				"Bringing people and brands together like never before.");

	}

	private LatLng getLatLngByAddress(String address) {
		LatLng latLng = new Localizador(this.getActivity())
				.getCordenada(address);
		return latLng;
	}

	private void centralizar(LatLng latLng) {
		GoogleMap map = getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
		map.animateCamera(update);
	}

	private void addMarker(LatLng latLng, String title, String snippet) {
		GoogleMap map = getMap();
		map.addMarker(new MarkerOptions().title(title).snippet(snippet)
				.position(latLng));
	}

	public void onResumeLatLng() {

		GoogleMap map = getMap();
		LatLng latLng = new LatLng(-23.61071, -46.70342);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
		map.animateCamera(update);
	}
}
