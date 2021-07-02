package jp.ac.hcs.s3a327.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;

	/**
	 * タスク管理リストから情報を受け取り、結果画面を表示する
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - タスク管理リスト
	 */
	@GetMapping("/tasklist")
	public String getTasklist(Principal principal, Model model){
		
		TaskEntity taskEntity = taskService.selectAll(principal.getName());
		model.addAttribute("taskEntity", taskEntity);
		
		log.info("[" + principal.getName() + "]タスクリスト");
		if (taskEntity == null) {
			return "index";
		}
		return "task/task";
	}
	
	
	/**
	 * タスク管理リストから情報を受け取り、結果画面を表示する
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - タスク管理追加
	 */
	@GetMapping("/taskinsert")
	public String insertTask(@RequestParam("comment") String comment,@RequestParam("limitday") String limitday,
			Principal principal, Model model){
		
		boolean isSuccess = taskService.insertOne(principal.getName(),comment,limitday);
		
		log.info("[" + principal.getName() + "]タスク追加:" + "コメント:" + comment + "期限日:" + limitday);
		if (isSuccess) {
			log.info("成功");
		}else {
			log.info("失敗");
		}
		return getTasklist(principal,model);
	}
}
