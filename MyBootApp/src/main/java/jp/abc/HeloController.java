package jp.abc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "お名前を書いて送信してください。");
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView send(@RequestParam("text1") String str, @RequestParam("text2") String str2, ModelAndView mav) {
		int p = (str + str2).hashCode() % 10;
		mav.addObject("msg", "こんにちは、" + str + "さん！");
		mav.addObject("result", str + "さんの" + str2 + "度は" + p + "%です。");
		mav.addObject("value", str);
		mav.addObject("text2", str2);
		mav.setViewName("index");
		return mav;
	}
}