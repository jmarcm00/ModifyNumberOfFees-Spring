package es.unileon.ulebank.assets.strategy.loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.ScheduledPaymentHandler;
import es.unileon.ulebank.assets.support.DateWrap;
import es.unileon.ulebank.assets.support.PaymentPeriod;

/**
 * Implementacion de una de las estrategias para calcular las cuotas mensuales
 * de los prestamos siguiendo el metodo aleman
 * 
 * 
 * v1.0 Initial version
 */
public class GermanMethod implements StrategyLoan {

	/**
	 * Object reference to the loan that wait calculating the fees
	 */
	private Loan loan;

	private ArrayList<ScheduledPayment> payments;

	private DateWrap date;

	/**
	 * En el constructor se pasan el capital que va a ser prestado, el tipo de
	 * interes en tanto por ciento y el tiempo para ser amortizado.
	 * 
	 * @param money
	 * @param interest
	 * @param amortizationTime
	 */
	public GermanMethod(Loan loan) {
		this.loan = loan;
		this.payments = new ArrayList<ScheduledPayment>();
		this.date = new DateWrap(new Date(), this.loan.getPaymentPeriod());
	}

	/**
	 * Metodo de la interfaz al cual se llama para realizar el calculo
	 */
	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		return doNewGermanMethod();
	}

	/**
	 * Metodo que calcula todas las cuotas utilizando el metodo aleman
	 */
	public ArrayList<ScheduledPayment> doNewGermanMethod() {
		ArrayList<ScheduledPayment> paymentsGerman = new ArrayList<>();
		// Capital inicial
		double capital = this.loan.getDebt();

		// Calculamo el termino amortizativo constante
		double amortizationTerm = this.loan.getDebt() * this.loan.getInterest();
		amortizationTerm = round(amortizationTerm, 1);

		double interestsMoney = this.loan.getDebt() * this.loan.getInterest();
		interestsMoney = round(interestsMoney, 1);

		//En la primera cuota no se amortiza nada
		double amortization = amortizationTerm - interestsMoney;
		amortization = round(amortization, 1);

		capital = round(capital, 1);

		paymentsGerman.add(new ScheduledPayment(this.date.getDate(),
				amortizationTerm, amortization, interestsMoney, capital,
				new ScheduledPaymentHandler(this.loan.getId(), this.loan.getLinkedAccount()
						.getTitulars(), this.date.getDate())));
		this.date.updateDate();

		amortizationTerm = (this.loan.getDebt() * this.loan.getInterest())
				/ (1.0 - Math.pow((1.0 - this.loan.getInterest()),
						this.loan.getAmortizationTime()));

		amortizationTerm = round(amortizationTerm, 1);

		int n = this.loan.getAmortizationTime();
		int k = 1;

		while (capital > 0) {
			// Calculo de la cualtia de capital amortizado
			capital = (amortizationTerm * (1.0 - Math.pow(
					1 - this.loan.getInterest(), n - k)))
					/ this.loan.getInterest();
			capital = round(capital, 1);
			k++;

			interestsMoney = capital * this.loan.getInterest();
			interestsMoney = round(interestsMoney, 1);

			amortization = amortizationTerm - interestsMoney;
			amortization = round(amortization, 1);

			paymentsGerman.add(new ScheduledPayment(this.date.getDate(),
					amortizationTerm, amortization, interestsMoney, capital,
					new ScheduledPaymentHandler(this.loan.getId(), this.loan.getLinkedAccount()
							.getTitulars(), this.date.getDate())));
			this.date.updateDate();
		}

		this.payments = paymentsGerman;
		return paymentsGerman;

	}

	/**
	 * Metodo utilizado para redondear un numero. Se deben de pasa el numero a
	 * redondear y el factor.
	 * 
	 * @param num
	 *            Numero que se quiere redondear
	 * @param factor
	 *            Numero de decimales a los que se quiere redondear
	 * @return Numero redondeado
	 */
	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	/**
	 * Metodo que devuelve una lista con todos los pagos a realizar.
	 * 
	 * @return lista de los pagos
	 */
	public ArrayList<ScheduledPayment> getPayments() {
		return this.payments;
	}

	/**
	 * Metodo utilizado para devolver un string con las cuota halladas por el
	 * metodo aleman
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (ScheduledPayment payment : this.payments) {
			sb.append(payment.toString());
			sb.append("\n");
		}

		return sb.toString();
	}

}
