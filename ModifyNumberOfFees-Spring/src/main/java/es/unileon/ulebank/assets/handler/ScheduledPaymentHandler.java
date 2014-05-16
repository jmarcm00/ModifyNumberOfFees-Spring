package es.unileon.ulebank.assets.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.unileon.ulebank.client.Client;

public class ScheduledPaymentHandler implements Handler {
	
	private StringBuffer id;
	
	/**
	 * List of clients of the account, the date of the payment and
	 * the loan ID
	 * 
	 * loandID-clientID-clientID-year-month-day
	 * @param loanID
	 * @param clients
	 * @param datePayment
	 */
	public ScheduledPaymentHandler(Handler loanId, List<Client> clients, Date datePayment) {
		this.id = new StringBuffer();
		
		this.id.append(loanId.toString());
		this.id.append("-");
		
		for(Client client : clients){
			this.id.append(client.getId());
			this.id.append("-");
		}
		
		long time =  datePayment.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		
		
		this.id.append(cal.get(Calendar.YEAR));
		this.id.append("-");
		this.id.append(cal.get(Calendar.MONTH));
		this.id.append("-");
		this.id.append(cal.get(Calendar.DATE));
	}
	
	@Override
	public int compareTo(Handler another) {
		return this.id.toString().compareTo(another.toString());
	}
	
	@Override
	public String toString() {
		return this.id.toString();
	}
	
}
