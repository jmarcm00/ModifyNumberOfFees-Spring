package es.unileon.ulebank.assets;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.command.UpdateInterestCommand;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.financialproducts.InterestRate;
import es.unileon.ulebank.assets.handler.GenericLoanHandler;
import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.history.LoanHistory;
import es.unileon.ulebank.assets.iterator.LoanIterator;
import es.unileon.ulebank.assets.iterator.LoanIteratorDates;
import es.unileon.ulebank.assets.strategy.commission.*;
import es.unileon.ulebank.assets.strategy.loan.FrenchMethod;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.strategy.loan.StrategyLoan;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.taskList.Task;
import es.unileon.ulebank.taskList.TaskList;
import es.unileon.ulebank.time.Time;

// TODO PREGUNTAR A CAMINO COMO ACTUALIZAR DEBT CUANDO PASIVOS REALIZA EL PAGO DE LA CUOTA

public class Loan implements FinancialProduct {
	/**
	 * Type of time period used for the effective interest
	 */
	private PaymentPeriod paymentPeriod;

	/**
	 * Interest applicated to the loan
	 */
	private double interest;
	
	/**
	 * Bankinterest
	 */
	private double bankInterest;

	/**
	 * Number of fees to resolve the loan
	 */
	private int amortizationTime;

	/**
	 * Amount of money required for the user
	 */
	private double initialCapital;

	/**
	 * Amount of money that the user have not payed yet
	 */
	private double debt;

	/**
	 * Commission applicated in the case of the client do not pay the fee in the
	 * correct time
	 */
	private double delayedPaymentInterest;

	/**
	 * Unique identificator for the loan
	 */
	private Handler idLoan;

	/**
	 *Strategy used for calculate the payments
	 */
	private StrategyLoan strategy;

	/**
	 * Money that you have already payed
	 */
	private double amortized;

	/**
	 * Arraylist where you store the fees with all data
	 */
	private ArrayList<ScheduledPayment> payments;

	/**
	 * Commisions that you have in the contract
	 */
	/**
	 * Commission that you applied if the owner cancel the loan
	 */
	private StrategyCommission cancelCommission;
	/**
	 * Commission applied when the bank studied the account and other things
	 */
	private StrategyCommission studyCommission;
	/**
	 * Commission applied for open a loan
	 */
	private StrategyCommission openningCommission;
	/**
	 * Commission applied if the owner decides modify the loan contract during the loan
	 */
	private StrategyCommission modifyCommission;
	// TODO Add the amortizedCommission

	/**
	 * Account where we must charge the different payments of the loan
	 */
	private Account account;

	/**
	 * List where we store the payments for every loans
	 */

	private LoanHistory loanHistory;

	/*
	 * internal index used to have the possibility to change the arraylist of
	 * the payments
	 */
	private int arrayListIndex;

	/*
	 * Fixed fee that you have to pay every month
	 */
	private double periodFee;
	/**
	 * The interest rate of loan
	 */
	
	private InterestRate interestRate;
	/**
	 * Indicate when is the period of recalculate the period
	 */

	private PaymentPeriod recalcOfInterest;
	/**
	 * Indicate when the loan is created
	 */
	private Date creationalDate;
	 /**	
	  * This is the task list
	  */
	private TaskList taskList;

