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
public class InvalidFeeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidFeeException() {
		super("Fee factor must be a positive double");
	}
}
