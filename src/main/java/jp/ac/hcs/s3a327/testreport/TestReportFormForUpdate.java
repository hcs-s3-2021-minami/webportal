package jp.ac.hcs.s3a327.testreport;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * アップデート用にパスワード、ダークモード、権限のチェックを外したUserForm。
 * その他の内容はUserFormに準じる。
 */
@Data
public class TestReportFormForUpdate {
	
	/** ユーザID（メールアドレス）*/
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String user_id;
	

	/** パスワード */
	private String password;
	
	
	/** ユーザ 名*/
	@NotBlank(message = "{require_check}")
	private String user_name;
	
	
	/** ダークモードフラグ */
	private boolean darkmode;
	
	
	/** 権限 */
	private String role;
}
