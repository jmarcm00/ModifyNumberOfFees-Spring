package es.unileon.ulebank.assets.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.strategy.commission.StrategyCommission;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.transacionManager.TransactionManager;


public class LoanTests {

	private Office office;
	private Bank bank;
	private TransactionManager manager;
    private String accountNumber = "0000000000";

    @Before
    public void setUp() throws Exception {
    	this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
		Account account = new Account(office, bank, accountNumber);

    }

    @Test
    public void testSetAndGetDescription() {
        /*String testDescription = "aDescription";
        assertNull(card.getDescription());
        card.setDescription(testDescription);
        assertEquals(testDescription, card.getDescription());*/
    }

    @Test
    public void testSetAndGetPrice() {
       /* double testPrice = 100.00;
        assertEquals(0, 0, 0);    
        card.setPrice(testPrice);
        assertEquals(testPrice, card.getPrice(), 0);*/
    }

}