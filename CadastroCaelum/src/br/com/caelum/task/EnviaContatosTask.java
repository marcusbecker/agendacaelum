package br.com.caelum.task;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;
import br.com.caelum.cadastro.model.AlunoModel;
import br.com.caelum.converter.AlunoConverter;
import br.com.caelum.support.WebClient;

public class EnviaContatosTask extends AsyncTask<AlunoDAO, Object, String> {

	private final String endereco = "http://www.caelum.com.br/mobile";

	private Activity context;

	private ProgressDialog progress;

	public EnviaContatosTask(Activity context) {
		this.context = context;
	}

	@Override
	protected String doInBackground(AlunoDAO... params) {
		// AlunoDAO alunoDAO = params[0];
		AlunoDAO alunoDAO = new AlunoDAO(new ComumDAO(context));

		List<AlunoModel> alunos = alunoDAO.getLista();
		String json = new AlunoConverter().toJSON(alunos);

		WebClient client = new WebClient(endereco);

		String reposta = client.post(json);

		return reposta;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde...",
				"Envio de dados para a web", true, true);
	}

	@Override
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
}
