package jp.ac.hcs.s3a327.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ShopController {
	@Autowired
	private GourmetService gourmetService;

	/**
	 * キーワードから店舗を検索し、結果画面を表示する
	 * @param shopname 検索するキーワード
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 店舗一覧
	 */
	@PostMapping("/gourmet")
	public String getZipCode(@RequestParam("shopname") String shopname,
			Principal principal, Model model){
		
		ShopEntity shopEntity = gourmetService.getShop(shopname);
		model.addAttribute("shopEntity", shopEntity);
		model.addAttribute("shopname", shopname);
		
		log.info("[" + principal.getName() + "]グルメ検索:" + shopname);
		if (shopname == "") {
			return "index";
		}
		return "gourmet/gourmet";
	}
}
