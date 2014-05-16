package es.unileon.ulebank.assets.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.service.SimpleFeeManager;


public class SimpleFeeManagerTests {

    private SimpleFeeManager productManager;
    
   // private List<CreditCard> cards;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);

    private static int POSITIVE_PRICE_INCREASE = 10; 
    
    @Before
    public void setUp() throws Exception {
        productManager = new SimpleFeeManager();
        //cards = new ArrayList<CreditCard>();
        
        // stub up a list of cards
       /* CreditCard card = new CreditCard(null, null, 0, 0, 0, 0, null, null, null);
        card.setDescription("Chair");
        card.setPrice(CHAIR_PRICE);
        cards.add(card);
        
        card = new CreditCard(null, null, 0, 0, 0, 0, null, null, null);
        card.setDescription("Table");
        card.setPrice(TABLE_PRICE);
        cards.add(card);
        
        productManager.setProducts(cards);*/

    }

    @Test
    public void testGetProductsWithNoProducts() {
        productManager = new SimpleFeeManager();
        assertNull(productManager.getFees());
    }

    @Test
    public void testGetProducts() {
        /*List<CreditCard> cards = productManager.getProducts();
        assertNotNull(cards);        
        assertEquals(PRODUCT_COUNT, productManager.getProducts().size());
    
        CreditCard card = cards.get(0);
        assertEquals(CHAIR_DESCRIPTION, card.getDescription());
        assertEquals(CHAIR_PRICE, card.getPrice());
        
        card = cards.get(1);
        assertEquals(TABLE_DESCRIPTION, card.getDescription());
        assertEquals(TABLE_PRICE, card.getPrice());     */ 
    }

    @Test
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productManager = new SimpleFeeManager();
           // productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
            fail("Products list is null.");
        }
    }

    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productManager = new SimpleFeeManager();
            //productManager.setProducts(new ArrayList<CreditCard>());
            //productManager.changeBuyLimits(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
            fail("Products list is empty.");
        }           
    }
    
    @Test
    public void testIncreasePriceWithPositivePercentage() {
        /*productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        List<CreditCard> cards = productManager.getProducts();      
        CreditCard card = cards.get(0);
        assertEquals(expectedChairPriceWithIncrease, card.getPrice(), 0);
        
        card = cards.get(1);      
        assertEquals(expectedTablePriceWithIncrease, card.getPrice(), 0);   */    
    }
}