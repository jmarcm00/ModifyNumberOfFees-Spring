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
public class ConditionSubject<T extends Transaction> implements Condition<T> {

    /**
     * Words to search in the subject
     */
    private final String word;

    private final boolean isValidSubject;

    /**
     * Create a new ConditionSubejct. The word as passed as parameters will be
     * searched in the subject. If the param isValidSubject is true this word
     * must be in the subject and if isValidSubject is false the words musn't be
     * in the subject.
     *
     * @param isValidSubject ( if the words are valid )
     *
     * @param word (words to search )
     */
    public ConditionSubject(boolean isValidSubject, String word) {
        this.word = word;
        this.isValidSubject = isValidSubject;
    }

    @Override
    public boolean test(T t) {
        return t.getSubject().contains(this.word) ^ !isValidSubject;
    }

}
