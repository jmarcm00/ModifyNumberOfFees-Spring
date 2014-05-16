package es.unileon.ulebank.fees;

public class CrossedStepException extends Exception {

	private static final long serialVersionUID = 1L;

	public CrossedStepException(FeeStep step1, FeeStep step2) {
		super("Step " + step1.getLow() + " collides with " + step2);
	}
}
