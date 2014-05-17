package es.unileon.ulebank.assets.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.handler.FinancialProductHandler;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.service.SimpleFeeManager;
import es.unileon.ulebank.transacionManager.TransactionManager;


public class SimpleFeeManagerTests {

	private SimpleFeeManager productManager;
    
	private Office office;
	private Bank bank;
	private TransactionManager manager;
	private String accountNumber = "0000000000";
	private Account account;
	private FinancialProductHandler fPHandler;
	private Loan loan;
    
    @Before
    public void setUp() throws Exception {
        productManager = new SimpleFeeManager();
        this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
		fPHandler = new FinancialProductHandler("LN", "ES");
		account = new Account(office, bank, accountNumber);

		this.loan = new Loan(fPHandler, 3000, 0.05, PaymentPeriod.MONTHLY, 30, account);
		this.productManager.setFees(loan);
    }

    @Test
    public void testGetProductsWithNoProducts() {
        productManager = new SimpleFeeManager();
        assertNull(productManager.getFees());
    }

    @Test
    public void testGetCard() {
        assertNotNull(productManager.getFees());        
        assertEquals(productManager.getFees(), this.loan);
    }

    @Test(expected = NullPointerException.class)
    public void testChangeNumberOfFeesWithNullLoan() {
    	productManager = new SimpleFeeManager();
    	productManager.setNewNumberOfFees(18);
    }

    @Test
    public void testChangeNumberOfFeesWithIncorrectLimits() {
    	this.productManager.setNewNumberOfFees(1);
    	assertEquals(this.productManager.getFees().getAmortizationTime(), 1);
    	this.productManager.setNewNumberOfFees(90);
    	assertEquals(this.productManager.getFees().getAmortizationTime(), 90);
    }
    
    @Test
    public void testChangeNumberOfFeesWithCorrectLimits() {
    	this.productManager.setNewNumberOfFees(24);
    	assertEquals(this.loan.getAmortizationTime(), 24);
    }
   
}