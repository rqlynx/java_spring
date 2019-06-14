package jp.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jp.abc.repositories.MyDataRepository;

@Controller
public class MyDataController {

	@Autowired
	private MyDataRepository repository;
}
