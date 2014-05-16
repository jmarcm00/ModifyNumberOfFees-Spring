/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.transacionManager;

import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.history.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * The transaction manager allow to do transactions between banks. We only need
 * to be register in it.
 *
 * @author runix
 */
public class TransactionManager {

	/**
	 * The list of banks
	 */
	private final List<Bank> banks;

	/**
	 * Create a new Transaction manager
	 */
	public TransactionManager() {
		this.banks = new ArrayList<>();
	}

	/**
	 * Add a new bank. The bank cannot be repeated.
	 *
	 * @param bank
	 *            ( bank to add)
	 *
	 * @return ( true if succes, false otherwise )
	 */
	public boolean addBank(Bank bank) {
		boolean repeated = false;
		if (bank != null) {
			for (int i = 0; i < banks.size() && !repeated; i++) {
				if (this.banks.get(i).getID().compareTo(bank.getID()) == 0) {
					repeated = true;
				}
			}
		} else {
			repeated = true;
		}
		return !repeated && this.banks.add(bank);
	}

	/**
	 * Delete a bank. If the bank doesn't exists the method retuns false and if
	 * the bank exists remove it and return true.
	 *
	 * @param id
	 *            ( The bank's id)
	 *
	 * @return ( true if sucess, false otherwise)
	 */
	public boolean deleteBank(Handler id) {
		boolean deleted = false;
		for (int i = 0; i < banks.size() && !deleted; i++) {
			if (banks.get(i).getID().compareTo(id) == 0) {
				banks.remove(i);
				deleted = true;
			}
		}
		return deleted;
	}

	/**
	 * Forward the transaction to the bank or return TransactionException if
	 * there isn't a bank with that id.
	 *
	 * @param t
	 *            ( transaction to forward )
	 *
	 * @param destine
	 *            ( destine for the transaction )
	 *
	 * @throws MalformedHandlerException
	 *             (If the destine handler isn't valid )
	 *
	 * @throws TransactionException
	 *             (If the bank isn't found or there are problems with the
	 *             transaction ).
	 */
	public void doTransaction(Transaction t, Handler destine)
			throws MalformedHandlerException, TransactionException {
		StringBuilder error = new StringBuilder();
		if (t != null && destine != null) {
			Handler destination = new AccountHandler(destine).getBankHandler();
			boolean found = false;
			for (int i = 0; i < banks.size() && !found; i++) {
				if (banks.get(i).getID().compareTo(destination) == 0) {
					banks.get(i).doTransaction(t, destine);
					found = true;
				}
			}
			if (!found) {
				error.append("Cannot found the bank ")
						.append(destination.toString()).append(" \n");
			}
		} else {
			error.append("The transaction or destine cannot be null");
		}
		if (error.length() > 0) {
			throw new TransactionException(error.toString());
		}
	}

}
