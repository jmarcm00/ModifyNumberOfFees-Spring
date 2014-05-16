package es.unileon.ulebank.assets.strategy.commission;

import es.unileon.ulebank.assets.strategy.commission.exception.CommissionException;

public class LockValueCommission implements StrategyCommission {

	/**
	 * Value of commision
	 */
	private final double VALUE;

	public LockValueCommission() {
		this.VALUE = 0;
	}

	public LockValueCommission(double value) throws CommissionException {
		if (value < 0)
			throw new CommissionException("Enter a positive value.");

		this.VALUE = value;
	}

	@Override
	public double calculateCommission() {

		return this.VALUE;
	}

}
