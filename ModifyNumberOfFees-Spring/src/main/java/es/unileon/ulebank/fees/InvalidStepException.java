/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.ulebank.fees;

/**
 * 
 * @author roobre
 */
public class InvalidStepException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidStepException() {
		super("Bounds must be positive doubles");
	}

	public InvalidStepException(double low, double high) {
		super(low + " must be lower than " + high);
	}
}
