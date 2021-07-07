package jp.ac.hcs.s3a327.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String getTasklist(Principal principal, Model model){
		
		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);
		
		log.info("[" + principal.getName() + "]ユーザリスト");
		
		return "user/user";
	}
	
	/**
	 * ユーザ登録画面（管理者用）を表示する。
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用）
	 
	@GetMapping("/user/insert")
	public String getUserInsert(Userform userform, Model model){
		return "user/insert";
	}*/
	
	/**
	 * 1件分のユーザ情報をデータベースに登録する。
	 * @param form 登録ｓるうユーザ情報（パスワードは平文）
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal, Model model) {
	
	//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserInsert(form,model);
		}
		
		return getUserList(model);
	}*/
	
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
	
	
	/**
	 * 自分の全てのタスク情報をCSVファイルとしてダウンロードさせる.
	 * @param principal ログイン情報
	 * @param model
	 * @return タスク情報のCSVファイル
	 
	@PostMapping("/task/csv")
	public ResponseEntity<byte[]> getTaskCsv(Principal principal, Model model) {

		final String OUTPUT_FULLPATH = WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV;

		log.info("[" + principal.getName() + "]CSVファイル作成:" + OUTPUT_FULLPATH);

		// CSVファイルをサーバ上に作成
		taskService.taskListCsvOut(principal.getName());

		// CSVファイルをサーバから読み込み
		byte[] bytes = null;
		try {
			bytes = taskService.getFile(OUTPUT_FULLPATH);
			log.info("[" + principal.getName() + "]CSVファイル読み込み成功:" + OUTPUT_FULLPATH);
		} catch (IOException e) {
			log.warn("[" + principal.getName() + "]CSVファイル読み込み失敗:" + OUTPUT_FULLPATH);
			e.printStackTrace();
		}

		// CSVファイルのダウンロード用ヘッダー情報設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", WebConfig.FILENAME_TASK_CSV);

		// CSVファイルを端末へ送信
		return new ResponseEntity<byte[]>(bytes, header, HttpStatus.OK);
	}*/
}
