/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.office;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.Employee;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author runix
 */
public class Office {

    private static final Logger LOG = Logger.getLogger(Account.class.getName());

    /**
     *
     */
    public static final long MAX_ACCOUNT_NUMBER = 1000000000l - 1;
    /**
     *
     */
    private final List<Account> accounts;
    /**
     *
     */
    private final List<Client> clients;
    /**
     *
     */
    private final Handler id;
    /**
     *
     */
    private final Bank bank;
    /**
     *
     */
    private long nextAccountNumber;
    /**
     * The costs of the local of the office
     */
    private int localCost;
    /**
     * The costs of the light, water and gas of the office
     */
    private int utilitiesCost;
    /**
     * The expenses in the salaries of the employees
     */
    private int employeeCost;
    /**
     * The total expenses or costs of the office
     */
    private int totalExpenses;
    /**
     * The total income of the office
     */
    private int totalIncome;
    /**
     * The total balance of the office
     */
    private int balance;
    /**
     * The list of employees of this office
     */
    private ArrayList<Employee> employeeList;

    /**
     * The list of accounts of this office
     */
    /**
     *
     * @param id
     * @param bank
     */
    public Office(Handler id, Bank bank) {
        this.accounts = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.id = id;
        this.bank = bank;
        this.nextAccountNumber = 0;
        this.employeeList = new ArrayList<>();
    }

    /**
     *
     * @param account
     * @return
     */
    public boolean addAccount(Account account) {
        if (account != null) {
            int i = 0;
            boolean found = false;
            while (i < this.accounts.size() && !found) {
                if (accounts.get(i).getID().compareTo(account.getID()) == 0) {
                    found = true;
                }
                ++i;
            }
            if (!found) {
                return this.accounts.add(account);
            }
        }
        return false;
    }

    /**
     *
     * @param client
     * @return
     */
    public synchronized boolean addClient(Client client) {
        if (client != null) {
            int i = 0;
            boolean found = false;
            while (i < this.clients.size() && !found) {
                found = clients.get(i).getId().compareTo(client.getId()) == 0;
                ++i;
            }
            if (!found) {
                return this.clients.add(client);
            }
        }
        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    public synchronized boolean deleteClient(Handler id) {
        int i = 0;
        boolean found = false;
        while (i < this.clients.size() && !found) {
            if (clients.get(i).getId().compareTo(id) == 0) {
                //TODO perform account liquidation
                clients.remove(i);
                found = true;
            }
            ++i;
        }
        return found;
    }

    /**
     *
     * @param id
     * @return
     */
    public synchronized boolean deleteAccount(Handler id) {
        int i = 0;
        boolean found = false;
        while (i < this.accounts.size() && !found) {
            if (accounts.get(i).getID().compareTo(id) == 0) {
                accounts.remove(i);
                found = true;
            }
            ++i;
        }
        return found;
    }

    /**
     *
     * @return
     */
    public List<Client> getClients() {
        return new ArrayList<>(this.clients);
    }

    /**
     *
     * @return
     */
    public Handler getIdOffice() {
        return this.id;
    }

    /**
     *
     * @param t
     * @param destine
     * @throws TransactionException
     * @throws MalformedHandlerException
     */
    public void doTransaction(Transaction t, Handler destine) throws TransactionException, MalformedHandlerException {
        boolean finish = false;
        StringBuilder error = new StringBuilder();
        if (t != null && destine != null) {
            AccountHandler handler = new AccountHandler(destine);
            if (handler.getBankHandler().compareTo(this.bank.getID()) == 0 && handler.getOfficeHandler().compareTo(this.id) == 0) {
                for (int i = 0; i < accounts.size() && !finish; i++) {
                    if (accounts.get(i).getID().compareTo(destine) == 0) {
                        accounts.get(i).doTransaction(t);
                        finish = true;
                    }
                }
            } else {
                this.bank.doTransaction(t, destine);
            }
        } else {
            error.append(("The transaction cannot be null or destination be null"));
        }

        if (error.length() > 0) {
            LOG.error("Office id " + this.id + " error : " + error.toString());
            throw new TransactionException(error.toString());
        }
    }

    /**
     *
     * @return
     */
    public synchronized String getNewAccountNumber() {
        String accountNumber;
        if (this.nextAccountNumber == MAX_ACCOUNT_NUMBER) {
            accountNumber = "";
        } else {
            accountNumber = String.format("%010d", this.nextAccountNumber++);
        }
        return accountNumber;
    }

    /**
     *
     * @return
     */
    public List<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * Returns the expenses of the office
     */
    public int getExpenses() {
        return totalExpenses;
    }

    /**
     * Sets the total expenses of the office
     */
    public void setExpenses(int localCost, int utilitiesCost, int employeeCost) {

        this.localCost = localCost;
        this.utilitiesCost = utilitiesCost;
        this.employeeCost = employeeCost;

        this.totalExpenses = this.localCost + this.utilitiesCost
                + this.employeeCost;
    }

    /**
     * Returns the income of the office
     */
    public int getTotalIncome() {
        return totalIncome;
    }

    /**
     * Sets the total income of the office
     */
    public void setTotalIncome(int totalIncome) {
        // Addition of the types of incomes.
        this.totalIncome = totalIncome;
    }

    /**
     * Returns the balance of the office
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the total balance of the office
     */
    public void setBalance() {
        this.balance = this.totalIncome - this.totalExpenses;
    }

    /**
     * Returns a copy of the list of employees of the office
     */
    public ArrayList<Employee> getEmployeeList() {
        return new ArrayList<Employee>(employeeList);
    }

    /**
     * Sets the list of employees of the office
     */
    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * Returns the list of accounts of the office
     */
    public List<Account> getAccountList() {
        return this.accounts;
    }

    /**
     * Adds an employee to the list of employees
     */
    public boolean addEmployee(Employee employee) {
        return employeeList.add(employee);
    }

    /**
     * Deletes an employee to the list of employees
     *
     * @param employee
     * @return
     */
    public boolean deleteEmployee(Employee employee) {
        return employeeList.remove(employee);
    }
}
