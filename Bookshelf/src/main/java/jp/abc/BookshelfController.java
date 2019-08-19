package jp.abc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repositories.BookshelfRepository;

@Controller
public class BookshelfController {

	@Autowired /* インスタンスをフィールドに関連付け */
	BookshelfRepository repository; /* リポジトリを取得 */

	/* ページ遷移時の処理 */

	@RequestMapping(value = "/bookshelf/", method = RequestMethod.GET) /* マッピング */
	public ModelAndView bookshelf(@ModelAttribute("formModel") Bookshelf bookshelf, ModelAndView mav) {
		mav.setViewName("bookshelf"); /* 引っ張ってくるHTMLの名前 */
		Iterable<Bookshelf> list = repository.findAll(); /* UserRepositoryの中身をすべて取得 */
		mav.addObject("datalist", list); /* 取得したリストをdatalistという名前で渡す */
		return mav; /* 画面を返す（表示する）*/
	}

	/* Post時の処理 */

	@RequestMapping(value = "/bookshelf", method = RequestMethod.POST) /* マッピング */
	public ModelAndView form(
			/* [ModelAttribute] => 送信されたデータを保存するアノテーション */
			@ModelAttribute("formModel") @Validated Bookshelf bookshelf,
			BindingResult result,
			ModelAndView mav) {
		/* エラーが発生していなければセーブしてリダイレクト */
		if (!result.hasErrors()) {
			repository.saveAndFlush(bookshelf);
			return new ModelAndView("redirect:/bookshelf/");
		}
		mav.setViewName("bookshelf");
		Iterable<Bookshelf> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	/* ページ遷移時の処理 */

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute Bookshelf bookshelf,
			@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("edit");
		Optional<Bookshelf> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute("formModel") @Validated Bookshelf bookshelf,
			BindingResult result,
			ModelAndView mav) {
		/* エラーが発生していなければセーブしてリダイレクト */
		if (!result.hasErrors()) {
			repository.saveAndFlush(bookshelf);
			return new ModelAndView("redirect:/bookshelf/");
		}
		mav.setViewName("edit");
		return mav;
	}
}
