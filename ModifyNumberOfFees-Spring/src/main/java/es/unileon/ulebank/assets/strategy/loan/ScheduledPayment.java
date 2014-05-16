package es.unileon.ulebank.assets.strategy.loan;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.handler.ScheduledPaymentHandler;

public class ScheduledPayment {
	/**
	 * Fecha en la que se hace pago
	 */
	private Date paymentDate;

	/**
	 * Fecha limite del pago
	 */
	private Date expiration;
	/**
	 * Importe que debe ser pagado
	 */
	private double amountOfTerm;
	/**
	 * Cantidad de dinero que es destinada a pagar la deuda.
	 */
	private double amortization;
	/**
	 * Cantidad de dinero que pertenece a los intereses generados y que no esta
	 * destinada a amorizar el prestamo.
	 */
	private double interests;
	/**
	 * Capital que aun no ha sido amortizado
	 */
	private double outstandingCapital;

	/**
	 * Indica si la couta esta o no pagada
	 */
	private boolean isPaid;
	
	
	/**
	 * Handler id
	 */
	private Handler scheduledPaymentId;
	
	/**
	 * Constructor al cual se le pasan la fecha de pago, el importe de la couta,
	 * la cantidad perteneciente a la amorizacion, la cantidad que pertenece a
	 * los intereses y la cantidad de capital que aun falta por amortizar
	 * 
	 * @param expiration
	 * @param importOfterm
	 * @param amortization
	 * @param interests
	 * @param pendingCapital
	 */
	public ScheduledPayment(Date expiration, double importOfterm,
			double amortization, double interests, double pendingCapital, ScheduledPaymentHandler scheduledPaymentId) {
		this.expiration = expiration;
		this.amountOfTerm = importOfterm;
		this.amortization = amortization;
		this.interests = interests;
		this.outstandingCapital = pendingCapital;
		this.setPaid(false);
		this.paymentDate = null;
		
		this.scheduledPaymentId = scheduledPaymentId;

	}

	/**
	 * Devuelve la fecha en la cual el pago debe ser abonado.
	 * 
	 * @return
	 */
	public Date getExpiration() {
		return expiration;
	}

	/**
	 * Envia la fecha de finalizaci�n del plazo de pago
	 * 
	 * @param expiration
	 *            Date con la fecha de finalizaci�n del pago.
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	/**
	 * Devuelve el importe de la couta
	 * 
	 * @return
	 */
	public double getImportOfTerm() {
		return amountOfTerm;
	}

	/**
	 * Envia el importe de la cuota.
	 * 
	 * @param amountOfTerm
	 *            Double con el valor de la cuota
	 */
	public void setImportOfTerm(double amountOfTerm) {
		this.amountOfTerm = amountOfTerm;
	}

	/**
	 * Devuelve la cantidad de dinero amortizado en ese pago.
	 * 
	 * @return
	 */
	public double getAmortization() {
		return amortization;
	}

	/**
	 * Envia la cantidad de dinero amortizado en ese pago
	 * 
	 * @param amortization
	 *            Double con la cantidad de dinero amortizado en ese pago
	 */
	public void setAmortization(double amortization) {
		this.amortization = amortization;
	}

	/**
	 * Devuelve la cantidad de dinero perteneciente a los intereses.
	 * 
	 * @return
	 */
	public double getInterests() {
		return interests;
	}

	/**
	 * Envia la cantidad de dinero que son intereses
	 * 
	 * @param interests
	 *            Double con el valor de la cantidad de dinero que son intereses
	 */
	public void setInterests(double interests) {
		this.interests = interests;
	}

	/**
	 * Devuelve la cantidad de dinero que aun esta pendiente de ser amortizado.
	 * 
	 * @return
	 */
	public double getOutstandingCapital() {
		return outstandingCapital;
	}

	/**
	 * Envia la cantidad de dinero que aun esta pendiente de ser amortizado
	 * 
	 * @param outstandingCapital
	 *            Double con la cantidad de dinero que aun esta pendiente de ser
	 *            amortizado
	 */
	public void setOutstandingCapital(double outstandingCapital) {
		this.outstandingCapital = outstandingCapital;
	}

	@Override
	/**
	 * Devuelve un string con todos los datos acerca del pago que se tiene que realizar
	 */
	public String toString() {
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer a = new StringBuffer();
		a.append("Expiration date: " + form.format(expiration)
				+ " Import of term: " + amountOfTerm
				+ " Capital Amortization: " + amortization + " Interests: "
				+ interests + " Pending capital: " + outstandingCapital
				+ " is paid: " + isPaid);

		if (paymentDate != null) {
			a.append(" Payment date : " + form.format(paymentDate));
		} else {
			a.append(" Payment date : NOT PAID YET ");
		}

		return a.toString();
	}

	public boolean isPaid() {
		return this.isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public Handler getId() {
		return this.scheduledPaymentId;
	}

}
