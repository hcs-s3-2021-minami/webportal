package jp.ac.hcs.s3a327.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ情報を操作する。
 */
@Transactional
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * ユーザ情報を全件取得する。
	 * @param userId
	 * @return UserEntity
	 */
	public UserEntity selectAll() {

		UserEntity userEntity;
		try {
			userEntity = userRepository.selectAll();
			
		}catch(DataAccessException e){
			e.printStackTrace();
			userEntity = null;
		}
		
		return userEntity;
			
	}
	
	/**
	 * ユーザ情報を1件追加する。
	 * @param userData追加するユーザ情報（パスワードは平文）
	 * @return 処理結果（成功：true,失敗：false）
	 */
	public boolean insertOne(UserData userData) {
		int rowNumber;
		try {
			rowNumber = userRepository.insertOne(userData);
		}catch(DataAccessException e){
			e.printStackTrace();
			rowNumber = 0;
		}
		return rowNumber > 0;	
	}
	
	/**
	 * ユーザ情報を1件選択する。
	 * @param userData取得するユーザ情報
	 * @return 処理結果（成功：true,失敗：false）
	 */
	public UserData selectOne(String user_id) {
		UserData userData = new UserData();
		try {
			userData = userRepository.selectOne(user_id);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			
		}
		
		return userData;
			
	}
	
	/**
	 * ユーザ情報を1件削除する。
	 * @param userData 削除するユーザ情報
	 * @return 処理結果（成功：true,失敗：false）
	 */
	public boolean deleteOne(String user_id) {
		int rowNumber;
		
		try {
			rowNumber = userRepository.deleteOne(user_id);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			rowNumber = 0;
		}
		return rowNumber > 0;	
	}
	
	
	/**
	 * 入力項目をUserDataへ変換する
	 * （このメソッドは入力チェックを実施したうえで呼び出すこと）
	 * @param from 入力データ
	 * @return UserData
	 */
	UserData refillToData(UserForm form) {
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setPassword(form.getPassword());
		data.setUser_name(form.getUser_name());
		data.setDarkmode(form.isDarkmode());
		data.setRole(form.getRole());
		//初期値は有効とする
		data.setEnabled(true);
		return data;
	}
	
}
