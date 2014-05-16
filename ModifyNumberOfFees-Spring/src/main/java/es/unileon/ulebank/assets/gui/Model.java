/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.ulebank.assets.gui;

import java.util.ArrayList;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.KindOfMethod;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.HandlerFinancialProduct;
import es.unileon.ulebank.assets.handler.LoanIdentificationNumberCode;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.assets.strategy.commission.PercentCommission;
import es.unileon.ulebank.assets.strategy.commission.StrategyCommission;
import es.unileon.ulebank.assets.strategy.commission.exception.CommissionException;
import es.unileon.ulebank.assets.strategy.loan.AmericanMethod;
import es.unileon.ulebank.assets.strategy.loan.FrenchMethod;
import es.unileon.ulebank.assets.strategy.loan.GermanMethod;
import es.unileon.ulebank.assets.strategy.loan.ItalianMethod;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.strategy.loan.StrategyLoan;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.transacionManager.TransactionManager;

/**
 * 
 * @author amdiaz8
 */
public class Model {
	private HandlerFinancialProduct handler;
	private Loan loan;
	private StrategyLoan strategyLoan;

	private StrategyCommission strategyCancelCommission;
	private StrategyCommission strategyStudyCommission;
	private StrategyCommission strategyModifyCommission;
	private StrategyCommission strategyOpenningCommission;

	private double cancelCommission;
	private double modifyCommission;
	private double openningCommission;
	private double studyCommission;

	private double money;
	private double interest;
	private PaymentPeriod paymentPeriod;
	private int time;

	private ArrayList<ScheduledPayment> payments;

	private static Model model = null;

	private Model() throws LINCMalformedException, CommissionException {
		this.cancelCommission = 0;
		this.openningCommission = 0;
		this.modifyCommission = 0;
		this.studyCommission = 0;

		this.money = 0;
		this.interest = 0;
		this.paymentPeriod = PaymentPeriod.MONTHLY;
		this.time = 0;

		this.payments = new ArrayList<>();

		this.handler = new HandlerFinancialProduct(
				new LoanIdentificationNumberCode("LN", "ES"));

		this.strategyCancelCommission = new PercentCommission(
				this.cancelCommission);
		this.strategyModifyCommission = new PercentCommission(
				this.modifyCommission);
		this.strategyOpenningCommission = new PercentCommission(
				this.openningCommission);
		this.strategyStudyCommission = new PercentCommission(
				this.studyCommission);

		try {
			TransactionManager manager = new TransactionManager();
			Bank bank = new Bank(manager, new GenericHandler("1234"));
			Office office = new Office(new GenericHandler("1234"), bank);
			Account commercialAccount = new Account(office, bank, "0000000000");
			GenericHandler authorizedHandler1 = new GenericHandler("Miguel");
			Client authorized1 = new Client(authorizedHandler1);
			commercialAccount.addAuthorized(authorized1);
			this.loan = new Loan(this.handler, this.money, this.interest,
					this.paymentPeriod, this.time, commercialAccount);
		} catch (LoanException e) {
		} catch (MalformedHandlerException e) {
		}

		this.strategyLoan = new FrenchMethod(this.loan);
		this.loan.setStrategy(this.strategyLoan);
	}

	public void createLoan(KindOfMethod method) throws CommissionException {

		try {

			TransactionManager manager = new TransactionManager();
			Bank bank = new Bank(manager, new GenericHandler("1234"));
			Office office = new Office(new GenericHandler("1234"), bank);
			Account commercialAccount = new Account(office, bank, "0000000000");
			GenericHandler authorizedHandler1 = new GenericHandler("Miguel");
			Client authorized1 = new Client(authorizedHandler1);
			commercialAccount.addAuthorized(authorized1);
			this.loan = new Loan(this.handler, this.money, this.interest,
					this.paymentPeriod, this.time, commercialAccount);
		} catch (LoanException e) {
		} catch (MalformedHandlerException e) {
		}

		setStrategyLoan(method);

		this.loan.setStrategy(this.strategyLoan);

		this.payments = this.strategyLoan.doCalculationOfPayments();
	}

	public void setStrategyLoan(KindOfMethod method) {
		switch (method) {
		case French:
			this.strategyLoan = new FrenchMethod(this.loan);
			break;
		case German:
			this.strategyLoan = new GermanMethod(this.loan);
			break;
		case Italian:
			this.strategyLoan = new ItalianMethod(this.loan);
			break;
		case American:
			this.strategyLoan = new AmericanMethod(this.loan,
					this.loan.getInterest());
			break;
		default:
			this.strategyLoan = new GermanMethod(this.loan);
			break;
		}
	}

	public HandlerFinancialProduct getHandler() {
		return handler;
	}

	public void setHandler(HandlerFinancialProduct handler) {
		this.handler = handler;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public StrategyLoan getStrategyLoan() {
		return strategyLoan;
	}

	public void setStrategyLoan(StrategyLoan strategyLoan) {
		this.strategyLoan = strategyLoan;
	}

	public double getCancelCommission() {
		return cancelCommission;
	}

	public void setCancelCommission(double cancelCommission) {
		this.cancelCommission = cancelCommission;
	}

	public double getModifyCommission() {
		return modifyCommission;
	}

	public void setModifyCommission(double modifyCommission) {
		this.modifyCommission = modifyCommission;
	}

	public double getOpenningCommission() {
		return openningCommission;
	}

	public void setOpenningCommission(double openningCommission) {
		this.openningCommission = openningCommission;
	}

	public double getStudyCommission() {
		return studyCommission;
	}

	public void setStudyCommission(double studyCommission) {
		this.studyCommission = studyCommission;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public PaymentPeriod getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(PaymentPeriod paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ArrayList<ScheduledPayment> getPayments() {
		return payments;
	}

	public void setPayments(ArrayList<ScheduledPayment> payments) {
		this.payments = payments;
	}

	public StrategyCommission getStrategyCancelCommission() {
		return strategyCancelCommission;
	}

	public void setStrategyCancelCommission(
			PercentCommission strategyCancelCommission) {
		this.strategyCancelCommission = strategyCancelCommission;
	}

	public StrategyCommission getStrategyStudyCommission() {
		return strategyStudyCommission;
	}

	public void setStrategyStudyCommission(
			PercentCommission strategyStudyCommission) {
		this.strategyStudyCommission = strategyStudyCommission;
	}

	public StrategyCommission getStrategyModifyCommission() {
		return strategyModifyCommission;
	}

	public void setStrategyModifyCommission(
			PercentCommission strategyModifyCommission) {
		this.strategyModifyCommission = strategyModifyCommission;
	}

	public StrategyCommission getStrategyOpenningCommission() {
		return strategyOpenningCommission;
	}

	public void setStrategyOpenningCommission(
			PercentCommission strategyOpenningCommission) {
		this.strategyOpenningCommission = strategyOpenningCommission;
	}

	public static Model getInstance() throws LINCMalformedException,
			CommissionException {
		if (model == null) {
			model = new Model();
		}

		return model;
	}

}
