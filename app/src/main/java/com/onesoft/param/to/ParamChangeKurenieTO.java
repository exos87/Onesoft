package com.onesoft.param.to;

/**
 * Created by exosj on 28.11.2016.
 */

public class ParamChangeKurenieTO {


    Integer limit;

    /** Default serial number */
    private static final long serialVersionUID = 1L;

    public ParamChangeKurenieTO() {
        super();
    }

    public Integer getLimit() {
        return limit;
    }


    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    @Override
    public String toString() {
        return "BaseParamUkRequestTO [limit=" + limit + "]";
    }



}
