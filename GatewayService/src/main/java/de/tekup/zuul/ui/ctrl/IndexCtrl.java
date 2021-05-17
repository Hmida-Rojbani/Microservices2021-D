package de.tekup.zuul.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCtrl {
	
	@GetMapping("/")
	public String indexTemplate() {
		return "index";
	}

}
