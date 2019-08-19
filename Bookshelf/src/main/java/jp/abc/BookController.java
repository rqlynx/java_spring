package jp.abc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repositories.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	/* ページ遷移時の処理 */

	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ModelAndView tweet(ModelAndView mav) {
		mav.setViewName("book");
		/* データベースを呼んでくる */
		Book books = new Book();
		mav.addObject("formModel", books);

		/* ツイートリストを読み込み */
		List<Book> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	/* POST時の処理 */

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	/* @Validと@ModelAttributeがよくわからん */
	public ModelAndView tweetform(@ModelAttribute("formModel")@Validated Book book, BindingResult result, ModelAndView mav) {
		if (result.hasErrors()) {
			mav.setViewName("book");
			/* データベースを呼んでくる */
			mav.addObject("formModel", book);
			Iterable<Book> list = repository.findAll();
			mav.addObject("datalist", list);
			return mav;
		} else {
			/* セーブ */
			repository.saveAndFlush(book);
			return new ModelAndView("redirect:/book/");
		}
	}
}
