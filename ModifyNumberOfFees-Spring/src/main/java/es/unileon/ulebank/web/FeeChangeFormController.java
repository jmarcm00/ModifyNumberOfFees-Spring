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

@Controller
@RequestMapping(value="/feeLimits.htm")
public class FeeChangeFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private FeeManager feeManager;

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

    @RequestMapping(method = RequestMethod.GET)
    protected FeeChanges formBackingObject(HttpServletRequest request) throws ServletException {
    	FeeChanges numFees = new FeeChanges();
        numFees.setNumberOfFees(this.feeManager.getFees().getAmortizationTime());
        return numFees;
    }

    public void setProductManager(FeeManager feeManager) {
        this.feeManager = feeManager;
    }

    public FeeManager getProductManager() {
        return feeManager;
    }

}