	/**
	 * Constructor of Loan class
	 * 
	 * @param idFinancialProduct
	 *            Unique identificator for the loan
	 * @param initialCapital
	 *            double Amount of money requested for the client
	 * @param interest
	 *            double Interest applicated to the loan
	 * @param paymentPeriod
	 *            Type of time period used for the effective interest
	 * @param month
	 *            int Number of months that the loan is open
	 * @param commissions
	 *            Type of commissions applicated to the loan
	 * 
	 */
	public Loan(Handler idLoan, double initialCapital, double interest,
			PaymentPeriod paymentPeriod, int amortizationTime, Account account)
			throws LoanException {
		StringBuffer exceptionMessage = new StringBuffer();

		this.loanHistory = new LoanHistory();
		this.cancelCommission = new PercentCommission();
		this.studyCommission = new PercentCommission();
		this.cancelCommission = new PercentCommission();
		this.modifyCommission = new PercentCommission();
		this.openningCommission = new PercentCommission();
		
		this.creationalDate=new Date();
		
		this.idLoan = idLoan;

		this.debt = initialCapital;

		if (interest >= 0 && interest <= 1) {
			this.interest = interest;
			this.bankInterest=interest;
		} else {
			exceptionMessage
					.append("The interest value must be a value between 0 and 1\n");
		}

		// if
		// (!this.openningCommission.getClass().equals(PercentCommission.class))
		// {
		// this.debt += openningCommission.calculateCommission();
		// } else {
		// this.debt += this.debt * (openningCommission.calculateCommission());
		// }
		//
		// if (!this.studyCommission.getClass().equals(PercentCommission.class))
		// {
		// this.debt += studyCommission.calculateCommission();
		// } else {
		// this.debt += this.debt * (studyCommission.calculateCommission());
		// }

		this.paymentPeriod = paymentPeriod;
		this.amortizationTime = amortizationTime;
		this.payments = new ArrayList<ScheduledPayment>();
		this.initialCapital = this.debt;
		this.strategy = new FrenchMethod(this);
		this.account = account;
		this.payments = this.strategy.doCalculationOfPayments();
		this.loanHistory.addAllPayments(this.payments);
		this.account = account;
		this.arrayListIndex = 0;
		this.taskList=new TaskList();
		if (exceptionMessage.length() > 1)
			throw new LoanException(exceptionMessage.toString());

	}
	/**
	 * This is the constructor used for variable loans
	 * 
	 * @param idFinancialProduct
	 *            Unique identificator for the loan
	 * @param initialCapital
	 *            double Amount of money requested for the client
	 * @param interest
	 *            double Interest applicated to the loan
	 * @param paymentPeriod
	 *            Type of time period used for the effective interest
	 * @param month
	 *            int Number of months that the loan is open
	 * @param commissions
	 *            Type of commissions applicated to the loan
	 * @param interestRate
	 * 			  Is the interestRateof Test variable loan
	 * 
	 */
	//TODO 
	public Loan(Handler idLoan, double initialCapital, double interest,
			PaymentPeriod paymentPeriod, int amortizationTime, Account account
			,InterestRate interestRate,PaymentPeriod recalcOfInterset)
			throws LoanException {
		StringBuffer exceptionMessage = new StringBuffer();
		this.interestRate=interestRate;
		this.recalcOfInterest=recalcOfInterset;
		
		this.creationalDate.setTime(Time.getInstance().getTime());
		
		this.loanHistory = new LoanHistory();
		this.cancelCommission = new PercentCommission();
		this.studyCommission = new PercentCommission();
		this.cancelCommission = new PercentCommission();
		this.modifyCommission = new PercentCommission();
		this.openningCommission = new PercentCommission();

		this.idLoan = idLoan;

		this.debt = initialCapital;

		if (interest >= 0 && interest <= 1) {
			this.bankInterest=interest;
			this.interest = this.bankInterest+this.interestRate.getInterestRate();
		} else {
			exceptionMessage
					.append("The interest value must be a value between 0 and 1\n");
		}
		
		this.paymentPeriod = paymentPeriod;
		this.amortizationTime = amortizationTime;
		this.payments = new ArrayList<ScheduledPayment>();
		this.initialCapital = this.debt;
		this.strategy = new FrenchMethod(this);
		this.account = account;
		this.payments = this.strategy.doCalculationOfPayments();
		this.loanHistory.addAllPayments(this.payments);
		this.account = account;
		this.arrayListIndex = 0;
		this.taskList=new TaskList();
	//TODO Ask integration about the id of the command
		Command command;
		try {
			command = (Command) new UpdateInterestCommand(this, new GenericLoanHandler("aaa"));
		
		Task taskUpdateInterest=new Task(forwardDate(creationalDate, recalcOfInterset), command);
		this.taskList.addTask(taskUpdateInterest);
		} catch (MalformedHandlerException e) {
			e.printStackTrace();
		}
		
		if (exceptionMessage.length() > 1)
			throw new LoanException(exceptionMessage.toString());
		
	}
	/**
	 * This method can forward the actual date
	 * @param date
	 * @param paymentPeriod
	 * @return The new simulated date
	 */
	/*TODO puede ser cambiado a otra clase utils*/ 
	public Date forwardDate(Date date,PaymentPeriod paymentPeriod){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);//reset the parameter 
		
