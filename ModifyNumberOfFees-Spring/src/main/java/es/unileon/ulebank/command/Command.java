/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.Handler;
import java.util.Date;

/**
 *
 * @author runix
 */
public interface Command {

	/**
	 *
	 * @return
	 */
	public Date getEffectiveDate();

	/**
	 *
	 * @return
	 */
	public Handler getID();

	/**
     *
     */
	public void execute();

	/**
     *
     */
	public void undo();

	/**
     *
     */
	public void redo();
}
