package jp.abc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/") //URLの名前
	public ModelAndView mav(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
}