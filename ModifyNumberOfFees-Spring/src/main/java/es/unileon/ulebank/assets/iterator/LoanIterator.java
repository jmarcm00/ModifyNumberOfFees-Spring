package es.unileon.ulebank.assets.iterator;

import java.util.Iterator;
import java.util.List;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanIterator implements Iterator<ScheduledPayment> {

	/**
	 * List where the fees of a loan are stored
	 */
	private List<ScheduledPayment> loanPayments;
	/**
	 * Position that a fee are stored in the list
	 */
	private int position;
	
	public LoanIterator(List<ScheduledPayment> loanPayments) {
		this.loanPayments = loanPayments;
		this.position = 0;
	}

	/**
	 * Check if one fee is not the last of the loan
	 */
	@Override
	public boolean hasNext() {
		if(this.position < this.loanPayments.size()) return true;
		return false;
	}

	/**
	 * Change the position of the iterator in the fees list
	 */
	@Override
	public ScheduledPayment next() {
		ScheduledPayment payment = this.loanPayments.get(this.position);
		this.position++;
		return payment;
	}

	@Override
	public void remove() {
		
	}

}
