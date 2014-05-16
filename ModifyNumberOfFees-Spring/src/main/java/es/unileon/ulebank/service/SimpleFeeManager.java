package es.unileon.ulebank.service;

import es.unileon.ulebank.assets.Loan;

/**
 * Simple Fee Manager Class
 * @brief Class which manages the loan
 */
public class SimpleFeeManager implements FeeManager {
	
	/**
	 * Loan which modifies from changeNumFees.jsp
	 */
	private Loan fees;
	
	@Override
	public void setNewNumberOfFees(int numFees) {
		fees.setAmortizationTime(numFees);
		fees.calcPayments();
	}

	@Override
	public Loan getFees() {
		return fees;
	}

	public void setFees(Loan fees) {
		this.fees = fees;
	}
	
}
