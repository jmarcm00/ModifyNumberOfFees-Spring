package es.unileon.ulebank.assets.support;

public enum PaymentPeriod {

	MONTHLY(1, 12), TWICEMONTHLY(2, 6), QUARTERLY(3, 4), BIANNUAL(6, 2), ANNUAL(
			12, 1);

	private final int time;
	private final int period;

	private PaymentPeriod(int time, int period) {
		this.time = time;
		this.period = period;
	}

	/**
	 * It returns the time between payments
	 * 
	 * @return time that is
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * It returns the period used to compute the interest rate equivalent
	 * 
	 * @return return the period of time
	 */
	public int getPeriod() {
		return this.period;
	}

}
