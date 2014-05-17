package es.unileon.ulebank.assets.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.FinancialProductHandler;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.transacionManager.TransactionManager;


public class LoanTests {

	private Office office;
	private Bank bank;
	private TransactionManager manager;
    private String accountNumber = "0000000000";
    private Account account;
    private FinancialProductHandler fPHandler;
    private Loan loan;

    @Before
    public void setUp() throws Exception {
    	this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
		this.account = new Account(office, bank, accountNumber);
		this.fPHandler = new FinancialProductHandler("LN", "ES");
		this.loan = new Loan(fPHandler, 3000, 0.05, PaymentPeriod.MONTHLY, 30, account);
    }
    
    @Test(expected=NullPointerException.class)
    public void testLoanNull() {
    	loan = null;
    	loan.getId();
    }
    
    @Test
    public void testLoanNotNull() {
    	assertTrue(this.loan != null);
    }
    
    @Test
    public void testPaymentsNotNull() {
    	assertTrue(this.loan.calcPayments() != null);
    }
    
    @Test
    public void testSetAndGetAmortizationTime() {
        int testFees = 10;
        assertNotNull(loan.getAmortizationTime());
        loan.setAmortizationTime(testFees);
        assertEquals(testFees, loan.getAmortizationTime());
    }

}