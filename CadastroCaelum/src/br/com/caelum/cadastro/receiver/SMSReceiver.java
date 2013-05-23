package br.com.caelum.cadastro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.ComumDAO;

public class SMSReceiver extends BroadcastReceiver {

	private ComumDAO dao;
	private AlunoDAO alunoDAO;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (dao == null) {
			dao = new ComumDAO(context);
			alunoDAO = new AlunoDAO(dao);
		}

		Bundle bundle = intent.getExtras();
		Object[] messages = (Object[]) bundle.get("pdus");

		SmsMessage sms = SmsMessage.createFromPdu((byte[]) messages[0]);

		boolean isAluno = alunoDAO.isAluno(sms.getDisplayOriginatingAddress());

		if (isAluno) {
			/*Toast.makeText(context,
					"Chegou um SMS de: " + sms.getDisplayOriginatingAddress(),
					Toast.LENGTH_LONG).show();*/
			
			MediaPlayer mp = MediaPlayer.create(context, R.raw.kalimba);
			mp.start();
		}
	}
}
