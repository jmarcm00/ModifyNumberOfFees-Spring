package es.unileon.ulebank.command;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.office.Office;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paula
 */
public class CreateAccountCommand implements Command {

	private final Office office;
	private final Bank bank;
	private final Handler commandID;
	private final Date effectiveDate;
	private Account account;

	/**
	 *
	 * @param office
	 * @param bank
	 * @param effectiveDate
	 * @param commandId
	 */
	public CreateAccountCommand(Office office, Bank bank, Date effectiveDate,
			Handler commandId) {
		this.bank = bank;
		this.office = office;
		this.effectiveDate = effectiveDate;
		this.commandID = commandId;
	}

	/**
     *
     */
	@Override
	public void execute() {
		try {
			this.account = new Account(this.office, this.bank,
					this.office.getNewAccountNumber());
			this.office.addAccount(account);
		} catch (MalformedHandlerException ex) {
			Logger.getLogger(CreateAccountCommand.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Handler getID() {
		return this.commandID;
	}

	/**
     *
     */
	@Override
	public void undo() {
		if (this.account != null) {
			this.office.deleteAccount(this.account.getID());
		}
	}

	/**
     *
     */
	@Override
	public void redo() {
		if (this.account != null) {
			this.office.addAccount(this.account);
		}
	}

}
