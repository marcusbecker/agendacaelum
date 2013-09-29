package br.com.caelum.cadastro.fragment;

import java.util.Arrays;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.ProvasActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.ProvaModel;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ListaProvasFragment extends Fragment {

	private ListView listViewProvas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.provas_lista, container,
				false);

		this.listViewProvas = (ListView) layoutProvas
				.findViewById(R.id.lista_provas);

		ProvaModel prova1 = new ProvaModel("20/03/2012", "Matematica");
		prova1.setTopicos(Arrays.asList("Algebra linear", "Integral",
				"Diferencial"));

		ProvaModel prova2 = new ProvaModel("25/03/2012", "Portugues");
		prova2.setTopicos(Arrays.asList("Complemento nominal",
				"Oracoes Subordinadas"));

		List<ProvaModel> provas = Arrays.asList(prova1, prova2);

		this.listViewProvas.setAdapter(new ArrayAdapter<ProvaModel>(
				getActivity(), android.R.layout.simple_list_item_1, provas));

		this.listViewProvas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				ProvaModel selecionada = (ProvaModel) adapter
						.getItemAtPosition(posicao);

				Toast.makeText(getActivity(),
						"Prova selecionada: " + selecionada, Toast.LENGTH_LONG)
						.show();

				ProvasActivity calendarioProvas = (ProvasActivity) getActivity();
				calendarioProvas.selecionaProva(selecionada);
			}
		});

		return layoutProvas;
	}
}
