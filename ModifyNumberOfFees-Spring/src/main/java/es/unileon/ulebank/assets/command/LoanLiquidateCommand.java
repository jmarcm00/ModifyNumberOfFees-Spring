package es.unileon.ulebank.assets.command;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.Handler;

public class LoanLiquidateCommand implements Command {
	
	private Handler idCommand;
	private double moneyToLiquidate;
	private Loan loan;
	private double initialDebt;
	
	public LoanLiquidateCommand(Handler idCommand, Loan loan, double moneyToLiquidate) {
		this.idCommand = idCommand;
		this.loan = loan;
		this.moneyToLiquidate = moneyToLiquidate;
		this.initialDebt = this.loan.getDebt();
	}
	
	@Override
	public void execute() {
		try {
			this.loan.liquidate(this.moneyToLiquidate);
		} catch (LoanException e) {
		}
	}

	@Override
	public void undo() {
		this.loan.setDebt(this.initialDebt);
	}

	@Override
	public void redo() {
		try {
			this.loan.liquidate(this.moneyToLiquidate);
		} catch (LoanException e) {
		}
	}

	@Override
	public Handler getId() {
		return this.idCommand;
	}

}
