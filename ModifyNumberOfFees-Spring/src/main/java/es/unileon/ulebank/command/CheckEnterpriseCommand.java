/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.command;

import es.unileon.ulebank.client.EnterpriseHandler;
import es.unileon.ulebank.handler.Handler;
import java.util.Date;

/**
 *
 * @author Paula
 */

/**** DUDOSO ****/

public class CheckEnterpriseCommand implements Command {
	private EnterpriseHandler enterpriseCode;

	/**
	 *
	 * @param cif
	 * @param letter
	 */
	public CheckEnterpriseCommand(int cif, char letter) {
		this.enterpriseCode = new EnterpriseHandler(letter, cif);

	}

	/**
     *
     */
	@Override
	public void execute() {
		this.enterpriseCode.compareTo(enterpriseCode);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Date getEffectiveDate() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Handler getID() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	/**
     *
     */
	@Override
	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	/**
     *
     */
	@Override
	public void redo() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
