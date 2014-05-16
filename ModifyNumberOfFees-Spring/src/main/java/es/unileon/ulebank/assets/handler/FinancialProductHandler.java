package es.unileon.ulebank.assets.handler;

import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.handler.MalformedHandlerException;

public class FinancialProductHandler implements Handler {
	
	/**
	 * Loan identification number.
	 */
	private LoanIdentificationNumberCode loanIdentification;
	
	public FinancialProductHandler(String type, String countryCode) throws MalformedHandlerException{
		try {
			this.loanIdentification = new LoanIdentificationNumberCode(type, countryCode);
		} catch (LINCMalformedException e) {
			throw new MalformedHandlerException(e.getMessage());
		}
	}
	
	/**
	 * Handler which is passed an instance of the class LoanIdentificationNumberCode responsible 
	 * for generating the identification number.
	 * @param linc Instance of LoanIdentificationNumberCode
	 */
	@Deprecated
	public FinancialProductHandler(LoanIdentificationNumberCode linc) {
		this.loanIdentification = linc;
	}
	
	@Override
	public int compareTo(Handler anotherHandler) {
		return this.loanIdentification.toString().compareTo(anotherHandler.toString());
	}
	
	@Override
	public String toString() {
		return this.loanIdentification.toString();
	}
}
