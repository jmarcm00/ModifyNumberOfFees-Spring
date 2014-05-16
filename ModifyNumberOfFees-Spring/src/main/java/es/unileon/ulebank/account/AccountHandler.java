/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.account;

import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author runix
 */
public class AccountHandler implements Handler {
	/**
	 * the number of digits ( account number )
	 */
	private static final int ACCOUNT_NUMBER_LENGTH = 0b1010;
	/**
	 * the number of digits ( office number )
	 */
	private static final int OFFICE_NUMBER_LENGTH = 0b100;
	/**
	 * the number of digits ( bank number )
	 */
	private static final int BANK_NUMBER_LENGTH = 0b100;
	/**
	 * number of fields
	 */
	private static final int NUMBER_OF_FIELDS = 0b100;

	/**
	 * Separator of the fields
	 */
	private final static String SEPARATOR = "-";

	private static Handler getField(Handler another, int number,
			String separator) throws MalformedHandlerException {

		String[] splitHandler = null;
		StringBuilder error = new StringBuilder();
		if (another != null && another.toString() != null && separator != null) {
			if (number >= 0 && number < NUMBER_OF_FIELDS) {
				// Check if the handler syntax is the same as AccountHandler
				// syntax and parse it
				if (another.toString().contains(separator)) {
					splitHandler = another.toString().split(separator);
					if (splitHandler.length != NUMBER_OF_FIELDS) {
						error.append("The handler fields (String) must be separated by \""
								+ separator + "\" and has 4 fields");
					}
				} else {
					// Check if there are letters and try to parse in raw format
					String raw = "";
					boolean foundLetter = false;
					for (int i = 0; i < another.toString().length()
							&& !foundLetter; i++) {
						if (Character.isDigit(another.toString().charAt(i))) {
							raw += another.toString().charAt(i);
						} else {
							foundLetter = true;
						}
					}
					if (!foundLetter) {
						if (raw.length() == (ACCOUNT_NUMBER_LENGTH
								+ BANK_NUMBER_LENGTH + OFFICE_NUMBER_LENGTH + 2)) {
							splitHandler = new String[NUMBER_OF_FIELDS];
							splitHandler[0] = raw.substring(0,
									BANK_NUMBER_LENGTH);
							splitHandler[1] = raw.substring(BANK_NUMBER_LENGTH,
									BANK_NUMBER_LENGTH + OFFICE_NUMBER_LENGTH);
							splitHandler[2] = raw.substring(BANK_NUMBER_LENGTH
									+ OFFICE_NUMBER_LENGTH, BANK_NUMBER_LENGTH
									+ OFFICE_NUMBER_LENGTH + 2);
							splitHandler[3] = raw.substring(BANK_NUMBER_LENGTH
									+ OFFICE_NUMBER_LENGTH + 2,
									BANK_NUMBER_LENGTH + OFFICE_NUMBER_LENGTH
											+ 2 + ACCOUNT_NUMBER_LENGTH);
						} else {
							error.append("Error, incorrect length");
						}
					} else {
						error.append("Error, the accountNumber cannot has letters, only "
								+ SEPARATOR
								+ " character is allowed splitting the fields\n");
					}
				}
			} else {
				error.append("Error, invalid number, the number must be less than "
						+ NUMBER_OF_FIELDS + " and more than " + 0 + " \n");
			}
		} else {
			error.append("Error, there are null fields or the toString method return a null String");
		}
		if (error.length() > 0) {
			throw new MalformedHandlerException(error.toString());
		}
		return new GenericHandler(splitHandler[number]);
	}

	/**
	 * Calculate control digits of the account
	 *
	 * @param office
	 *            ( office number in String format )
	 * @param bank
	 *            ( bank number in String format )
	 * @param accountNumber
	 *            ( accountNumber in String format)
	 * @return ( control digits of the account )
	 * @author runix
	 */
	private static String calculateDC(String office, String bank,
			String accountNumber) {
		return String.valueOf(calculateDigit("00" + bank.toString()
				+ office.toString()))
				+ String.valueOf(calculateDigit(accountNumber + ""));
	}

