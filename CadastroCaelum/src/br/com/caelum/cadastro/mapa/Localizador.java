package br.com.caelum.cadastro.mapa;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Localizador {

	private Context context;

	public Localizador(Context context) {
		this.context = context;
	}

	public LatLng getCordenada(String endereco) {
		Geocoder geocoder = new Geocoder(context);
		try {
			List<Address> enderecos = geocoder.getFromLocationName(endereco, 1);

			if (!enderecos.isEmpty()) {
				Address end = enderecos.get(0);

				return new LatLng(end.getLatitude(), end.getLongitude());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
