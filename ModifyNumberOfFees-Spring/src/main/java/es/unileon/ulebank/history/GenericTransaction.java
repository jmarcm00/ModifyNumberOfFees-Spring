/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.history;

import es.unileon.ulebank.account.DetailedInformation;
import java.util.Date;

/**
 * Generic transaction
 *
 * @author runix
 */
public class GenericTransaction extends Transaction {

    /**
     * Create a new generic transaction
     *
     * @param amount ( Transaction amount )
     * @param date ( Transaction date )
     * @param subject ( Transaction subject )
     */
    public GenericTransaction(double amount, Date date, String subject) {
        super(amount, date, subject);
    }

    /**
     * Create a new generic transaction
     *
     * @param amount ( Transaction amount )
     * @param date ( Transaction date )
     * @param subject ( Transaction subject )
     */
    public GenericTransaction(double amount, Date date, String subject, DetailedInformation extraInfo) {
        super(amount, date, subject, extraInfo);
    }
}
