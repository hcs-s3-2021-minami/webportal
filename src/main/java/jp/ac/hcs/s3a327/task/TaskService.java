package jp.ac.hcs.s3a327.task;

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
	
}
