package jp.abc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ObjectController {
    @RequestMapping("/obj")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("obj");
        mav.addObject("msg", "current data");
        DataObject obj = new DataObject(123, "hanako", "hanako@flower");
        mav.addObject("obj", obj);
        mav.addObject("code", "message 1<hr/>message 2<hr/>message 3");
        return mav;
    }
}