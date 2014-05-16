package es.unileon.ulebank.assets.gui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

/**
 *
 * @author amdiaz8
 */
public class PaymentsLoanModel extends AbstractListModel {

	private ArrayList<ScheduledPayment> payments;

	public PaymentsLoanModel() {
		this.payments = new ArrayList<ScheduledPayment>();
	}

	public PaymentsLoanModel(ArrayList<ScheduledPayment> payments) {
		this.payments = payments;
	}

	@Override
	public int getSize() {
		return this.payments.size();
	}

	@Override
	public ScheduledPayment getElementAt(int index) {
		return this.payments.get(index);
	}

	public void addPayment(ScheduledPayment payment) {
		this.payments.add(payment);
	}

	public void setPayments(ArrayList<ScheduledPayment> payments) {
		this.payments = payments;
	}

	public ArrayList<ScheduledPayment> getPayments() {
		return payments;
	}

}
