package jp.ac.hcs.s3a327.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class WeatherController {
	@Autowired
	private WeatherService weatherService;

	/**
	 * 都市コードから札幌の天気予報を検索し、結果画面を表示する
	 * @param cityCode 都市コード
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 天気予報結果
	 */
	@PostMapping("/weather")
	public String getWeather(@RequestParam("cityCode") String cityCode,
			Principal principal, Model model){
		WeatherEntity weatherEntity = weatherService.getWeather(cityCode);
		
		model.addAttribute("weatherEntity", weatherEntity);
		
		log.info("[" + principal.getName() + "]天気予報検索:" + cityCode);
		if (cityCode == "") {
			return "index";
		}
		return "weather/weather";
	}
}
