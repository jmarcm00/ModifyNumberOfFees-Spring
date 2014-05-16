package es.unileon.ulebank.assets;

//cambio para corregir enlace
import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.client.Client;

public class ClientProducts {
	/**
	 * Client which need a loan
	 */
	private Client client;
	/**
	 * Arraylist for store the different financial products
	 */
	private ArrayList<FinancialProduct> financialProducts;

	public ClientProducts() {
		this.financialProducts = new ArrayList<FinancialProduct>();
	}

	/**
	 * Return a boolean result if the FinancialProduct is created or not
	 * 
	 * @param product
	 * @return
	 */
	public boolean add(FinancialProduct product) {
		return this.financialProducts.add(product);
	}

	/**
	 * Return a boolean result if the financial product has been removed
	 * 
	 * @param product
	 * @return
	 */
	public boolean remove(FinancialProduct product) {
		return this.financialProducts.remove(product);
	}

	/**
	 * Return the financial product that match with the index param
	 * 
	 * @param index
	 * @return
	 */
	public FinancialProduct getFinancialProduct(int index) {
		return this.financialProducts.get(index);
	}

	/**
	 * Method that allows to search any financial product created. If the
	 * financial product is found the method return the product, but if the
	 * product is not found the method returns null
	 * 
	 * @param handler
	 * @return
	 */
	public FinancialProduct search(Handler handler) {
		Iterator<FinancialProduct> iterator = this.financialProducts.iterator();
		boolean found = false;

		FinancialProduct product = null;

		while (iterator.hasNext() && !found) {
			product = iterator.next();
			if (handler.compareTo(product.getIdFinancialProduct()) == 0) {
				found = true;
			}
		}

		return product;
	}
}
