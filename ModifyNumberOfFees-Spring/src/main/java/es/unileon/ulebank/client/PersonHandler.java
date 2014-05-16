/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.utils.DniLetters;

/**
 * Identifies a person with the dni
 * 
 * @author Gonzalo Nicolas Barreales
 */
public class PersonHandler implements Handler {

	/**
	 * DNI number
	 */
	private int dni;

	/**
	 * DNI letter
	 */
	private char letter;

	private char foreingLetter;

	/**
	 * Creates the handler of the person with the dni data
	 * 
	 * @param dni
	 * @param letter
	 * @throws MalformedHandlerException
	 *             if the letter doesn't match with the dni number
	 */
	public PersonHandler(int dni, char letter) throws MalformedHandlerException {
		letter = Character.toUpperCase(letter);
		if (DniLetters.getInstance().isDniValid(dni, letter)) {
			this.dni = dni;
			this.letter = letter;
			this.foreingLetter = ' ';
		} else {
			throw new MalformedHandlerException("Incorrect DNI");
		}
	}

	/**
	 *
	 * @param foreingLetter
	 * @param dni
	 * @param letter
	 * @throws MalformedHandlerException
	 */
	public PersonHandler(char foreingLetter, int dni, char letter)
			throws MalformedHandlerException {
		foreingLetter = Character.toUpperCase(foreingLetter);
		letter = Character.toUpperCase(letter);
		int addFactor = 0;
		switch (foreingLetter) {
		case 'X':
			break;
		case 'Y':
			addFactor = 10000000;
			break;
		case 'Z':
			addFactor = 20000000;
			break;
		default:
			throw new MalformedHandlerException("Incorrect NIE");
		}
		if (DniLetters.getInstance().isDniValid(addFactor + dni, letter)) {
			this.foreingLetter = foreingLetter;
			this.dni = dni;
			this.letter = letter;
		} else {
			throw new MalformedHandlerException("Invalid NIE");
		}
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
		String result = "";
		if (foreingLetter != ' ')
			result += foreingLetter;
		result += Integer.toString(dni) + letter;
		return result;
	}

}
