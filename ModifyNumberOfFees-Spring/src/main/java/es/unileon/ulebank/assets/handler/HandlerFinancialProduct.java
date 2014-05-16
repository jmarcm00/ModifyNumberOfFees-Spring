package es.unileon.ulebank.assets.handler;

public class HandlerFinancialProduct implements Handler {

	/**
	 * Loan identification number.
	 */
	private LoanIdentificationNumberCode loanIdentification;

	/**
	 * Handler which is passed an instance of the class
	 * LoanIdentificationNumberCode responsible for generating the
	 * identification number.
	 * 
	 * @param linc
	 *            Instance of LoanIdentificationNumberCode
	 */
	public HandlerFinancialProduct(LoanIdentificationNumberCode linc) {
		this.loanIdentification = linc;
	}

	@Override
	public int compareTo(Handler anotherHandler) {
		return this.loanIdentification.toString().compareTo(
				anotherHandler.toString());
	}

	@Override
	public String toString() {
		return this.loanIdentification.toString();
	}
}
