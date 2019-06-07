package jp.abc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
    @RequestMapping(value="/form2", method=RequestMethod.GET)
    public ModelAndView form2(ModelAndView mav) {
        mav.setViewName("form2");
        return mav;
    }

    @RequestMapping(value="form2", method=RequestMethod.POST)
    public ModelAndView send(
            @RequestParam(value="check1", required=false)boolean check1,
            @RequestParam(value="radio1", required=false)String radio1,
            @RequestParam(value="select1", required=false)String select1,
            @RequestParam(value="select2", required=false)String[] select2,
            ModelAndView mav) {
        String res = "";
        try {
            res= "check1:" + check1 +
                    " radio: " + radio1 +
                    " select: " + select1 +
                    "\nselect2:";
        } catch (NullPointerException e) {}
        try {
            res += select2[0];
            for (int i = 1; i < select2.length; i++) {
                res += ", " + select2[i];
            }
        } catch (NullPointerException e) {
            res += "null";
        }
        mav.addObject("msg", res);
        mav.setViewName("form2");
        return mav;
    }

}