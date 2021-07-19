package jp.ac.hcs.s3a327.testreport;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class TestReportController {
	@Autowired
	private TestReportService testReportService;

	/**
	 * 受験報告リストから情報を受け取り、結果画面を表示する
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 受験報告リスト
	 */
	@GetMapping("/testreport/list")
	public String getTestReportList(Principal principal, Model model){
		
		TestReportEntity testReportEntity = testReportService.selectAll();
		model.addAttribute("testReportEntity", testReportEntity);
		
		log.info("[" + principal.getName() + "]受験報告リスト");
		
		return "testreport/testreportList";
	}
	
//	/**
//	 * 受験報告新規登録画面を表示する
//	 * @param form 登録時の入力チェック用UserForm
//	 * @param model
//	 * @return 結果画面 - 受験報告新規登録（管理者用）
//	 */
//	@GetMapping("/testreport/insert")
//	public String getTestReportInsert(TestReportForm form, Model model){
//		return "testreport/insert";
//	}
//	
//	/**
//	 * 1件分の受験報告情報をデータベースに登録する。
//	 * @param form 登録する受験報告情報
//	 * @param bindingResult データバインド実施結果
//	 * @param principal 受験報告情報
//	 * @param model
//	 * @return 結果画面 - 受験報告一覧
//	 */
//	@PostMapping("/testreport/insert")
//	public String getTestReportInsert(@ModelAttribute @Validated TestReportForm form,
//			BindingResult bindingResult,
//			Principal principal, Model model) {
//	
//	//入力チェックに引っかかった場合、前の画面に戻る
//		if (bindingResult.hasErrors()) {
//			return getTestReportInsert(form,model);
//		}
//		
//		log.info("[" + principal.getName() + "]受験報告登録データ:" + form.toString());
//		
//		TestReportData data = testReportService.refillToData(form);
//		boolean result = testReportService.insertOne(data);
//		if(result) {
//			model.addAttribute("message","受験報告を登録しました。");
//		}else {
//			model.addAttribute("errorMessage","受験報告の登録に失敗しました。操作をやり直してください。");
//		}
//		
//		return getTestReportList(principal,model);
//	}
//	
//	/**
//	 * 受験報告詳細画面を表示する。
//	 * @param form 登録時の入力チェック用TestReportForm
//	 * @param principal ログイン情報
//	 * @param model
//	 * @return 結果画面 - 受験報告詳細（管理者用）
//	 */
//	@GetMapping("/testreport/detail/{id}")
//	public String getTestReportDetail(@PathVariable("id") String user_id,
//			Principal principal, Model model){
//				
//		//3.ユーザ情報の取得
//		TestReportData data = testReportService.selectOne(user_id);
//		
//		log.info("[" + principal.getName() + "]受験報告詳細");
//		
//		model.addAttribute("testReportData",data);
//		return "testreport/detail";
//	}
//	
//	
//	/**
//	 * 1件分の受験報告情報をデータベースから削除する。
//	 * @param principal ログイン情報
//	 * @param model
//	 * @return 結果画面 - 受験報告一覧
//	 */
//	@PostMapping("/testreport/delete")
//	public String delete(@RequestParam("id") String user_id,
//			Principal principal, Model model){
//				
//		boolean isSuccess = testReportService.deleteOne(user_id);
//		
//		log.info("[" + principal.getName() + "]受験報告削除");
//		if (isSuccess) {
//			log.info("削除成功");
//		}else {
//			log.info("削除失敗");
//		}
//		return getTestReportList(principal,model);
//	}
//	
//	/**
//	 * 1件分の受験報告情報をデータベースから更新する。
//	 * @param principal ログイン情報
//	 * @param model
//	 * @return 結果画面 - 受験報告一覧
//	 */
//	@PostMapping("/testreport/update")
//	public String update(@ModelAttribute @Validated TestReportFormForUpdate form,
//			BindingResult bindingResult,
//			Principal principal, Model model){
//				
//		//入力チェックに引っかかった場合、前の画面に戻る
//		if (bindingResult.hasErrors()) {
//			return getTestReportDetail(principal.getName(),principal,model);
//		}
//		
//		log.info("[" + principal.getName() + "]受験報告情報更新データ:" + form.toString());
//				
//		TestReportData data = testReportService.refillToData(form);
//		boolean result = testReportService.updateOne(data);
//		if(result) {
//			model.addAttribute("message","受験報告情報を更新しました。");
//		}else {
//			model.addAttribute("errorMessage","受験報告情報の更新に失敗しました。操作をやり直してください。");
//		}
//		
//		return getTestReportList(principal,model);
//	}
	
}
