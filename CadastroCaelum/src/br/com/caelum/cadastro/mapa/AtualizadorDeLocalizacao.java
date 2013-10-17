package br.com.caelum.cadastro.mapa;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import br.com.caelum.cadastro.fragment.MapaFragmentAlunos;

import com.google.android.gms.maps.model.LatLng;

public class AtualizadorDeLocalizacao implements LocationListener {

	private MapaFragmentAlunos mapaFragment;
	private LocationManager locationManager;

	public AtualizadorDeLocalizacao(Activity context,
			MapaFragmentAlunos mapaFragment) {

		this.mapaFragment = mapaFragment;

		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				2000, 20, this);

		Toast.makeText(context, "Carregando GPS", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();

		LatLng local = new LatLng(latitude, longitude);

		mapaFragment.centralizar(local);
	}

	public void cancelar() {
		locationManager.removeUpdates(this);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
