package br.com.caelum.cadastro.adapter;

import java.util.List;

import br.com.caelum.cadastro.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.caelum.cadastro.model.AlunoModel;

public class ListaAlunosAdapter extends BaseAdapter {

	private Activity activity;
	private List<AlunoModel> alunos;

	public ListaAlunosAdapter(Activity activity, List<AlunoModel> alunos) {
		this.activity = activity;
		this.alunos = alunos;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int posicao) {
		return alunos.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return alunos.get(posicao).getId();
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		if (posicao % 2 == 0) {
		    view.setBackgroundColor(activity.getResources().
		        getColor(R.color.linha_par));
		}
		
		AlunoModel aluno = alunos.get(posicao);
		TextView nome = (TextView) view.findViewById(R.id.nome);
		ImageView foto = (ImageView) view.findViewById(R.id.foto);
		
		nome.setText(aluno.getNome());
		
		if(aluno.getFoto() != null){
			Bitmap imagem = BitmapFactory.decodeFile(aluno.getFoto());
			foto.setImageBitmap(Bitmap.createScaledBitmap(imagem, 50, 50, true));
			
		}else{
			Bitmap imagem = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
			foto.setImageBitmap(imagem);
		}
		
		return view;
	}

}
