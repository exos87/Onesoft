package com.onesoft.services;

import com.onesoft.param.smrek.to.KurenieSmrekZmenaTO;
import com.onesoft.smrek.answer.GetKurenieAnswer;
import com.onesoft.smrek.answer.GetParamForChangeKurenieAnswer;
import com.onesoft.smrek.answer.ParamChangeKurenieAnswer;

/**
 * Created by exosj on 09.12.2016.
 */

public interface ISmrekServiceApartman {
    //Nacitanie dat z db pre prislusny apartman
    GetKurenieAnswer getParamForApartmanKurenie(Integer cisloApartmanu);
    //Nacita zmenove hodnoty z db zmena
    GetParamForChangeKurenieAnswer getKurenieChangeApartman(Integer cisloApartmanu);
    //Insertne zmeny do Db pre dany apartman pre kurenie
    ParamChangeKurenieAnswer insertChangeKurenie(KurenieSmrekZmenaTO request);
}
