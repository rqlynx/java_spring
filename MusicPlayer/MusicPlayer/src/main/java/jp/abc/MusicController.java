package jp.abc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repositories.MusicRepository;

@Controller
public class MusicController {

	@Autowired
	private MusicRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		Music m = new Music();
		mav.addObject("formModel", m);
		List<Music> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView post(
			@ModelAttribute("formModel") @Validated Music music,
			Errors result,
			ModelAndView mav) {
		if (result.hasErrors()) {
			mav.setViewName("index");
			mav.addObject("msg", "エラーです");
			return mav;
		}
		repository.saveAndFlush(music);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute Music music,
			@PathVariable long id,
			ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit music");
		Optional<Music> data = repository.findById(id);
		mav.addObject("formModel", data.get());
		return mav;
	}
}