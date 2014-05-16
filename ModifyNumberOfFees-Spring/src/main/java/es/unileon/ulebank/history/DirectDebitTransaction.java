/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.history;

import es.unileon.ulebank.account.DetailedInformation;
import java.util.Date;

/**
 *
 * @author runix
 */
public class DirectDebitTransaction extends Transaction {

    public DirectDebitTransaction(double amount, Date date, String subject) {
        super(amount, date, subject);
    }

    public DirectDebitTransaction(double amount, Date date, String subject, DetailedInformation info) {
        super(amount, date, subject, info);
    }

}
