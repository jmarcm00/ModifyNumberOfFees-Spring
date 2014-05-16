/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.history.conditions;

import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.iterator.Condition;

/**
 *
 * @author runix
 * @param <T>
 */
public class ConditionTransactionBetweenTwoAmounts<T extends Transaction> implements Condition<T> {

    private double min;
    private double max;

    /**
     *
     * @param min
     * @param max
     * @throws WrongArgsException
     */
    public ConditionTransactionBetweenTwoAmounts(double min, double max) throws WrongArgsException {
        if (min > max) {
            throw new WrongArgsException("Low param is higher than high param\n");
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean test(T t) {
        double amount = t.getAmount();
        return (this.min <= amount && amount <= this.max);
    }
}
