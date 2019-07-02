package jp.abc;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView form(
			@ModelAttribute("formModel") @Validated MyData mydata,
			BindingResult result,
			ModelAndView mav) {
		if (!result.hasErrors()) {
			repository.saveAndFlush(mydata);
			return new ModelAndView("redirect:/mydata");
		}
		mav.setViewName("mydata");
		mav.addObject("msg", "sorry, error is occured...");
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata,
			@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit mydata");
		Optional<MyData> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute MyData mydata,
			ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/mydata");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "delete mydata");
		Optional<MyData> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id,
			ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/mydata");
	}

	@PersistenceContext
	EntityManager entityManager;

	MyDataDao<MyData> dao;

	@PostConstruct
	public void init() {
		dao = new MyDataDaoImpl(entityManager);
	}

	@RequestMapping("/dao")
	public ModelAndView dao(ModelAndView mav) {
		mav.setViewName("dao");
		mav.addObject("msg", "DAOを使ったサンプルです。");
		List<MyData> list = dao.getAll();
		mav.addObject("datalist", list);
		return mav;
	}
}
