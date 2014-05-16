package es.unileon.ulebank.service;


import es.unileon.ulebank.assets.Loan;

/**
 * Fee Manager Interface
 * @brief Interface for the fee managers
 */
public interface FeeManager {

	/**
	 * Method that changes the number of fees from feeLimits.jsp
	 * @param numFees
	 */
	public void setNewNumberOfFees(int numFees);

	/**
     * Method that returns the loan of the management
     * @return
     */
	public Loan getFees();
	
}
