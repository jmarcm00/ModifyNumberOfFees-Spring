package es.unileon.ulebank.assets.strategy.loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.ScheduledPaymentHandler;
import es.unileon.ulebank.assets.support.PaymentPeriod;

/**
 * Implementation of strategy for calculated all fee of the loan following the
 * french method
 * 
 * 
 * v1.0 Initial version
 */
public class DefaultLoanStrategy implements StrategyLoan {

	private ArrayList<ScheduledPayment> payments;

	/**
	 * Object reference to the loan that wait calculating the fees
	 */
	private Loan loan;

	/**
	 * Constructor of FrenchMethod
	 * 
	 * @param loan
	 *            Loan that wait calculating the fees
	 */
	public DefaultLoanStrategy(Loan loan) {
		this.loan = loan;
		this.payments = loan.getPayments();
	}

	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		Calendar date = Calendar.getInstance();

		int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();

		if (this.payments.size() != 0) {
			// si el arraylist ya tiene elementos partimos de
			// la ultima fecha limite para pagar
			date.setTime(this.payments.get(this.payments.size() - 1)
					.getExpiration());
		}

		double fee = this.loan.getPeriodFee();
		fee = round(fee, 100);
		// double interestEf = this.calculateInterestEf();
		double interestEf = this.loan.getInterest();
		double interest = 0;
		double amortized = 0;
		double totalLoan = this.loan.getDebt();
		double totalCapital;
		boolean control = false;
		while (!control) {

			interest = totalLoan * interestEf;
			amortized = fee - interest;

			if (totalLoan > fee) {
				totalLoan -= fee;
			} else {
				fee = round(totalLoan, 100);
				totalLoan = 0;

				control = true;
			}

			totalCapital = round(totalLoan, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);

			date.add(Calendar.MONTH, monthToAdd);
			ScheduledPayment payment = new ScheduledPayment(date.getTime(),
					fee, amortized, interest, totalCapital,
					new ScheduledPaymentHandler(this.loan.getId(), this.loan.getLinkedAccount()
							.getTitulars(), date.getTime()));
			this.payments.add(payment);
			return payments;

		}
		return payments;

	}

	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	// /**
	// * Object reference to the loan that wait calculating the fees
	// */
	// private Loan loan;
	//
	// /**
	// * Constructor of FrenchMethod
	// *
	// * @param loan
	// * Loan that wait calculating the fees
	// */
	// public DefaultCopyOfFrenchMethod(Loan loan) {
	// this.loan = loan;
	// }
	//
	// /**
	// * Method used to calculating the effective interest of do the fees
	// *
	// * @return Double with the value of this effective interest
	// */
	// public double calculateInterestEf() {
	// double interestEf = 0;
	// interestEf = (this.loan.getInterest() / 100)
	// / this.loan.getPaymentPeriod().getPeriod();
	// return interestEf;
	// }
	//
	// /**
	// * Method used to calculating the fees of loan
	// *
	// * @return Double with the value of this fees of loan
	// */
	// public double calculateMonthlyFee() {
	// double fee = 0;
	// double interesEf = this.calculateInterestEf();
	// int numFee = (this.loan.getAmortizationTime() / this.calculatePayment());
	// double fracc = ((Math.pow((1 + interesEf), numFee)) * interesEf)
	// / (Math.pow(1 + interesEf, numFee) - 1);
	// fee = this.loan.getAmountOfMoney() * fracc;
	// return fee;
	// }
	//
	// /**
	// * Method used to invert the payment period for calculated the total
	// number
	// * of payments for repayment the loan
	// *
	// * @return Integer with the value of number of payments in one year
	// */
	// private int calculatePayment() {
	// int num = this.loan.getPaymentPeriod().getPeriod();
	// if (num == 12)
	// return 1;
	// else if (num == 6)
	// return 2;
	// else if (num == 4)
	// return 3;
	// else if (num == 2)
	// return 6;
	// else if (num == 1)
	// return 12;
	// return 0;
	// }
	//
	// /**
	// * Method used to calculating the fees of loan and give dates for perform
	// * this payments
	// */
	// @Override
	// public void doCalculationOfPayments() {
	// int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();
	// Calendar date = Calendar.getInstance();
	// double fee = this.calculateMonthlyFee();
	// double interestEf = this.calculateInterestEf();
	// double interest = 0;
	// double amortized = 0;
	// double totalLoan = this.loan.getAmountOfMoney();
	// double totalCapital = fee * this.loan.getAmortizationTime();
	//
	// for (int i = 0; i < this.loan.getAmortizationTime(); i++) {
	// interest = totalLoan * interestEf;
	// amortized = fee - interest;
	// totalLoan -= amortized;
	//
	// totalCapital = round(totalLoan, 100);
	// fee = round(fee, 100);
	// amortized = round(amortized, 100);
	// interest = round(interest, 100);
	//
	// date.add(Calendar.MONTH, monthToAdd);
	// this.payments.add(new ScheduledPayment(date.getTime(), fee,
	// amortized, interest, totalCapital));
	// }
	// }
	//
	// /**
	// * Metodo utilizado para redondear un numero. Se deben de pasa el numero a
	// * redondear y el factor.
	// *
	// * @param num
	// * Numero que se quiere redondear
	// * @param factor
	// * Numero de decimales a los que se quiere redondear
	// * @return Numero redondeado
	// */
	// public double round(double num, int factor) {
	// num = num * factor;
	// num = Math.round(num);
	// num = num / factor;
	// return num;
	// }

}
