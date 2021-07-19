package jp.ac.hcs.s3a327.testreport;

import java.util.Date;

import lombok.Data;

/**
 * 1件分の受験報告情報
 */
@Data
public class TestReportData {
	
	/** 
	 * 受験報告ID
	 * 主キー、SQLにて自動採番
	 */
	private int id;
	
	/** 
	 * 状態
	 * Testreportテーブルの主キーと紐づく、受験報告の状態
	 */
	private String status;

	/** 
	 * 名前
	 * Testreportテーブルの主キーと紐づく、受験報告情報から取得
	 */
	private String user_name;
	
	/** 
	 * 所属クラス
	 * Testreportテーブルの主キーと紐づく、受験報告情報から取得
	 */
	private String user_class;
	
	/** 
	 * 出席番号
	 * Testreportテーブルの主キーと紐づく、受験報告情報から取得
	 */
	private int attendance_number;
	
	/** 
	 * 日付
	 * Testreportテーブルの主キーと紐づく、受験報告情報から取得
	 */
	private Date day;


}