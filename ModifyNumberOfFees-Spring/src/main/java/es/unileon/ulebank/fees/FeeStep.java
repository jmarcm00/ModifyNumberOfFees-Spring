package es.unileon.ulebank.fees;

/**
 * @class FeeStep This class represents the boundaries of an interval and the
 * fee associated to that interval. LOWER boundary is included but HIGHER
 * boundary is NOT (a.k.a. [low, high)).
 * @author roobre
 */
public class FeeStep {

    /**
     * Lower boundary of the interval.
     */
    private final double low;

    /**
     * Higher boundary of the interval.
     */
    private final double high;

    /**
     * Fee to be applied as amount multiplicator, THUS ONE (a 2% fee is storead
     * as 0.02).
     */
    private final double fee;

    public FeeStep(double low, double high, double fee) throws InvalidStepException, InvalidFeeException {
        if (low > high) {
            throw new InvalidStepException(low, high);
        }
        
        if (low < 0) {
            throw new InvalidStepException();
        }
        
        if (fee < 0) {
            throw new InvalidFeeException();
        }
        
        this.low = low;
        this.high = high;
        this.fee = fee;
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return this.fee;
    }

    /**
     * @return the low
     */
    public double getLow() {
        return low;
    }

    /**
     * @return the high
     */
    public double getHigh() {
        return high;
    }

    /**
     * Checks if the interval contains a given value
     *
     * @param value Value to check
     * @return True if the interval contains a given value, false otherwise.
     */
    public boolean wraps(double value) {
        return (value >= this.low && value < this.high);
    }

    /**
     * Checks if two intervals overlap
     *
     * @param another Another interval to check.
     * @return True if the two intervals overlap, false otherwise.
     */
    public boolean collides(FeeStep another) {
        return this.high > another.low && this.low < another.high;
    }

    @Override
    public String toString() {
        return "[" + this.low + "," + this.high + ")";
    }
}
