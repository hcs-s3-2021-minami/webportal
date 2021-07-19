package jp.ac.hcs.s3a327.testreport;


import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * フロント-バックでユーザ情報をやり取りする。
 * 各項目のデータ仕様はUserEntityを参照とする。
 */
@Data
public class TestReportForm {
	
	/** 
	 * ユーザID（メールアドレス）
	 * 必須チェック、妥当性チェック（メールアドレス形式）
	 */
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String user_id;
	

	/** 
	 * パスワード
	 * 必須チェック、桁数チェック（長さ4から100桁まで）、妥当性チェック（英数字大文字含む）
	 */
	@NotBlank(message = "{require_check}")
	@Length(min=4,max=100,message = "{length_check}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message = "{pattern_check}")
	private String password;
	
	
	/** 
	 * ユーザ名
	 * 必須チェック
	 */
	@NotBlank(message = "{require_check}")
	private String user_name;
	
	
	/** 
	 * ダークモードフラグ
	 * - ON：true
	 * - OFF：false
	 * 必須チェック、妥当性チェック（true/false)
	 */
	//@NotBlank(message = "{require_check}")
	//@Pattern(regexp = "^(true|false)$",message = "{pattern_check}")
	@AssertFalse(message = "{false_check}")
	private boolean darkmode;
	
	
	/** 
	 * 権限
	 * - 管理："ROLE_ADMIN"
	 * - 一般："ROLE_GENERAL"
	 * 必須チェック
	 */
	@NotBlank(message = "{require_check}")
	private String role;
}
