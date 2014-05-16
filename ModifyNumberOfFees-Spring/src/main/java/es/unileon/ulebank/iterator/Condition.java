/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.iterator;

/**
 * This class represents a condition for the ConditionalIterator and determine
 * if a class(T) can be added or not.
 *
 * @author runix
 * @param <T>
 */
public interface Condition<T> {

    /**
     *
     * @param t (Object to test)
     *
     * @return true if is valid, otherwise false
     */
    boolean test(T t);

}