		int month=calendar.get(Calendar.MONTH)+paymentPeriod.getPeriod();
		int year=calendar.get(Calendar.YEAR);
		int day=calendar.get(Calendar.DATE);
		if(month > 12){
			++year;
		}
		month=month%12+1;
		
		calendar.set(year, month, day);

		
		return calendar.getTime();
	}

	/**
	 * This method returns an ArrayList with all fees of the loan
	 * 
	 * @return payments The arraylist with fees
	 */
	public ArrayList<ScheduledPayment> calcPayments() {
		this.payments = this.strategy.doCalculationOfPayments();
		return payments;
	}

	/**
	 * This method allows to know what is the price that one person must pay if
	 * he decide cancel the loan. This amount of money is the total amount of
	 * money for cancel the loan
	 * 
	 * @return double with amount of money to pay
	 * @throws LoanException
	 */
	public double cancelLoan() throws LoanException {
		StringBuffer msgException = new StringBuffer();
		double feeCancel = 0;
		if (this.cancelCommission.getClass().equals(PercentCommission.class)) {
			feeCancel = this.cancelCommission.calculateCommission() * this.debt;
			feeCancel += this.debt;
		} else {
			feeCancel += this.cancelCommission.calculateCommission()
					+ this.debt;
		}

		// We carry out the transaction to discount the money from the account
		// of the customer
		try {
			if (!(this.account.getBalance() < this.debt)) {
				Transaction transactionCharge = new GenericTransaction(
						feeCancel, new Date(Time.getInstance().getTime()),
						"cancel loan");

				transactionCharge.setEffectiveDate(new Date(Time.getInstance().getTime()));
				this.account.doTransaction(transactionCharge);
			} else {
				msgException.append("not enough money");
			}
		} catch (TransactionException transactionException) {
			msgException.append("Transaction error.\n");
			msgException.append(transactionException.getMessage());
		}

		if (msgException.length() > 0) {
			throw new LoanException(msgException.toString());
		}

		// If the exception is not launched the payment is made and will zero
		// debt.
		this.debt = 0;

		return feeCancel;

	}

	/**
	 * Method that applies the delayed interest if some fee has not been payed
	 * in time
	 */
	public void delayedPayment() {
		boolean isPaid = isNotPaid();
		if (isPaid && this.debt > 0)
			this.debt = this.debt + this.debt * delayedPaymentInterest;

	}

	/**
	 * Method that is necesary when the interest change
	 */
	//TODO 
	@Override
	public void update() {
		this.interest=this.interestRate.getInterestRate()+bankInterest;
		this.payments = this.strategy.doCalculationOfPayments();
		this.loanHistory.addAllPayments(this.payments);
	}

	public double liquidate(double quantity) throws LoanException {
		StringBuffer exceptionMessage = new StringBuffer();
		double comission = 0;

		if (!(quantity < this.debt)) {
			exceptionMessage
					.append("The money to liquidate is more than the debt!");
		}

		if (exceptionMessage.length() > 0) {
			throw new LoanException(exceptionMessage.toString());
		}

		// We carry out the transaction to discount the money from the account
		// of the customer.
		try {
			if (!(this.account.getBalance() < this.debt)) {
				Transaction transactionCharge = new GenericTransaction(
						quantity, new Date(Time.getInstance().getTime()),
						"liquidate a quantity");
				transactionCharge.setEffectiveDate(new Date(Time.getInstance().getTime()));
				this.account.doTransaction(transactionCharge);
			} else {
				exceptionMessage.append("not enough money");
			}
		} catch (TransactionException transactionException) {
			exceptionMessage.append("Transaction error.\n");
			exceptionMessage.append(transactionException.getMessage());
		}

		// If the transaction is unsuccessful we launched the exception.
		if (exceptionMessage.length() > 0) {
			throw new LoanException(exceptionMessage.toString());
		}

		// Si la transaccion se realizo con exito descontamos el dinero de la
		// deuda
		if (!this.modifyCommission.getClass().equals(PercentCommission.class)) {
			comission = quantity * this.modifyCommission.calculateCommission();
			quantity -= comission;
			this.debt -= quantity;
			this.setAmortized(this.initialCapital - this.debt);
			update();
		} else {
			comission = this.modifyCommission.calculateCommission();
			quantity -= comission;
			this.debt -= quantity;
			this.setAmortized(this.initialCapital - this.debt);
			update();
		}

		return comission;
	}

	public PaymentPeriod getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(PaymentPeriod paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public double getInterest() {
		return this.interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getAmortizationTime() {
		return amortizationTime;
	}

	public void setAmortizationTime(int amortizationTime) {
		this.amortizationTime = amortizationTime;
	}

	public double getAmountOfMoney() {
		return initialCapital;
	}

	public void setAmountOfMoney(double amountOfMoney) {
		this.initialCapital = amountOfMoney;
	}

	public double getDebt() {
		return this.debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
		update();
	}

	public double getDelayedPaymentInterest() {
		return this.delayedPaymentInterest;
	}

	public void setDelayedPaymentInterest(double delayedPaymentInterest) {
		this.delayedPaymentInterest = delayedPaymentInterest;
	}

	public StrategyLoan getStrategy() {
		return this.strategy;
	}

	public void setStrategy(StrategyLoan strategy) {
		this.strategy = strategy;
		update();
	}

	public Handler getId() {
		return this.idLoan;
	}

	public void setId(Handler idLoan) {
		this.idLoan = idLoan;
	}

	public ArrayList<ScheduledPayment> getPayments() {
		return this.payments;
	}

	/**
	 * Method used to paying the fee if payment is not made
	 * 
	 * @param index
	 *            indicates the number of payments to be amortized
	 */
	@Deprecated
	public void paid(int index) { //Este metodo se borrara asiq no lo useis
		if (index >= 0 && index < payments.size()) {
			ScheduledPayment payment = payments.get(index);
			if (!payment.isPaid()) {
				this.debt -= payment.getAmortization();
				payment.setPaid(true);
			}
		}
	}

	/**
	 * Method used to pay the payment by an id handler
	 * 
	 * @param handlerId
	 *            is the handler of the payment
	 * @throws LoanException 
	 */
	public void paid(Handler handlerId) throws LoanException {
		StringBuffer exceptionMessage = new StringBuffer();
		
		//we look for the payment
		boolean found = false;
		ScheduledPayment payment = null;
		for (int i = 0; i < this.payments.size() && !found; i++) {
			payment = this.payments.get(i);
			if (payment.getId().compareTo(handlerId) == 0) {
				found = true;
			}
		}
		
		
		if (payment != null && !payment.isPaid()) {
			
			//we do the transaction
			try {
				Transaction transaction = new GenericTransaction(
						payment.getImportOfTerm(), new Date(
								Time.getInstance().getTime()), "payment");

				transaction.setEffectiveDate(new Date(Time.getInstance().getTime()));

				this.account.doTransaction(transaction);
				
			} catch (TransactionException e) {
				exceptionMessage.append("Transaction error.\n");
			}
			
			//if the transaction has not errors and was made successfully
			if(exceptionMessage.length() == 0){
				//we subtract the quantity to amortize of the debt
				this.debt -= payment.getAmortization();
				payment.setPaid(true);
			}else{
				throw new LoanException("The payment has not been made successfully.");
			}
		}
	}

	/**
	 * To String method that shows us the information about a loan
	 */

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append("ID: " + this.idLoan.toString() + "\n");
		output.append("Debt: " + this.debt + "\n");
		output.append("Payment period: " + this.paymentPeriod + "\n");
		if (!this.openningCommission.getClass().equals(PercentCommission.class))
			output.append("Comision for opening "
					+ this.openningCommission.calculateCommission() + "\n");
		else
			output.append("Comision for opening "
					+ this.openningCommission.calculateCommission() + "\n");

		return output.toString();
	}

	/**
	 * Method that returns true if any month has not been paid, false if all
	 * were paid
	 * 
	 * @return true if it has not paid any month
	 */
	public boolean isNotPaid() {
		boolean isNotPaid = false;

		for (int i = 0; i < this.payments.size() && !isNotPaid; i++) {
			ScheduledPayment payment = this.payments.get(i);
			if (!payment.isPaid()) {
				isNotPaid = true;
			}
		}

		return isNotPaid;
	}

	public double getAmortized() {
		return amortized;
	}

	public void setAmortized(double amortized) {
		this.amortized = amortized;
	}

	public LoanIteratorDates iterator(Date startDate, Date endDate) {
		return new LoanIteratorDates(this.payments, startDate, endDate);
	}

	public LoanIterator iterator() {
		return new LoanIterator(this.payments);
	}

	//TODO MAKE THE DOC IN ENGLISH OF THIS METHOD PLEASE. Not put your ideas
	public void makeNormalPayment(double amount) {
		// lanzo alguna excepcion o que?
		// pongo la condicion de que el pago se haga entre los meses indicados?
		if (amount == periodFee && payments.size() > 0
				&& arrayListIndex < payments.size()) {
			ScheduledPayment hesGonnaPay = this.payments.get(arrayListIndex);
			hesGonnaPay.setPaid(true);
			this.debt = debt - amount;
			// pongo la fecha de hoy, pero deberia dejar que se le pase por
			// parametro?
			hesGonnaPay.setPaymentDate(new Date());
			arrayListIndex++;
		}

	}
	//TODO MAKE THE DOC IN ENGLISH OF THIS METHOD PLEASE. Not put your ideas

	// metodo de pago de cantidades diferentes a la mensual calculada
	public void makeAbnormalPayment(double amount) {
		// excepciones
		// pongo la condicion de que el pago se haga entre los meses indicados?
		if (amount < this.debt && amount > 0) {
			ScheduledPayment hesGonnaPay = this.payments.get(arrayListIndex);

			double interest = 0;
			double amortized = 0;
			double totalLoan = this.debt;
			double totalCapital = this.debt;

			interest = amount * this.interest;
			amortized = amount - interest;
			if (totalLoan > amount) {
				totalLoan -= amount;
			} else {
				totalLoan = 0;
			}
			totalCapital = round(totalLoan, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);
			hesGonnaPay.setAmortization(amortized);
			hesGonnaPay.setInterests(interest);
			hesGonnaPay.setOutstandingCapital(totalCapital);
			// Cambiar
			hesGonnaPay.setPaymentDate(new Date());

			hesGonnaPay.setPaid(true);
			// hesGonnaPay.setOutstandingCapital(outstandingCapital);
			this.debt = debt - amount;
			hesGonnaPay.setImportOfTerm(amount);
		}

		// borro todos los elementos en adelante porque hay que recalcular
		int auxSize = this.payments.size();
		for (int auxInd = arrayListIndex + 1; auxInd < auxSize; auxInd++) {
			this.payments.remove(this.payments.get(this.payments.size() - 1));
		}
		// se recalcula todo
		this.strategy.doCalculationOfPayments();
		// actualizo el indice del arrayList
		++arrayListIndex;

	}

	/**
	 * Calculate the amount of money of one payment
	 * 
	 * @return fee Money to pay in one payment
	 */
	public double calculateMonthlyFee() {
		double fee = 0;
		double interesEf = this.calculateEffectiveInterestRate();
		int numFee = (this.getAmortizationTime() / this.calculatePayment());
		double fracc = ((Math.pow((1 + interesEf), numFee)) * interesEf)
				/ (Math.pow(1 + interesEf, numFee) - 1);
		fee = this.getAmountOfMoney() * fracc;
		return fee;
	}

	/**
	 * Method used to invert the payment period for calculated the total number
	 * of payments for repayment the loan
	 * 
	 * @return Integer with the value of number of payments in one year
	 */
	private int calculatePayment() {
		int num = this.getPaymentPeriod().getPeriod();
		if (num == 12)
			return 1;
		else if (num == 6)
			return 2;
		else if (num == 4)
			return 3;
		else if (num == 2)
			return 6;
		else if (num == 1)
			return 12;
		return 0;
	}

	/**
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 */
	public double calculateEffectiveInterestRate() {
		return Math.pow(1 + (this.interest / this.paymentPeriod.getPeriod()),
				this.paymentPeriod.getPeriod()) - 1;
	}

	/**
	 * Method used to round some numbers for the payments. This method allow us
	 * to be more exactly in the calcs
	 * 
	 * @param num
	 * @param factor
	 * @return num Number rounded
	 */

	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	public double getPeriodFee() {
		return periodFee;
	}

	public Account getLinkedAccount() {
		return this.account;
	}
	@Override
	public Handler getIdFinancialProduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
