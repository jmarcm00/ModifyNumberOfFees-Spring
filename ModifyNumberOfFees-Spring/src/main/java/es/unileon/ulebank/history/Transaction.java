/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.ulebank.history;

import es.unileon.ulebank.account.DetailedInformation;
import es.unileon.ulebank.handler.Handler;
import java.util.Date;

/**
 *
 * @author roobre
 */
public abstract class Transaction {

    private final Handler id;
    private final double amount;
    private final Date date;
    private Date effectiveDate;
    private final String subject;
    private DetailedInformation extraInformation;

    /**
     *
     * @param amount
     * @param date
     * @param subject
     */
    public Transaction(double amount, Date date, String subject) {
        this(amount, date, subject, new DetailedInformation(""));
    }

    /**
     *
     * @param amount
     * @param date
     * @param subject
     * @param info
     */
    public Transaction(double amount, Date date, String subject, DetailedInformation info) {
        this.id = TransactionHandlerProvider.getTransactionHandler();
        StringBuilder err = new StringBuilder();
        if (subject == null) {
            err.append("The subject cannot be null \n");
        } else {
            if (subject.length() == 0) {
                err.append("Transaction length cannot be 0 \n");
            }
        }

        if (id == null) {
            err.append(("The id cannot be null \n"));
        } else {
            if (id.toString().length() == 0) {
                err.append(("The id size cannot be 0 \n"));
            }
        }

        if (date == null) {
            err.append("The date cannot be null \n");
        }
        this.amount = amount;
        this.date = date;
        this.subject = subject;
        this.extraInformation = info;
        this.extraInformation.doFinal();
    }

    /**
     * @return the id
     */
    public Handler getId() {
        return id;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Transaction " + "id=" + id + ", amount=" + amount + ", date=" + date + ", effectiveDate=" + effectiveDate + ", subject=" + subject;
    }
}
