/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.utils;

/**
 * Class based in Songleton pattern that gets the dni letter of a dni number
 *
 * @author Gonzalo Nicolas Barreales
 */
public class DniLetters {

	/**
	 * instance of the class
	 */
	private static DniLetters instance = null;

	/**
	 * Gets the unique instance of the class, if it doesn't exists, the instance
	 * is created
	 *
	 * @return instance
	 */
	public static DniLetters getInstance() {
		if (instance == null) {
			instance = new DniLetters();
		}
		return instance;
	}

	/**
	 * dni letters table
	 */
	private final char[] dniLetters;

	/**
	 * Constructos that create the dni letters table.
	 */
	private DniLetters() {

		char[] dniLetters = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',
				'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
		this.dniLetters = dniLetters;
	}

	/**
	 * Obtaint the dni correct letter and compare it with the parametter
	 * dniLetter
	 *
	 * @param dniNumber
	 * @param dniLetter
	 * @return true if the letter is valid, false if the letter is not correct
	 */
	public boolean isDniValid(int dniNumber, char dniLetter) {
		boolean result = false;
		int dniRest = dniNumber % 23;
		if (dniRest < 23 && dniLetters[dniRest] == dniLetter
				&& Integer.toString(dniNumber).length() <= 8) {
			result = true;
		}
		return result;
	}

}
