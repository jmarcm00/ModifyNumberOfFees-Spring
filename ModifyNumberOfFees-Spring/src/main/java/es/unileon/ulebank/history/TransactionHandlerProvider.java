/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package es.unileon.ulebank.history;

import es.unileon.ulebank.handler.Handler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
*
* @author roobre
*/
public final class TransactionHandlerProvider {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static String lastTimestamp = "";
    private static long currentId = 0;
    
    /**
     *
     * @return
     */
    public static synchronized Handler getTransactionHandler() {
        String timestamp = format.format(new Date());
        if (timestamp.compareTo(TransactionHandlerProvider.lastTimestamp) != 0) {
            TransactionHandlerProvider.lastTimestamp = timestamp;
            TransactionHandlerProvider.currentId = 0;
        }

        return new TransactionHandler(++TransactionHandlerProvider.currentId, TransactionHandlerProvider.lastTimestamp);
    }

    private TransactionHandlerProvider() {
    }
}