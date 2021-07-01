package jp.ac.hcs.s3a327.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	 * @return 結果画面 - タスク管理
	 */
	@GetMapping("/taskList")
	public String getTasklist(Principal principal, Model model){
		
		TaskEntity taskEntity = taskService.selectAll(principal.getName());
		model.addAttribute("taskEntity", taskEntity);
		
//		log.info("[" + principal.getName() + "]天気予報検索:" + uset_id);
//		if (uset_id == "") {
//			return "index";
//		}
		return "task/task";
	}
}
