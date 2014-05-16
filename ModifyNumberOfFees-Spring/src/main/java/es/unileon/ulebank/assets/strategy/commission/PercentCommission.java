package es.unileon.ulebank.assets.strategy.commission;

import es.unileon.ulebank.assets.strategy.commission.exception.CommissionException;

public class PercentCommission implements StrategyCommission {

	/**
	 * Value of commision
	 */
	private final double VALUE;

	public PercentCommission() {
		this.VALUE = 0;
	}

	public PercentCommission(double value) throws CommissionException {
		if (value > 100 || value < 0)
			throw new CommissionException("Enter a value over 0 or 100.");

		this.VALUE = value;
	}

	@Override
	public double calculateCommission() {

		return this.VALUE;
	}
}
