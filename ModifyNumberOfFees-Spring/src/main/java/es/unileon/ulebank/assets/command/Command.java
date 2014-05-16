package es.unileon.ulebank.assets.command;

import es.unileon.ulebank.assets.handler.Handler;

public interface Command {
	public void execute();
	public void undo();
	public void redo();
	public Handler getId();
}
