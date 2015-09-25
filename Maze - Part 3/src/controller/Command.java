package controller;

public interface Command {

	/**
	 * Do command.
	 * @param params
	 */
	public void doCommand(String[] params);
}
