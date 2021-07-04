package jp.ac.hcs.s3a327.task;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク情報を操作する。
 */
@Transactional
@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	/**
	 * 指定したユーザIDのタスク情報を全件取得する。
	 * @param userId
	 * @return TaskEntity
	 */
	public TaskEntity selectAll(String userId) {

		TaskEntity taskEntity;
		try {
			taskEntity = taskRepository.selectAll(userId);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			taskEntity = null;
		}
		
		return taskEntity;
			
	}
	
	/**
	 * 指定したユーザIDのタスク情報を1件追加する。
	 * @param userId
	 * @return TaskEntity
	 * @throws java.text.ParseException 
	 */
	public boolean insertOne(String userId, String comment, String limitday) throws ParseException, java.text.ParseException{
		
		TaskData data = new TaskData();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		data.setUser_id(userId);
		data.setComment(comment);
		data.setLimitday(dateFormat.parse(limitday));
		boolean isSuccess = false;
		
		try {
			int rowNumber = taskRepository.insertOne(data);
			
			if(rowNumber >= 1) {
				isSuccess = true;
			}
			 
		}catch(DataAccessException e){
			e.printStackTrace();
			
		}
		
		return isSuccess;
			
	}
}
