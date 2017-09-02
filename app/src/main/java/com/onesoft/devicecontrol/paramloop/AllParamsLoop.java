package com.onesoft.devicecontrol.paramloop;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.onesoft.fero.answer.ParamFeroAllAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKurenieAnswer;
import com.onesoft.fero.answer.enums.AnswerStatusEnum;
import com.onesoft.fero.answer.enums.ApiStatusInfoEnum;
import com.onesoft.param.to.ParametrFeroKotolTO;
import com.onesoft.param.to.ParametrFeroKurenieTO;
import com.onesoft.param.to.ParametrFeroVodaTO;
import com.onesoft.services.SmrekService;

public class AllParamsLoop extends IntentService {
    SmrekService  smrekService = new SmrekService();
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    //Odpoved na volanie api uvodna obrazovka nacitanie dat
    ParamFeroAllAnswer answerAllParam = new ParamFeroAllAnswer();
    ParamFeroAllAnswer answer = new ParamFeroAllAnswer();
    //Odpoved na zmenu
    ParamFeroChangeKurenieAnswer answerKurenieChange = new ParamFeroChangeKurenieAnswer();
    //Inicializacia transfer objekov pre jednotlive  layouty kotol kurenie voda
    ParametrFeroKotolTO kotolBase = new ParametrFeroKotolTO();
    ParametrFeroKurenieTO kurenieBase = new ParametrFeroKurenieTO();
    ParametrFeroVodaTO vodaBase = new ParametrFeroVodaTO();
    private static final String TAG = "DownloadService";

    public AllParamsLoop() {
        super(AllParamsLoop.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");

        Bundle bundle = new Bundle();

        if (true) {
            /* Update UI: Download Service is Running */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            try {
                answer  = getParam();

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


    public ParamFeroAllAnswer getParam (){
        answerAllParam = smrekService.allFeroParam(1, 0);
        //Kurenie Fero Naplnenie hodnout do poly pre zobrazenie
        if (answerAllParam.getParamFeroAll().getFeroKurenie() != null) {
            kurenieBase = answerAllParam.getParamFeroAll().getFeroKurenie();
        }
        //TODO dorobit chybove hlasky
        //Kotol Fero Naplnenie hodnout do poly pre zobrazenie
        if (answerAllParam.getParamFeroAll().getFeroKotol() != null) {
            kotolBase = answerAllParam.getParamFeroAll().getFeroKotol();
        }
        //TODO dorobit chybove hlasky
        //Voda Fero Naplnenie hodnout do poly pre zobrazenie
        if (answerAllParam.getParamFeroAll().getFeroVoda() != null) {
            vodaBase = answerAllParam.getParamFeroAll().getFeroVoda();
        }
        //TODO dorobit chybove hlasky
        if (answerAllParam != null || !answerAllParam.getStatusInfoList().equals(ApiStatusInfoEnum.ERROR)) {
            setAnswerAllParam(answerAllParam);
            return answerAllParam;
        }else
        return null;
    }

    public ParamFeroAllAnswer getAnswerAllParam() {
        return answerAllParam;
    }

    public void setAnswerAllParam(ParamFeroAllAnswer answerAllParam) {
        this.answerAllParam = answerAllParam;
    }
}
