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
	 * 指定したユーザIDのタスク情報を1件追加する。
	 * @param userId
	 * @return TaskEntity
	 * @throws java.text.ParseException 
	 
	public boolean insertOne(String userId, String comment, String limitday) throws ParseException, java.text.ParseException{
		
		UserData data = new UserData();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		data.setUser_id(userId);
		data.setComment(comment);
		data.setLimitday(dateFormat.parse(limitday));
		boolean isSuccess = false;
//		boolean result = limitday instanceof Date;
		try {
			int rowNumber = taskRepository.insertOne(data);
			
			if(rowNumber >= 1 || (comment != "" || comment.length() <= 50) 
					|| (limitday != "")) {
				isSuccess = true;
			}
			 
		}catch(DataAccessException e){
			e.printStackTrace();
			
		}
		
		
		
		return isSuccess;
			
	}
	
	public boolean deleteOne(int id) throws ParseException, java.text.ParseException{
		boolean isSuccess = false;
		
		try {
			int rowNumber = taskRepository.deleteOne(id);
			
			if(rowNumber >= 1) {
				isSuccess = true;
			}
			 
		}catch(DataAccessException e){
			e.printStackTrace();
			
		}
		
		return isSuccess;
			
	}*/
	
	
	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskRepository.tasklistCsvOut(user_id);
	}*/

	
	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}*/
}
