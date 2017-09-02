package com.onesoft.fero.answer.enums;



/**
 * Enum zakladnych statusov pre odpovede na poziadavky
 * 
 * @author lovas
 *
 */

public enum AnswerStatusEnum {

	/** Poziadavka prebehla v poriadku*/
	OK,
	
	/** Poziadavka zlyhala */
	FAILED,
	
	/** Vstupne parametre nie su spravne */
	VALIDATION_FAILED
}
