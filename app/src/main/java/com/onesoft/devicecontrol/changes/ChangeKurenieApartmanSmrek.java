package com.onesoft.devicecontrol.changes;

import com.onesoft.devicecontrol.enums.ChangeEnumStatus;
import com.onesoft.param.smrek.to.KurenieSmrekZmenaTO;
import com.onesoft.services.SmrekServiceApartman;
import com.onesoft.smrek.answer.GetParamForChangeKurenieAnswer;
import com.onesoft.smrek.answer.ParamChangeKurenieAnswer;

/**
 * Created by exosj on 20.12.2016.
 */

public class ChangeKurenieApartmanSmrek {

    GetParamForChangeKurenieAnswer answerKurenieChange = new GetParamForChangeKurenieAnswer();

    SmrekServiceApartman smrekServiceApartman;
    ChangeEnumStatus changeEnumStatus;

   public ParamChangeKurenieAnswer changeKurenienow (Integer changeProgKur, Integer changeZiadTeploKure, Integer changeChodStop, Integer ziadanaRef,Integer cisloApartmanu){
       smrekServiceApartman = new SmrekServiceApartman();

        answerKurenieChange = smrekServiceApartman.getKurenieChangeApartman(cisloApartmanu);
       if (changeProgKur !=null)
           answerKurenieChange.getParamForChangeList().get(0).setCprog_uk1(changeProgKur);
       if (changeZiadTeploKure !=null)
           switch (ziadanaRef) {
               case 1:  answerKurenieChange.getParamForChangeList().get(0).setTzmi_1(changeZiadTeploKure);
                   break;
               case 2: answerKurenieChange.getParamForChangeList().get(0).setTzmi_2(changeZiadTeploKure);
                   break;
               case 3:  answerKurenieChange.getParamForChangeList().get(0).setTzmi_3(changeZiadTeploKure);
                   break;
               case 4: answerKurenieChange.getParamForChangeList().get(0).setTzmi_4(changeZiadTeploKure);
                   break;
               default: answerKurenieChange.getParamForChangeList().get(0).setTzmi_1(null);
                   break;
           }
        if (changeChodStop !=null){
            answerKurenieChange.getParamForChangeList().get(0).setChod_reg_uk1(changeChodStop);
        }
       answerKurenieChange.getParamForChangeList().get(0).setCisloApartmanu(cisloApartmanu);
       KurenieSmrekZmenaTO request = answerKurenieChange.getParamForChangeList().get(0);
       ParamChangeKurenieAnswer answer = smrekServiceApartman.insertChangeKurenie(request);
         return answer;
    }


}
