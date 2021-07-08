package jp.ac.hcs.s3a327.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * ユーザ管理リストから情報を受け取り、結果画面を表示する
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - ユーザ管理リスト
	 */
	@GetMapping("/user/list")
	public String getUserList(Principal principal, Model model){
		
		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);
		
		log.info("[" + principal.getName() + "]ユーザリスト");
		
		return "user/userList";
	}
	
	/**
	 * ユーザ登録画面（管理者用）を表示する。
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用）
	 */
	 
	@GetMapping("/user/insert")
	public String getUserInsert(UserForm form, Model model){
		return "user/insert";
	}
	
	/**
	 * 1件分のユーザ情報をデータベースに登録する。
	 * @param form 登録ｓるうユーザ情報（パスワードは平文）
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 */
	 
	@PostMapping("/user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal, Model model) {
	
	//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserInsert(form,model);
		}
		
		log.info("[" + principal.getName() + "]ユーザ登録データ:" + form.toString());
		
		UserData data = userService.refillToData(form);
		boolean result = userService.insertOne(data);
		if(result) {
			model.addAttribute("message","ユーザを登録しました。");
		}else {
			model.addAttribute("errorMessage","ユーザ登録に失敗しました。操作をやり直してください。");
		}
		
		return getUserList(principal,model);
	}
	
	/**
	 * ユーザ登録画面（管理者用）を表示する。
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用）
	 */
	 
	@GetMapping("/user/detail/{id}")
	public String getUserDetail(@PathVariable("id") String user_id,
			Principal principal, Model model){
		
		UserData data = userService.selectOne(user_id);
		
		model.addAttribute("userData",data);
		return "user/detail";
	}
	
	/**
	 * タスク管理リストから選択されたタスクをタスク管理リストから削除する
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - タスク管理削除
	 * @throws java.text.ParseException 
	 * @throws ParseException 
	 
	@GetMapping("/taskdelete/{id}")
	public String deleteTask(@PathVariable("id") int id,
			Principal principal, Model model) throws ParseException, java.text.ParseException{
		
		boolean isSuccess = taskService.deleteOne(id);
		
		log.info("[" + principal.getName() + "]タスク削除:  " + "id:  " + id);
		if (isSuccess) {
			log.info("削除成功");
		}else {
			log.info("削除失敗");
		}
		return getTasklist(principal,model);
	}*/
}
