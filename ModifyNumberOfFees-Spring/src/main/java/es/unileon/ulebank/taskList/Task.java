/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.taskList;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.handler.Handler;
import java.util.Date;

/**
 *
 * @author runix
 */
public class Task {

    
    private final Command command;

    private final Date effectiveDate;

    public Task(Date effectiveDate, Command command) {
        this.effectiveDate = effectiveDate;
        this.command = command;
    }

    public Handler getID() {
        return this.command.getID();
    }

    public void execute() {
        this.command.execute();
    }

    public void undo() {
        this.command.undo();
    }

    public void redo() {
        this.command.redo();
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
}
