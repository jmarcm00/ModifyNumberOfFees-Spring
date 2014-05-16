package es.unileon.ulebank.assets.financialproducts;

import java.io.IOException;

import es.unileon.ulebank.assets.financialproducts.exceptions.IRSException;

public class IRS implements InterestRate {
	private double irs;
	private InterestRateConnection connection;

	public IRS() throws IRSException {
		StringBuffer exMessage = new StringBuffer();
		this.connection = new InterestRateConnection();
		try {
			this.connection.connection(KindInterestRate.IRS);
		} catch (IOException e) {
			exMessage.append(e.getMessage());
		}

		if (exMessage.length() > 0)
			throw new IRSException(exMessage.toString());

		this.irs = this.connection.getRate();
	}

	@Override
	public double getInterestRate() {
		return this.irs;
	}

}
