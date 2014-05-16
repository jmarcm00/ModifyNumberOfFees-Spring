package es.unileon.ulebank.fees;

public class LoanCommission implements FeeStrategy {
	
	private double commission;
	private boolean isPercent;
	
	/**
	 * 
	 * @param value of the commission, it can be an interest or a quantity of money
	 * @param isPercent true if is an interest
	 * @throws InvalidFeeException if it is malformed
	 */
	public LoanCommission(double commission, boolean isPercent) throws InvalidFeeException {
		if(isPercent){
			this.isPercent = isPercent;
			
			if(commission < 0 && commission > 1 ){
				throw new InvalidFeeException();
			}
			
			this.commission = commission;
		}else {
			this.isPercent = isPercent;
			this.commission = commission;
		}
	}
	
	
	@Override
	public double getFee(double value) {
		if(this.isPercent){
			return this.commission * value;
		}
		
		return this.commission + value;
	}

}
