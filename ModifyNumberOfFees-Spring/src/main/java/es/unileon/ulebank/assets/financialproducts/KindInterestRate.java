package es.unileon.ulebank.assets.financialproducts;

/**
 * Enum Class for the different types of interest
 * 
 * @author CarlitosMayo
 *
 */

public enum KindInterestRate {
	Euribor("euribor"), IRS("irs");
	String name;

	KindInterestRate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
