/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.account;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.Transaction;
import java.util.List;

/**
 *
 * @author runix
 */
public interface LiquidationStrategy {

	/**
	 * Perform liquitadation based on account history.
	 *
	 * @param transactions
	 *            ( The transactions for doing the liquidation )
	 * @param months
	 *            ( The number of months )
	 *
	 *            max(transaction.date)-min(transaction.date)==months
	 *
	 * @return (The amount of money to pay )
	 */
	public Transaction doLiquidation(List<Transaction> transactions, int months);

	/**
	 * The liquidation strategy id.
	 *
	 * @return (the id)
	 */
	public Handler getID();
}
