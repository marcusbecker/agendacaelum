package br.com.caelum.cadastro.fragment;

import br.com.caelum.cadastro.mapa.Localizador;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapaFragment extends SupportMapFragment {

	@Override
	public void onResume() {
		super.onResume();

		LatLng latLng = new Localizador(this.getActivity())
				.getCordenada("Av. Dqa. de Goiás, São Paulo");

		centralizar(latLng);
	}

	private void centralizar(LatLng latLng) {
		GoogleMap map = getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
		map.animateCamera(update);
	}

	public void onResumeLatLng() {

		GoogleMap map = getMap();
		LatLng latLng = new LatLng(-23.61071, -46.70342);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
		map.animateCamera(update);
	}
}
