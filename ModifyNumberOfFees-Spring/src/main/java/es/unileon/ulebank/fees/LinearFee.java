package es.unileon.ulebank.fees;

/**
 * Simple fee that applies a minimum plus a percentage of the given amount.
 * @author roobre
 */
public class LinearFee implements FeeStrategy {

    /**
     * Fee to be applied as amount multiplicator, THUS ONE (a 2% fee is storead
     * as 0.02).
     */
    private final double fee;

    /**
     * Minimum value which is always paid.
     */
    private final double minimum;

    public LinearFee(double fee, double minimum) throws InvalidFeeException {
        if (fee < 0 || minimum < 0) {
            throw new InvalidFeeException();
        }
        
        this.fee = fee;
        this.minimum = minimum;
    }

    @Override
    public double getFee(double value) {
        double total=this.fee * value;
        if(total <this.minimum) {
            total = this.minimum;
        }
        
        return total;
    
    }
}
