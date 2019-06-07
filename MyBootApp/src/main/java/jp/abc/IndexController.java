package jp.abc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/index/{num}")
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
        mav.addObject("msg", "total: " + res);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value="/form1", method=RequestMethod.GET)
    public ModelAndView form1(ModelAndView mav) {
        mav.setViewName("form1");
        mav.addObject("msg", "お名前を書いて送信してください");
        return mav;
    }

    @RequestMapping(value="/form1", method=RequestMethod.POST)
    public ModelAndView send(@RequestParam("text1")String str,
            @RequestParam("text2")String str2,
            ModelAndView mav) {
        int p = (str + str2).hashCode() % 101;
        p = Math.abs(p);
        mav.addObject("msg", "こんにちは" + str + "さん！");
        mav.addObject("result", str + "さんの"
                            + str2 + "度は、" + p +"％です。");
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
}