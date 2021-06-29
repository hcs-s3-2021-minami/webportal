package jp.ac.hcs.s3a327.zipcode;

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
public class ZipCodeService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** 郵便番号検索API　リクエストURL */
	private static final String URL = "http://zipcloud.ibsnet.co.jp/doc/api/search?zipcode={zipcode}";
	
	/**
	 * 指定した郵便番号に紐づく郵便番号情報を取得する。
	 * @param zipcode 郵便番号（7桁、ハイフン無し）
	 * @return ZipCode Entity
	 */
	public ZipCodeEntity getZip(String zipcode) {
		//APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, zipcode);
		
		//エンティティクラスを生成
		ZipCodeEntity zipCodeEntity = new ZipCodeEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			//statusパラメータの抽出
			String status = node.get("status").asText();
			zipCodeEntity.setStatus(status);
			//messageパラメータの抽出
			String message = node.get("message").asText();
			zipCodeEntity.setMessage(status);
			//messageパラメータの抽出
		}
	}
	
}
