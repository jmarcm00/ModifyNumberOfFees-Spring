package es.unileon.ulebank.assets.strategy.loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.ScheduledPaymentHandler;
import es.unileon.ulebank.assets.support.DateWrap;
import es.unileon.ulebank.assets.support.PaymentPeriod;

/**
 * Implementation of strategy for calculated all fee of the loan following the
 * progressive method
 * 
 * ************************* Formulate of the reason * *************************
 * 
 * v1.0 Initial version
 **/
public class ProgressiveMethod implements StrategyLoan {
	/**
	 * Object reference to the loan that wait calculating the fees
	 */
	private Loan loan;

	private ArrayList<ScheduledPayment> payments;

	/**
	 * Reason of the geometric progression
	 * **/
	private double reason;

	private DateWrap dateWrap;

	/**
	 * Constructor of ProgressiveMethod
	 * 
	 * @param loan
	 *            Loan that wait calculating the fees
	 **/
	public ProgressiveMethod(Loan loan, double reason) {
		this.reason = reason;
		this.loan = loan;
		this.payments = new ArrayList<ScheduledPayment>();
		this.dateWrap = new DateWrap(new Date(), this.loan.getPaymentPeriod());
	}

	/**
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 **/
	public double calculateInterestEf() {
		double interestEf = 0;
		interestEf = (this.loan.getInterest() / 100)
				/ (this.loan.getPaymentPeriod().getPeriod());
		return interestEf;
	}

	/**
	 * Method used to calculating the fees of loan
	 * 
	 **/
	public double calculateMonthlyFee() {
		double fee = 0;
		double interesEf = this.calculateInterestEf();
		int numFee = (this.loan.getAmortizationTime() / this.loan
				.getPaymentPeriod().getTime());

		double fracc = this.loan.getAmountOfMoney()
				* ((1 + interesEf - this.reason) / 1 - (Math.pow(
						(this.reason / 1 + interesEf), numFee)));
		fee = this.loan.getAmountOfMoney() * fracc;
		return fee;
	}

	/**
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 **/
	public void calculateInterest() {
		int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();

		double fee = this.calculateMonthlyFee();
		double interestEf = this.calculateInterestEf();
		double interest = 0;
		double amortized = 0;
		double totalCapital = fee * this.loan.getAmortizationTime();
		for (int i = 0; i < this.loan.getAmortizationTime(); i++) {
			totalCapital -= fee;
			interest = totalCapital * interestEf;
			amortized = fee - interest;
			totalCapital = round(totalCapital, 100);
			fee = round(fee, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);

			this.payments.add(new ScheduledPayment(this.dateWrap.getDate(),
					totalCapital, fee, interest, amortized,
					new ScheduledPaymentHandler(this.loan.getId(), this.loan
							.getLinkedAccount().getTitulars(), this.dateWrap
							.getDate())));
			this.dateWrap.updateDate();
		}
	}

	/**
	 * Used method to round.
	 * 
	 * @param num
	 *            :- Number to round
	 * @param factor
	 *            :- Number in decimal
	 * @return Numero :- Round number
	 **/
	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	/**
	 * Method used to calculating the fees of loan and give dates for perform
	 * this payments
	 **/
	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		ArrayList<ScheduledPayment> paymentsProgressive = new ArrayList<ScheduledPayment>();

		for (ScheduledPayment payment : this.payments) {
			paymentsProgressive.add(payment);
		}

		int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();
		double fee = this.calculateMonthlyFee();
		double interestEf = this.calculateInterestEf();
		double interest = 0;
		double amortized = 0;
		double totalLoan = this.loan.getAmountOfMoney();
		double totalCapital = fee * this.loan.getAmortizationTime();
		for (int i = 0; i < this.loan.getAmortizationTime(); i++) {
			interest = totalLoan * interestEf;
			amortized = fee - interest;
			totalLoan -= amortized;

			totalCapital = round(totalLoan, 100);
			fee = round(fee, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);

			paymentsProgressive.add(new ScheduledPayment(this.dateWrap
					.getDate(), fee, amortized, interest, totalCapital,
					new ScheduledPaymentHandler(this.loan.getId(), this.loan
							.getLinkedAccount().getTitulars(), this.dateWrap
							.getDate())));
			this.dateWrap.updateDate();
		}

		this.payments = paymentsProgressive;
		return paymentsProgressive;
	}

}