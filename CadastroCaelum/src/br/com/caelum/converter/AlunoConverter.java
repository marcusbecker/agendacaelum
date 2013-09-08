package br.com.caelum.converter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.caelum.cadastro.model.AlunoModel;

public class AlunoConverter {

	public String toJSON(List<AlunoModel> alunos) {
		try {
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("list").array().object().key("aluno")
					.array();

			for (AlunoModel aluno : alunos) {
				jsonStringer.object().key("id").value(aluno.getId())
						.key("nome").value(aluno.getNome()).key("telefone")
						.value(aluno.getTelefone()).key("endereco")
						.value(aluno.getEndereco()).key("site")
						.value(aluno.getSite()).key("nota")
						.value(aluno.getNota()).endObject();
			}

			return jsonStringer.endArray().endObject().endArray().endObject()
					.toString();
			
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

}
