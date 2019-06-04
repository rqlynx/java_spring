package jp.abc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	@RequestMapping(value="/form2",method=RequestMethod.GET)
	public ModelAndView form(ModelAndView mav) {
		mav.setViewName("form2");
		return mav;
	}
}
