package es.unileon.ulebank.assets.command;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.Handler;

public class DelayedPaymentCommand implements Command {
	private Handler idCommand;
	private Loan loan;
	private double debt;

	public DelayedPaymentCommand(Handler idCom, Loan loan) {
		this.idCommand = idCom;
		this.loan = loan;
		this.debt = loan.getDebt();
	}

	@Override
	public void execute() {
		this.loan.delayedPayment();

	}

	@Override
	public void undo() {
		this.loan.setDebt(debt);

	}

	@Override
	public void redo() {
		this.loan.delayedPayment();

	}

	@Override
	public Handler getId() {
		return idCommand;
	}

}
