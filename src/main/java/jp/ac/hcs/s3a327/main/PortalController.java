package jp.ac.hcs.s3a327.main;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class PortalController {
	
	/**
	 * メイン画面を表示する
	 * @param principal ログイン情報
	 * @param model
	 * @return メイン画面
	 */
	@RequestMapping("/")
	public String index(Principal principal, Model model) {
		log.info("[" + principal.getName() + "]メイン画面");
		return "index";
	}
	

}