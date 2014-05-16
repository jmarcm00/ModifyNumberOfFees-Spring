/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This iterator allow to iterate for a list of elements and include those that
 * pass a set of conditions.
 *
 *
 * @author runix
 * @param <T>
 */
public abstract class ConditionalIterator<T> implements Iterator<T> {

    /**
     * Conditions for decide if a element is going to be include or not
     */
    private final List<Condition<T>> conditions;
    /**
     * The iterator for the complete collection
     */
    private final java.util.Iterator<T> elementsIterator;
    /**
     * The next element
     */
    private T next;

    /**
     * Create a new Iterator with the conditions for decide if a determinate
     * element is going to be added or not.
     *
     * @param conditions ( conditions for the elements )
     * @param elements ( elements for the iterator )
     */
    public ConditionalIterator(List<Condition<T>> conditions, Collection<T> elements) {
        this(conditions, elements.iterator());
    }

    /**
     * Create a new Iterator without conditions. All items of the list will be
     * included ( no conditions )
     *
     * @param elements ( elements for the iterator )
     */
    public ConditionalIterator(List<T> elements) {
        this(new ArrayList<Condition<T>>(), elements);
    }

    /**
     * Create a new Iterator with one condition.
     *
     * @param condition
     * @param elements ( elements for the iterator )
     */
    public ConditionalIterator(Condition<T> condition, List<T> elements) {
        this(condition, elements.iterator());
    }

    /**
     * Create a new Iterator with one condition.
     *
     * @param condition
     * @param iterator
     */
    public ConditionalIterator(Condition<T> condition, Iterator<T> iterator) {
        this.conditions = new ArrayList<>();
        this.conditions.add(condition);
        this.elementsIterator = iterator;
        this.calcNext();
    }

    /**
     * Create a new Iterator with one condition.
     *
     * @param conditions
     * @param iterator
     */
    public ConditionalIterator(List<Condition<T>> conditions, Iterator<T> iterator) {
        this.conditions = conditions;
        this.elementsIterator = iterator;
        this.calcNext();
    }

    /**
     * Check if there are more elements in the iterator.
     *
     * @return ( true if there are elements, false otherwise )
     */
    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    /**
     * Get the next element
     *
     * @return (next element)
     */
    @Override
    public T next() {
        T element = this.next;
        this.calcNext();
        return element;
    }

    /**
     * Calculate the next element.
     */
    private void calcNext() {
        this.next = null;
        boolean done = false;
        while (this.elementsIterator.hasNext() && !done) {
            T actual = this.elementsIterator.next();
            boolean valid = true;
            int i = 0;
            while (i < this.conditions.size() && valid) {
                valid = this.conditions.get(i++).test(actual);
            }
            if (valid) {
                this.next = actual;
                done = true;
            }
        }
    }

    /**
     * Not supported in this iterator.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
