package es.unileon.ulebank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.unileon.ulebank.service.FeeChanges;
import es.unileon.ulebank.service.FeeManager;

/**
 * Class Controller of the page feeLimits.jsp
 * @brief Concrete controller of cashLimits.jsp which change the number of fees in the loan.
 */
@Controller
@RequestMapping(value="/feeLimits.htm")
public class FeeChangeFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    /** Manager de loan */
    @Autowired
    private FeeManager feeManager;

    /**
     * Method that obtains the data of the form in feeLimits.jsp and save the changes in the loan
     * @param feeChange
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid FeeChanges feeChange, BindingResult result)
    {
        if (result.hasErrors()) {
            return "feeLimits";
        }
		
        int fees = feeChange.getNumberOfFees();
        logger.info("Changing number of fees to " + fees );

        feeManager.setNewNumberOfFees(fees);

        return "redirect:/changeNumFees.htm";
    }

    /**
     * Method that sends the number of fees in the loan to the form in feeLimits.jsp
     * @param request
     * @return
     * @throws ServletException
     */
    @RequestMapping(method = RequestMethod.GET)
	public FeeChanges formBackingObject(HttpServletRequest request) throws ServletException {
    	FeeChanges numFees = new FeeChanges();
        numFees.setNumberOfFees(this.feeManager.getFees().getAmortizationTime());
        return numFees;
    }

    /**
     * Setter of the manager
     * @param feeManager
     */
    public void setProductManager(FeeManager feeManager) {
        this.feeManager = feeManager;
    }

    /**
     * Getter of the manager
     * @return
     */
    public FeeManager getProductManager() {
        return feeManager;
    }

}