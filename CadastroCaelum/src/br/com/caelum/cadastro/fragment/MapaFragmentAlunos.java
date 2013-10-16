package br.com.caelum.cadastro.fragment;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.mapa.Localizador;
import br.com.caelum.cadastro.model.AlunoModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragmentAlunos extends SupportMapFragment {

	private List<AlunoModel> alunos = new ArrayList<AlunoModel>(0);

	public List<AlunoModel> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoModel> alunos) {
		this.alunos = alunos;
	}

	@Override
	public void onResume() {
		super.onResume();

		LatLng latLng = null;

		for (AlunoModel aluno : alunos) {
			latLng = getLatLngByAddress(aluno.getEndereco());
			addMarker(latLng, aluno.getNome(), "");

		}

		if (latLng != null) {
			centralizar(latLng);
		}

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
