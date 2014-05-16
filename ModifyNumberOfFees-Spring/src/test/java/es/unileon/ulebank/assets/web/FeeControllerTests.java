package es.unileon.ulebank.assets.web;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.service.SimpleFeeManager;
import es.unileon.ulebank.web.FeeController;


public class FeeControllerTests {

	@Test
	public void testHandleRequestView() throws Exception{		
		FeeController controller = new FeeController();
		controller.setProductManager(new SimpleFeeManager());
		ModelAndView modelAndView = controller.handleRequest(null, null);		
		assertEquals("changeNumFees", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());
		@SuppressWarnings("unchecked")
		Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
		String nowValue = (String) modelMap.get("now");
		assertNotNull(nowValue);
	}

}