package jp.abc;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/index/{num}") //URLのパスを指定 /{変数}を指定するときはPathVariable
	public String index(@PathVariable int num, Model model) {
		int res = 0;
		for (int i = 1; i <= num; i++) {
			res += i;
		}
		model.addAttribute("msg", "total: " + res);
		return "index";
	}

	@RequestMapping("/mav/{num}")
	public ModelAndView mav(@PathVariable int num, ModelAndView mav) {
		int res = 0;
		for (int i = 1; i <= num; i++) {
			res += i;
		}
		mav.addObject("msg", "total: " + res); //
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/form1", method = RequestMethod.GET)
	public ModelAndView form1(ModelAndView mav) {
		mav.setViewName("form1");
		mav.addObject("msg", "お名前を書いて送信してください");
		return mav;
	}

	@RequestMapping(value = "/form1", method = RequestMethod.POST)
	public ModelAndView send(@RequestParam("text1") String str,
			@RequestParam("text2") String str2,
			ModelAndView mav) {
		int p = (str + str2).hashCode() % 101;
		p = Math.abs(p);
		mav.addObject("msg", "こんにちは" + str + "さん！");
		mav.addObject("result", str + "さんの"
				+ str2 + "度は、" + p + "％です。");
		mav.addObject("value", str);
		mav.addObject("text2", str2);
		mav.setViewName("form1");
		return mav;
	}

	@RequestMapping("/other")
	public String other() {
		return "redirect:/";
	}

	@RequestMapping("/home")
	public String home() {
		return "forward:/";
	}

	@RequestMapping("/date")
	public ModelAndView date(
			@RequestParam("id") int id,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("id", id);
		mav.addObject("check", id % 2 == 0);
		mav.addObject("trueVal", "Even number!");
		mav.addObject("falseVal", "Odd number..");
		return mav;
	}

	@RequestMapping("/number/{id}")
	public ModelAndView number(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("number");
		mav.addObject("id", id);
		mav.addObject("check", id >= 0);
		mav.addObject("trueVal", "POSITIVE!");
		mav.addObject("falseVal", "negative...");
		return mav;
	}

	@RequestMapping("/month/{month}")
	public ModelAndView month(@PathVariable int month, ModelAndView mav) {
		mav.setViewName("month");
		int m = Math.abs(month) % 12;
		m = m == 0 ? 12 : m;
		mav.addObject("month", m);
		mav.addObject("check", Math.floor(m / 3));
		return mav;
	}

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("list");
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "taro", "taro@yamada", "090-999-999" });
		data.add(new String[] { "hanako", "hanako@flower", "080-888-888" });
		data.add(new String[] { "sachiko", "sachiko@happy", "080-888-888" });
		mav.addObject("data", data);
		return mav;
	}

	@RequestMapping("/tax/{tax}")
	public ModelAndView tax(@PathVariable int tax, ModelAndView mav) {
		mav.setViewName("tax");
		mav.addObject("tax", tax);
		return mav;
	}

	@RequestMapping("/frag")
	public String frag() {
		return "frag";
	}
}