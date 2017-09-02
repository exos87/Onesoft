package com.onesoft.services;

import com.onesoft.fero.answer.ParamFeroAllAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKotolAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKurenieAnswer;
import com.onesoft.fero.answer.ParamFeroChangeTuvAnswer;
import com.onesoft.fero.answer.ParamFeroKotolChangeAnswer;
import com.onesoft.fero.answer.ParamFeroKurenieChangeAnswer;
import com.onesoft.fero.answer.ParamFeroTuvChangeAnswer;
import com.onesoft.param.to.ParametrFeroKotolChangeTO;
import com.onesoft.param.to.ParametrFeroKurenieChangeTO;
import com.onesoft.param.to.ParametrFeroTuvChangeTO;
import com.onesoft.smrek.answer.GetKurenieAnswer;

/**
 * Created by exosj on 09.12.2016.
 */

public interface ISmrekService {
     ParamFeroAllAnswer allFeroParam(Integer limit, Integer change);
     ParamFeroChangeKurenieAnswer insertChangeKurenie(ParametrFeroKurenieChangeTO request);
    ParamFeroKurenieChangeAnswer getKurenieChange(Integer limit, Integer change);
    //change kotol
    public ParamFeroKotolChangeAnswer getKotolChange(Integer limit, Integer change);
    ParamFeroChangeKotolAnswer insertChangeKotol(ParametrFeroKotolChangeTO request);

    //change tuv
    public ParamFeroTuvChangeAnswer getTuvChange(Integer limit, Integer change);
    public ParamFeroChangeTuvAnswer insertChangeKurenie(ParametrFeroTuvChangeTO request);

    //getSmrekApartmanKurenie
    public GetKurenieAnswer getSmrekApratmanKurenieParam (Integer request);
}
