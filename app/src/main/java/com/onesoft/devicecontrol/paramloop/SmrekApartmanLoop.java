package com.onesoft.devicecontrol.paramloop;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;


import com.onesoft.fero.answer.enums.AnswerStatusEnum;
import com.onesoft.param.smrek.to.KurenieSmrekTO;
import com.onesoft.services.SmrekServiceApartman;
import com.onesoft.smrek.answer.GetKurenieAnswer;

public class SmrekApartmanLoop extends IntentService {
    int apartmanCislo;
    SmrekServiceApartman smrekServiceApartman = new SmrekServiceApartman();
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    //Odpoved na volanie api uvodna obrazovka nacitanie dat
    GetKurenieAnswer answerAllParam = new GetKurenieAnswer();
    GetKurenieAnswer answer = new GetKurenieAnswer();
    //Odpoved na zmenu

    //Inicializacia transfer objekov pre jednotlive  layouty  kurenie
       KurenieSmrekTO apartman = new KurenieSmrekTO();
    private static final String TAG = "DownloadService";

    public SmrekApartmanLoop() {
        super(SmrekApartmanLoop.class.getName());

    }

    @Override
    protected void onHandleIntent(Intent intent) {

//        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        apartmanCislo = intent.getIntExtra("cisloApartmanu",0);

        Bundle bundle = new Bundle();

        if (true && apartmanCislo !=0) {
            /* Update UI: Download Service is Running */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            try {
                answer  = getParam(apartmanCislo);
                /* Sending result back to activity */
                if (answer.getStatus().equals(AnswerStatusEnum.OK)) {
                    bundle.putParcelable("result", answer);
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e) {

                /* Sending error message back to activity */
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);
            }
        }
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }


    public GetKurenieAnswer getParam (Integer cisloApartmanu){
        answerAllParam = smrekServiceApartman.getParamForApartmanKurenie(cisloApartmanu);
        //Kurenie Fero Naplnenie hodnout do poly pre zobrazenie
        if (answerAllParam.getStatus().equals(AnswerStatusEnum.OK) ) {
//            apartman = answerAllParam.getKurenieSmrekList().get(0);
            answer.setKurenieSmrekList(answerAllParam.getKurenieSmrekList());
            return answer;
        }
      else
        return null;
    }

    public GetKurenieAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(GetKurenieAnswer answer) {
        this.answer = answer;
    }
}
