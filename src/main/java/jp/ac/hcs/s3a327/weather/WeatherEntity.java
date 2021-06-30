package jp.ac.hcs.s3a327.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 天気予報検索結果の天気情報
 * 各項目のデータ仕様については、APIの仕様を参照とする。
 * - http://weather.tsukumijima.net/
 */
@Data
public class WeatherEntity {
	
	/** タイトル **/
	private String title;
	
	/** 説明文 **/
	private String description;
	
	/** 天気情報のリスト **/
	private List<WeatherData> results = new ArrayList<WeatherData>();
	

}
