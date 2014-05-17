package es.unileon.ulebank.assets.web;

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
import es.unileon.ulebank.service.FeeChanges;
import es.unileon.ulebank.service.SimpleFeeManager;
import es.unileon.ulebank.transacionManager.TransactionManager;
import es.unileon.ulebank.web.FeeChangeFormController;

public class ChangeNumFeesFormControllerTest {

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
	public void testFormBackingObject() throws Exception{		
		FeeChangeFormController controller = new FeeChangeFormController();		
		controller.setProductManager(this.productManager);	
		assertEquals(controller.formBackingObject(null).getNumberOfFees(),this.productManager.getFees().getAmortizationTime());
	}
	
	@Test
	public void testOnSubmit() throws Exception{		
		FeeChangeFormController controller = new FeeChangeFormController();		
		controller.setProductManager(this.productManager);	
		FeeChanges limit = new FeeChanges();
		limit.setNumberOfFees(50);
		
		//controller.onSubmit(limit, new BindingResult());
		//assertEquals(50,this.productManager.getFees().getAmortizationTime());
	}

}
