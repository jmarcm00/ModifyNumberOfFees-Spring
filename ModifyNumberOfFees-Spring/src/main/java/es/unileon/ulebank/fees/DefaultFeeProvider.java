/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.ulebank.fees;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roobre
 */
public class DefaultFeeProvider {
    private FeeStrategy defaultFee;
    private static DefaultFeeProvider instance;
    
    private DefaultFeeProvider() {
        try {
            this.defaultFee = new LinearFee(5, 0.01);
        } catch (InvalidFeeException ex) {
            Logger.getLogger(DefaultFeeProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DefaultFeeProvider getInstance() {
        if (DefaultFeeProvider.instance == null) {
            DefaultFeeProvider.instance = new DefaultFeeProvider();
        }
        
        return DefaultFeeProvider.instance;
    }
    
    public FeeStrategy getDefaultFee() {
        return this.defaultFee;
    }
}
