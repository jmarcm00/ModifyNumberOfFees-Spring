package es.unileon.ulebank.service;


import es.unileon.ulebank.assets.Loan;

public interface FeeManager {

	public void setNewNumberOfFees(int numFees);

	public Loan getFees();
	
}
