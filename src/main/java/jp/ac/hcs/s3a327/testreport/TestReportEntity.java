package jp.ac.hcs.s3a327.testreport;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 受験報告情報。
 * 各項目のデータ仕様も合わせて管理する。
 */
@Data
public class TestReportEntity {
	
	/** 受験報告情報のリスト **/
	private List<TestReportData> testreportlist = new ArrayList<TestReportData>();
	

}
