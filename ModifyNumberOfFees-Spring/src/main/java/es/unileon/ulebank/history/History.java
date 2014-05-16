package es.unileon.ulebank.history;

import es.unileon.ulebank.handler.Handler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author roobre
 * @param <T>
 */
public class History<T extends Transaction> {

    private final Collection<T> transactions;

    /**
     *
     */
    public History() {
        this.transactions = new ArrayList();
    }

    /**
     *
     * @param transaction
     * @return
     */
    public boolean add(T transaction) {
        boolean found = false;
        Iterator<T> it = this.transactions.iterator();
        while (it.hasNext() && !found) {
            T t = it.next();
            if (t.getId().compareTo(transaction.getId()) == 0) {
                found = true;
            }
        }
        if (!found) {
            return this.transactions.add(transaction);
        }
        return false;

    }

    /**
     *
     * @param args
     * @return
     */
    public Iterator<T> getIterator() {
        return this.transactions.iterator();
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean remove(Handler id) {
        boolean found = false;
        Iterator<T> it = this.transactions.iterator();
        while (it.hasNext() && !found) {
            T t = it.next();
            if (t.getId().compareTo(id) == 0) {
                it.remove();
                found = true;
            }
        }
        return found;
    }
}
