package jp.ac.hcs.s3a327.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 郵便番号情報を操作する。
 * zipcloud社の郵便番号検索APIを活用する。
 * - http://zipcloud.ibsnet.co.jp/doc/api
 */
@Transactional
@Service
public class WeatherService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** 郵便番号検索API　リクエストURL */
	private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";
	
	/**
	 * 今日の日付から札幌の天気予報情報を取得する。
	 * @param cityCode 都市コード
	 * @return Weather Entity
	 */
	public WeatherEntity getWeather(String cityCode) {
		
		//APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, cityCode);
		
		//エンティティクラスを生成
		WeatherEntity weatherEntity = new WeatherEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			//titleパラメータの抽出
			String title = node.get("title").asText();
			weatherEntity.setTitle(title);
			//descriptionパラメータの抽出
			String description = node.get("description").asText();
			weatherEntity.setDescription(description);
			
			//resultsパラメータの抽出（配列分取得する）
			for (JsonNode forecast : node.get("forecasts")) {
				//データクラスの生成（result1件分）
				WeatherData weatherData = new WeatherData();
				
				weatherData.setDateLabel(forecast.get("dateLabel").asText());
				weatherData.setTelop(forecast.get("telop").asText());
//				weatherData.setTelop(forecast.get("description").asText());
				
				//可変長配列の末尾に追加
				weatherEntity.getForecasts().add(weatherData);
			}
		}catch (IOException e) {
				//例外発生時は、エラーメッセージの詳細を標準エラー出力
				e.printStackTrace();
		}
		
		return weatherEntity;
			
	}
	
}
