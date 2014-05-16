/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.bank;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.transacionManager.TransactionManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author runix
 */
public class Bank {
	private static final Logger LOG = Logger.getLogger(Account.class.getName());

	private final List<Office> offices;
	private final Handler bankID;
	private final TransactionManager manager;

	/**
	 *
	 * @param manager
	 * @param bankID
	 * @throws MalformedHandlerException
	 */
	public Bank(TransactionManager manager, Handler bankID)
			throws MalformedHandlerException {
		this.bankID = new BankHandler(bankID.toString());
		this.offices = new ArrayList<>();
		this.manager = manager;
	}

	/**
	 *
	 * @return
	 */
	public Handler getID() {
		return this.bankID;
	}

	/**
	 *
	 * @param office
	 * @return
	 */
	public boolean addOffice(Office office) {
		if (office != null) {
			for (int i = 0; i < offices.size(); ++i) {
				if (offices.get(i).getIdOffice()
						.compareTo(office.getIdOffice()) == 0) {
					return false;
				}
			}
			return this.offices.add(office);
		}
		return false;
	}

	/**
	 *
	 * @param office
	 * @return
	 */
	public boolean removeOffice(Handler office) {
		boolean removed = false;
		if (office != null) {
			for (int i = 0; i < offices.size() && !removed; ++i) {
				if (offices.get(i).getIdOffice().compareTo(office) == 0) {
					this.offices.remove(i);
					removed = true;
				}
			}
		}
		return removed;
	}

	/**
	 *
	 * @param t
	 * @param destine
	 * @throws MalformedHandlerException
	 * @throws TransactionException
	 */
	public void doTransaction(Transaction t, Handler destine)
			throws MalformedHandlerException, TransactionException {
		StringBuilder error = new StringBuilder();
		if (t != null && destine != null) {
			AccountHandler handler = new AccountHandler(destine);
			Handler bank = handler.getBankHandler();
			if (this.bankID.compareTo(bank) == 0) {
				Handler office = handler.getOfficeHandler();
				boolean found = false;
				for (int i = 0; i < this.offices.size() && !found; i++) {
					if (this.offices.get(i).getIdOffice().compareTo(office) == 0) {
						found = true;
						this.offices.get(i).doTransaction(t, destine);
					}
				}
				if (!found) {
					error.append("Error, office not found\n");
				}
			} else {
				this.manager.doTransaction(t, destine);
			}
		} else {
			error.append(("The transaction cannot be null or destination be null"));
		}

		if (error.length() > 0) {
			LOG.error("Bank id " + this.bankID + " error : " + error.toString());
			throw new TransactionException(error.toString());
		}
	}
}
