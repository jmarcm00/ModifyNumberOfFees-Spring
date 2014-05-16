package es.unileon.ulebank.assets.support;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanList<T extends Loan> {
	/**
	 * Loans list
	 */
	private ArrayList<T> loans;

	/**
	 * Date to do the payds
	 */
	private Date date;

	/**
	 * Calendar for simulate the date
	 */
	private Calendar calendar;

	/**
	 * Constructor. In this we use the actual date
	 */
	public LoanList() {
		this.loans = new ArrayList<T>();
		this.date = new Date();
		this.calendar = Calendar.getInstance();
		this.calendar.setTime(this.date);
	}

	/**
	 * Add loans to the list
	 * 
	 * @param loan
	 *            loan to add
	 * @return true if the loan have been added. In the other case the method
	 *         return false
	 */
	public boolean addLoan(T loan) {
		return this.loans.add(loan);
	}

	/**
	 * Delete loans from the list
	 * 
	 * @param loan
	 *            loan to delete
	 * @return true if the loan has been deleted succesfully, cancel if not.
	 */
	public boolean removeLoan(T loan) {
		boolean removed = false;
		T removedLoan = null;

		for (int i = 0; i < this.loans.size() && !removed; i++) {
			removedLoan = this.loans.get(i);
			if (removedLoan.getIdFinancialProduct().compareTo(
					loan.getIdFinancialProduct()) == 0) {
				this.loans.remove(i);
				removed = true;
			}
		}

		return removed;

	}

	/**
	 * Delete loans from the list
	 * 
	 * @param idLoan
	 *            loan to delete
	 * @return true if the loan has been deleted succesfully, false if not.
	 */
	public boolean removeLoan(Handler idLoan) {
		boolean removed = false;
		T removedLoan = null;

		for (int i = 0; i < this.loans.size() && !removed; i++) {
			removedLoan = this.loans.get(i);
			if (removedLoan.getIdFinancialProduct().compareTo(idLoan) == 0) {
				this.loans.remove(i);
				removed = true;
			}
		}

		return removed;
	}

	/**
	 * Do the payds
	 */
	private void doLoanPayments() {
		for (int i = 0; i < this.loans.size(); i++) {
			Loan loan = this.loans.get(i);
			ArrayList<ScheduledPayment> payments = loan.getPayments();
			for (int k = 0; k < payments.size(); k++) {
				ScheduledPayment payment = payments.get(k);
				if (!payment.isPaid()) {
					if (payment.getExpiration().getTime() == this.date
							.getTime()) {
						loan.paid(k);
					}
				}
			}
		}
	}

	/**
	 * This method can forward the days from the real date
	 * 
	 * @param days
	 *            days that you can forward
	 */
	public void forwardDays(int days) {
		if (days > 0) {
			this.calendar.add(Calendar.DATE, days);
			this.date = this.calendar.getTime();
			this.calendar.setTime(this.date);
			doLoanPayments();
		}
	}

	/**
	 * This method can backguard the days from the real date
	 * 
	 * @param days
	 *            days that you can to backguard
	 */
	public void backwardDays(int days) {
		if (days < 0) {
			this.calendar.add(Calendar.DATE, days);
			this.date = this.calendar.getTime();
			this.calendar.setTime(this.date);
			doLoanPayments();
		}
	}

	/**
	 * This method can forward the months from the real date
	 * 
	 * @param months
	 *            months that you can forward
	 */
	public void forwardMonths(int months) {
		if (months > 0) {
			this.calendar.add(Calendar.MONTH, months);
			this.date = this.calendar.getTime();
			this.calendar.setTime(this.date);
			doLoanPayments();
		}
	}

	/**
	 * This method can backguard the days from the real date
	 * 
	 * @param months
	 *            months that you can backguard
	 */
	public void backwardMonths(int months) {
		if (months < 0) {
			this.calendar.add(Calendar.MONTH, months);
			this.date = this.calendar.getTime();
			this.calendar.setTime(this.date);
			doLoanPayments();
		}
	}

	/**
	 * This method can forward the months from the real date
	 * 
	 * @param years
	 *            years that you can forward
	 */
	public void forwardYears(int years) {
		if (years > 0) {
			this.calendar.add(Calendar.YEAR, years);
			this.date = this.calendar.getTime();
			this.calendar.setTime(this.date);
			doLoanPayments();
		}
	}

	/**
	 * This method can backguard the yars from the real date
	 * 
	 * @param years
	 *            years that you can backguard
	 */
	public void backwardYears(int years) {
		if (years < 0) {
			this.calendar.add(Calendar.YEAR, years);
			this.date = this.calendar.getTime();
			this.calendar.setTime(this.date);
			doLoanPayments();
		}
	}

	/**
	 * Method that returns the actual date to do the payment of the fees
	 * 
	 * @return Date date used for the pay
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Method for change the payment dates
	 * 
	 * @param date
	 *            when we want to do the payments
	 */
	public void setDate(Date date) {
		this.date = date;
		this.calendar.setTime(date);
		doLoanPayments();
	}

	/**
	 * Method to change the payment date for the fees
	 * 
	 * @param date
	 *            when we want to do the payments
	 */
	public void newDate(int day, int month, int year) {
		Calendar newCalendar = new GregorianCalendar(year, month, day);
		setDate(newCalendar.getTime());
	}

}
