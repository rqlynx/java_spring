package jp.abc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repositories.PlayListRepository;

@Controller
public class PlayListController {

	@Autowired
	private PlayListRepository repository;

	@RequestMapping(value = "/playlist", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("playlist");
		PlayList pl = new PlayList();
		mav.addObject("formModel", pl);
		List<PlayList> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "playlist", method = RequestMethod.POST)
	public ModelAndView post(
			@ModelAttribute("formModel") @Validated PlayList playlist,
			Errors result,
			ModelAndView mav) {
		if (result.hasErrors()) {
			mav.addObject("msg", "エラーですよ");
			return mav;
		}
		repository.saveAndFlush(playlist);
		return new ModelAndView("redirect:/playlist");
	}
}