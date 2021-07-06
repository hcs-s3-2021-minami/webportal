package jp.ac.hcs.s3a327.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 店舗情報を操作する。
 * ホットペッパーWebサービスのグルメサーチAPIを活用する。
 * - https://webservice.recruit.co.jp/doc/hotpepper/reference.html
 */
@Transactional
@Service
public class GourmetService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** グルメサーチAPI リクエストURL */
	private static final String URL =
	"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";
	
	private static final String API_KEY = "54ed4f3e59b75f04";
	
	private static final String large_service_area = "SS40";

	
	/**
	 * 指定したキーワードに紐づく店舗情報を取得する。
	 * @param shopname 
	 * @return ShopEntity
	 */
	public ShopEntity getShop(String shopname) {
		
		//APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
		
		//エンティティクラスを生成
		ShopEntity shopEntity = new ShopEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);

			
			//resultsパラメータの抽出（配列分取得する）
			for (JsonNode shop : node.get("results").get("shop")) {
				//データクラスの生成（result1件分）
				ShopData shopData = new ShopData();
				
				shopData.setId(shop.get("id").asText());
				shopData.setName(shop.get("name").asText());
				shopData.setLogo_image(shop.get("logo_image").asText());
				shopData.setName_kana(shop.get("name_kana").asText());
				shopData.setAddress(shop.get("address").asText());
				shopData.setAccess(shop.get("access").asText());
				shopData.setUrl(shop.get("urls").get("pc").asText());
				shopData.setImage(shop.get("photo").get("mobile").get("l").asText());
				
				//可変長配列の末尾に追加
				shopEntity.getResults().add(shopData);
			}
		}catch (IOException e) {
				//例外発生時は、エラーメッセージの詳細を標準エラー出力
				e.printStackTrace();
		}
		
		return shopEntity;
			
	}
	
}
