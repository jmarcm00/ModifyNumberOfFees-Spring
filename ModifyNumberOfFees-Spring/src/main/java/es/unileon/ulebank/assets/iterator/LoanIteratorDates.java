package es.unileon.ulebank.assets.iterator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanIteratorDates implements Iterator<ScheduledPayment> {

	/**
	 * List where the fees of a loan are stored
	 */
	private List<ScheduledPayment> loanPayments;
	/**
	 * Position that a fee are stored in the list
	 */
	private int position;
	/**
	 * Date when the loan start
	 */
	private Date startDate;
	/**
	 * Date when the loan end
	 */
	private Date endDate;

	public LoanIteratorDates(List<ScheduledPayment> loanPayments, Date startDate,
			Date endDate) {
		this.loanPayments = loanPayments;
		this.startDate = startDate;
		this.endDate = endDate;
		this.position = 0;
	}

	/**
	 * Check if a fee is not the last
	 * @return
	 */
	@Override
	public boolean hasNext() {
		while(this.position < this.loanPayments.size()){
			ScheduledPayment payment = this.loanPayments.get(this.position);
			if(isWithinRange(payment.getExpiration())){
				return true;
			}else{
				this.position++;
			}
		}
		
		return false;
	}

	/**
	 * Advanced in the list
	 * @return payment The new payment that the iterator register
	 */
	@Override
	public ScheduledPayment next() {
		ScheduledPayment payment = this.loanPayments.get(this.position);
		this.position++;
		return payment;
	}

	@Override
	public void remove() {
		//TODO not implemented!
	}
	/**
	 * Check if the date is between the init and finish date
	 * @param testDate actual date
	 * @return True if the date is between this two dates, false if not.
	 */
	private boolean isWithinRange(Date testDate) {
		return ((testDate.getTime() >= this.startDate.getTime())
				&& (testDate.getTime() <= this.endDate.getTime()));
	}

}
