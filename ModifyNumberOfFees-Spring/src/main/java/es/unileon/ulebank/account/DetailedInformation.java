/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.account;

/**
 *
 * @author runix
 */
public class DetailedInformation {

	private final StringBuffer info;
	private boolean nonEditable;

	/**
	 *
	 * @param information
	 */
	public DetailedInformation(String information) {
		this.info = new StringBuffer(information);
		this.nonEditable = false;
	}

	/**
     *
     */
	public DetailedInformation() {
		this("");
	}

	/**
	 *
	 * @param information
	 */
	public void appendInformation(String information) {
		if (!this.nonEditable) {
			this.info.append(information);
			if (information.charAt(information.length() - 1) != '\n') {
				this.info.append("\n");
			}
		}
	}

	/**
	 *
	 * @param information
	 */
	public void appendInformaton(StringBuffer information) {
		this.appendInformation(information.toString());
	}

	/**
     *
     */
	public void doFinal() {
		this.nonEditable = true;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return this.info.toString();
	}
}
