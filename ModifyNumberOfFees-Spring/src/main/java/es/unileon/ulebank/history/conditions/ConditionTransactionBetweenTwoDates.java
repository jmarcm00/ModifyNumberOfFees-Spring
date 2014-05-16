/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.history.conditions;

import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.iterator.Condition;
import java.util.Date;

/**
 *
 * @author runix
 * @param <T>
 */
public class ConditionTransactionBetweenTwoDates<T extends Transaction> implements Condition<T> {

    private final long timestampMin;
    private final long timestampMax;

    /**
     *
     * @param min
     * @param max
     * @throws WrongArgsException
     */
    public ConditionTransactionBetweenTwoDates(Date min, Date max) throws WrongArgsException {
        this.timestampMin = min.getTime();
        this.timestampMax = max.getTime();
        if (this.timestampMin > this.timestampMax) {
            throw new WrongArgsException("Less date is higher than the high");
        }
    }

    @Override
    public boolean test(T t) {
        final long timestamp = t.getEffectiveDate().getTime();
        return (this.timestampMin <= timestamp && timestamp <= this.timestampMax);
    }

}
