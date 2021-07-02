package jp.ac.hcs.s3a327.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	 */
	public int insertOne(String userId, String comment, String limitday) {
		
		TaskData data = new TaskData();
		data.setId((Integer) map.get("id"));
		data.setUser_id((String) map.get("user_id"));
		data.setComment((String) map.get("comment"));
		data.setLimitday((Date) map.get("limitday"));
		
		try {
			int rowNumber = taskRepository.insertOne(data);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			boolean isSuccess = taskRepository.insertOne(principal.getName(),comment,limitday);
		}
		
		return isSuccess;
			
	}
}
