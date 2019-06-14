package jp.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repositories.MyDataRepository;

@Controller
public class MyDataController {

	@Autowired
	MyDataRepository repository;

	@RequestMapping(value = "/mydata", method = RequestMethod.GET) //URLのパス/GETメソッド入手
	public ModelAndView mydata(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		mav.setViewName("mydata"); //引っ張ってくるHTMLの名前
		mav.addObject("msg", "this is sample content.");
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/mydata", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/mydata");
	}

}
