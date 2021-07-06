package jp.ac.hcs.s3a327.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * キーワード検索結果の店舗情報。
 * 各項目のデータ仕様については、APIの仕様を参照とする。
 * １つのキーワードに複数の店舗が紐づく為、リスト構造となっている。
 * - https://webservice.recruit.co.jp/doc/hotpepper/reference.html
 */
@Data
public class ShopEntity {
	
	/** 検索キーワード **/
	private String shopname;
	
	/** 店舗情報のリスト **/
	private List<ShopData> results = new ArrayList<ShopData>();
	

}
