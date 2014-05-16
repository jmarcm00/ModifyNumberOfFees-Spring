package es.unileon.ulebank.history;

import es.unileon.ulebank.handler.Handler;

/**
*
* @author roobre
*/
public class TransactionHandler implements Handler {

    private final long id;
    private final String timestamp;

    /**
     *
     * @param id
     * @param timestamp
     */
    public TransactionHandler(long id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return timestamp + "." + Long.toString(id);
    }

    @Override
    public int compareTo(Handler another) {
        return this.toString().compareTo(another.toString());
    }

}