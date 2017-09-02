package com.onesoft.devicecontrol.changes;

import com.onesoft.devicecontrol.enums.ChangeEnumStatus;
import com.onesoft.fero.answer.ParamFeroChangeKotolAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKurenieAnswer;
import com.onesoft.fero.answer.ParamFeroChangeTuvAnswer;
import com.onesoft.fero.answer.ParamFeroKotolChangeAnswer;
import com.onesoft.fero.answer.ParamFeroKurenieChangeAnswer;
import com.onesoft.fero.answer.ParamFeroTuvChangeAnswer;
import com.onesoft.param.to.ParametrFeroKotolChangeTO;
import com.onesoft.param.to.ParametrFeroKurenieChangeTO;
import com.onesoft.param.to.ParametrFeroTuvChangeTO;
import com.onesoft.services.SmrekService;

/**
 * Created by exosj on 20.12.2016.
 */

public class  ChangeKurenie {

    ParamFeroKurenieChangeAnswer answerKurenieChange = new ParamFeroKurenieChangeAnswer();
    ParamFeroTuvChangeAnswer answerTuvChange = new ParamFeroTuvChangeAnswer();
    ParamFeroKotolChangeAnswer answerKotolChange = new ParamFeroKotolChangeAnswer();
    SmrekService smrekService;
    ChangeEnumStatus changeEnumStatus;

   public ParamFeroChangeKurenieAnswer changeKurenienow (Integer changeProgKur, Integer changeZiadTeploKure, Integer changeChodStop, Integer ziadanaRef){
       smrekService = new SmrekService();

        answerKurenieChange = smrekService.getKurenieChange(1, 0);
       if (changeProgKur !=null)
           answerKurenieChange.getChangeKurenie().setCprog_uk1(changeProgKur);
       if (changeZiadTeploKure !=null)
           switch (ziadanaRef) {
               case 0:  answerKurenieChange.getChangeKurenie().setTzmi_0(changeZiadTeploKure);
                   break;
               case 1:  answerKurenieChange.getChangeKurenie().setTzmi_1(changeZiadTeploKure);
                   break;
               case 2: answerKurenieChange.getChangeKurenie().setTzmi_2(changeZiadTeploKure);
                   break;
               case 3:  answerKurenieChange.getChangeKurenie().setTzmi_3(changeZiadTeploKure);
                   break;
               case 4:  answerKurenieChange.getChangeKurenie().setTzmi_4(changeZiadTeploKure);
                   break;
               case 9:  answerKurenieChange.getChangeKurenie().setTzmi_9(changeZiadTeploKure);
                   break;
               default: answerKurenieChange.getChangeKurenie().setTzmi_0(null);
                   break;
           }
        if (changeChodStop !=null){
            answerKurenieChange.getChangeKurenie().setChod_reg_uk1(changeChodStop);
        }
       ParametrFeroKurenieChangeTO request = answerKurenieChange.getChangeKurenie();
        ParamFeroChangeKurenieAnswer answer = smrekService.insertChangeKurenie( request);
         return answer;
    }

    public ParamFeroChangeKotolAnswer changeKotolnow (Integer changeZiadTeploKure, Integer changeChodStop,Integer status){
        smrekService = new SmrekService();
        answerKotolChange = smrekService.getKotolChange(1, 0);

        answerKotolChange.getParamKotolChange().setTz_ine(changeZiadTeploKure);
        answerKotolChange.getParamKotolChange().setN_zia_kot(changeChodStop);

        ParametrFeroKotolChangeTO request = answerKotolChange.getParamKotolChange();
        ParamFeroChangeKotolAnswer answer = smrekService.insertChangeKotol( request);
        return answer;
    }
    public ParamFeroChangeTuvAnswer changeTuvnow (Integer changeProgKur, Integer changeZiadTeploKure, Integer changeChodStop, Integer ziadanaRef){
        smrekService = new SmrekService();

        answerTuvChange = smrekService.getTuvChange(1, 0);
        if (changeProgKur !=null)
            answerTuvChange.getParamTuvChange().setCprog_tuv(changeProgKur);
        if (changeZiadTeploKure !=null)
            switch (ziadanaRef) {
                case 5:  answerTuvChange.getParamTuvChange().setTzmi_5(changeZiadTeploKure);
                    break;
                case 6:  answerTuvChange.getParamTuvChange().setTzmi_6(changeZiadTeploKure);
                    break;
                case 7: answerTuvChange.getParamTuvChange().setTzmi_7(changeZiadTeploKure);
                    break;
                case 8:  answerTuvChange.getParamTuvChange().setTzmi_8(changeZiadTeploKure);
                    break;
            }
        if (changeChodStop !=null){
            answerTuvChange.getParamTuvChange().setChod_reg_tuv(changeChodStop);
        }
        ParametrFeroTuvChangeTO request = answerTuvChange.getParamTuvChange();
        ParamFeroChangeTuvAnswer answer = smrekService.insertChangeKurenie(request);
        return answer;
    }

}