	/**
	 * Calculate the control digit. The length of the string must be 10
	 *
	 * @param number
	 *            ( The string with the numbers )
	 *
	 * @return ( the control digits )
	 * @author runix
	 */
	private static int calculateDigit(String number) {
		final int[] weights = new int[] { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
		final int length = number.length();
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += Integer.valueOf(number.charAt(i) + "") * weights[i];
		}
		int digit = 11 - sum % 11;
		if (digit == 11) {
			digit = 0;
		} else if (digit == 10) {
			digit = 1;
		}
		return digit;
	}

	/**
	 * The account number, the number of digits of this number is given by {
	 *
	 * @see ACCOUNT_NUMBER_LENGHT
	 */
	private final String accountNumber;

	/**
	 * The office identifier
	 */
	private final Handler officeHandler;
	/**
	 * The bank identifier
	 */
	private final Handler bankHandler;
	/**
	 * The control digits
	 */
	private final String dc;

	/**
	 * Create a new AccountHandler
	 *
	 * @param office
	 *            ( the office id )
	 * @param bank
	 *            ( the bank id )
	 * @param accountNumber
	 *            ( the account number )
	 * @author runix
	 * @throws MalformedHandlerException
	 *             ( If the account number, office handler, or bank handler
	 *             aren't correct )
	 */
	public AccountHandler(Handler office, Handler bank, String accountNumber)
			throws MalformedHandlerException {
		StringBuilder errors = new StringBuilder();
		Pattern numberPattern = Pattern.compile("^[0-9]*$");
		if (office != null && office.toString() != null && bank != null
				&& bank.toString() != null && accountNumber != null) {
			Matcher matcher = numberPattern.matcher(accountNumber);
			if (!matcher.find()) {
				errors.append("The accountNumber can only have numbers\n");
			}

			if (accountNumber.length() != ACCOUNT_NUMBER_LENGTH) {
				errors.append("The accountNumber length must be "
						+ ACCOUNT_NUMBER_LENGTH + "\n");
			}

			matcher = numberPattern.matcher(office.toString());
			if (!matcher.find()) {
				errors.append("The office id can only have numbers\n");
			}
			if (office.toString().length() != OFFICE_NUMBER_LENGTH) {
				errors.append("The office id length must be "
						+ OFFICE_NUMBER_LENGTH + " \n");
			}

			matcher = numberPattern.matcher(bank.toString());
			if (!matcher.find()) {
				errors.append("The bank id can only have numbers\n");
			}

			if (bank.toString().length() != BANK_NUMBER_LENGTH) {
				errors.append("The bank id length must be "
						+ BANK_NUMBER_LENGTH + " \n");
			}
		} else {
			errors.append("Error, there are null fields or the toString method return a null String");
		}
		if (errors.length() > 1) {
			throw new MalformedHandlerException(errors.toString());
		}
		this.officeHandler = office;
		this.bankHandler = bank;
		this.accountNumber = accountNumber;
		this.dc = calculateDC(office.toString(), bank.toString(), accountNumber
				+ "");
	}

	/**
	 *
	 * @param another
	 * @throws MalformedHandlerException
	 */
	public AccountHandler(Handler another) throws MalformedHandlerException {
		this(getField(another, 1, SEPARATOR), getField(another, 0, SEPARATOR),
				getField(another, 3, SEPARATOR).toString());
		StringBuilder error = new StringBuilder();
		if (!getField(another, 2, SEPARATOR).toString().equals(this.dc)) {
			error.append("Wrong control digits");
		}
		if (error.length() > 0) {
			throw new MalformedHandlerException(error.toString());
		}
	}

	/**
	 *
	 * @return
	 */
	public Handler getBankHandler() {
		return this.bankHandler;
	}

	/**
	 *
	 * @return
	 */
	public Handler getOfficeHandler() {
		return this.officeHandler;
	}

	/**
	 *
	 * @return
	 */
	public String getNumber() {
		return this.accountNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getDC() {
		return this.dc;
	}

	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return bankHandler.toString() + SEPARATOR + officeHandler.toString()
				+ SEPARATOR + dc + SEPARATOR + accountNumber;
	}
}
