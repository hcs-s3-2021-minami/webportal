package jp.ac.hcs.s3a327.task;

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
public class TaskService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** 郵便番号検索API　リクエストURL */
	private static final String URL = "https://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";
	
	/**
	 * 指定した郵便番号に紐づく郵便番号情報を取得する。
	 * @param zipcode 郵便番号（7桁、ハイフン無し）
	 * @return ZipCode Entity
	 */
	public TaskEntity getTaskList(String id) {
		
		//APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, id);
		
		//エンティティクラスを生成
		TaskEntity taskEntity = new TaskEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			//statusパラメータの抽出
			String status = node.get("status").asText();
			taskEntity.setStatus(status);
			//messageパラメータの抽出
			String message = node.get("message").asText();
			taskEntity.setMessage(message);
			
			//resultsパラメータの抽出（配列分取得する）
			for (JsonNode forecast : node.get("forecasts")) {
				//データクラスの生成（result1件分）
				TaskData weatherData = new TaskData();
				
				weatherData.setId(forecast.get("id").asInt());
				weatherData.setComment(forecast.get("comment").asText());
				weatherData.setLimitday(forecast.get("limitday").asText());
//				weatherData.setTelop(forecast.get("description").asText());
				
				//可変長配列の末尾に追加
				taskEntity.getForecasts().add(taskData);
			}
		}catch (IOException e) {
				//例外発生時は、エラーメッセージの詳細を標準エラー出力
				e.printStackTrace();
		}
		
		return taskEntity;
			
	}
	
}
