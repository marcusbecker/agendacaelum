package br.com.caelum.cadastro;

import android.widget.EditText;
import android.widget.SeekBar;
import br.com.caelum.cadastro.model.AlunoModel;

public class FormularioHelper {

	private EditText nome;
	private EditText telefone;
	private EditText endereco;
	private EditText site;
	private SeekBar nota;

	public FormularioHelper(FormularioAlunosActivity actv) {
		nome = (EditText) actv.findViewById(R.id.nome);
		telefone = (EditText) actv.findViewById(R.id.telefone);
		endereco = (EditText) actv.findViewById(R.id.endereco);
		site = (EditText) actv.findViewById(R.id.site);
		nota = (SeekBar) actv.findViewById(R.id.nota);

	}

	public AlunoModel getAlunoPopulado() {
		AlunoModel aluno = new AlunoModel();
		// aluno.setId(actv.findViewById(R.id.));
		aluno.setNome(nome.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setNota(nota.getProgress());

		return aluno;
	}
}
