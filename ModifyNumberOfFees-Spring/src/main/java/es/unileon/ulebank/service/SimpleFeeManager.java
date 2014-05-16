package es.unileon.ulebank.service;

import es.unileon.ulebank.assets.Loan;

public class SimpleFeeManager implements FeeManager {
	
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
