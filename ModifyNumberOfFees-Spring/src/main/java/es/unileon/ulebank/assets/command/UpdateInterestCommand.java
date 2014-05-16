package es.unileon.ulebank.assets.command;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.Handler;

public class UpdateInterestCommand implements Command{
	private Handler idCommand;
	
	private Loan loan;
	
	public UpdateInterestCommand(Loan loan,Handler idCommand){
		this.idCommand=idCommand;
		this.loan=loan;
	}
	
	@Override
	public void execute() {
		this.loan.update();
		
		
	}

	@Override
	public void undo() {
		
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Handler getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
