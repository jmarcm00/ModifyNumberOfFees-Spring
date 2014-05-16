package es.unileon.ulebank.assets.strategy.loan;

import java.util.ArrayList;
import java.util.Date;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.ScheduledPaymentHandler;
import es.unileon.ulebank.assets.support.DateWrap;

public class ItalianMethod implements StrategyLoan {

	private Loan loan;

	private ArrayList<ScheduledPayment> payments;

	private DateWrap date;

	/**
	 * Constructor al cual se le pasa el capital, el tipo de interes y el numero
	 * de cuotas
	 * 
	 * @param money
	 * @param interes
	 * @param numeroCuotas
	 */
	public ItalianMethod(Loan loan) {
		this.loan = loan;
		this.date = new DateWrap(new Date(), this.loan.getPaymentPeriod());
		this.payments = new ArrayList<ScheduledPayment>();
	}

	/**
	 * Metodo que realiza todo el calculo de las coutas
	 */
	public ArrayList<ScheduledPayment> doItalianMethod() {
		ArrayList<ScheduledPayment> paymentsItalian = new ArrayList<>();

		double moneyPending = this.loan.getDebt();
		double moneyInterest = moneyPending * this.loan.getInterest();
		double moneyAmortization = this.loan.getDebt()
				/ this.loan.getAmortizationTime();
		double amortization = moneyInterest + moneyAmortization;

		amortization = round(amortization, 100);
		moneyPending = round(moneyPending, 100);
		moneyInterest = round(moneyInterest, 100);
		moneyAmortization = round(moneyAmortization, 100);

		moneyPending -= moneyAmortization;
		moneyPending = round(moneyPending, 100);
		paymentsItalian.add(new ScheduledPayment(this.date.getDate(),
				amortization, moneyAmortization, moneyInterest, moneyPending,
				new ScheduledPaymentHandler(this.loan.getId(), this.loan.getLinkedAccount()
						.getTitulars(), this.date.getDate())));

		this.date.updateDate();

		while (moneyPending >= 0) {

			moneyInterest = moneyPending * this.loan.getInterest();
			amortization = moneyAmortization + moneyInterest;

			amortization = round(amortization, 100);
			moneyPending = round(moneyPending, 100);
			moneyInterest = round(moneyInterest, 100);
			moneyAmortization = round(moneyAmortization, 100);

			moneyPending -= moneyAmortization;
			moneyPending = round(moneyPending, 100);

			paymentsItalian.add(new ScheduledPayment(this.date.getDate(),
					amortization, moneyAmortization, moneyInterest,
					moneyPending, new ScheduledPaymentHandler(this.loan.getId(), this.loan
							.getLinkedAccount().getTitulars(), this.date
							.getDate())));
			this.date.updateDate();
		}

		this.payments = paymentsItalian;
		return paymentsItalian;

	}

	/**
	 * Metodo de la interfaz el cual se llama para hacer el calculo de las
	 * cuotas
	 */
	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		return doItalianMethod();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (ScheduledPayment payment : this.payments) {
			sb.append(payment.toString());
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * Metodo que devuleve una lista con los pagos a realizar
	 * 
	 * @return
	 */
	public ArrayList<ScheduledPayment> getPayments() {
		return this.payments;
	}

	/**
	 * Metodo utilizado para round un numero. Se deben de pasa el numero a
	 * redindear y el factor.
	 * 
	 * @param num
	 * @param factor
	 * @return
	 */
	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

}
