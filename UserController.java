package jp.co.internous.eagle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.eagle.model.domain.MstUser;
import jp.co.internous.eagle.model.form.UserForm;
import jp.co.internous.eagle.model.mapper.MstUserMapper;
import jp.co.internous.eagle.model.session.LoginSession;

@Controller
@RequestMapping("/eagle/user")
public class UserController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstUserMapper mstuserMapper;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		// page_header.htmlでsessionの変数を表示させているため、loginSessionを画面に送る。
			m.addAttribute("loginSession",loginSession);
		return "register_user";
	}
	
	//ユーザー名（メールアドレス）が重複しているか確認する
	@ResponseBody
	@PostMapping("/duplicatedUserName")
	//Ajax(JSON)によりPOSTで送られてきたユーザー名をUserForm型のformで受け取る
	public String duplicatedUserName(@RequestBody UserForm form) {
		//findCountByUserNameメソッドでユーザー名（メールアドレス）がDB（mst_userテーブル）内の重複の件数を検索し、件数をcountに代入する
		int count = mstuserMapper.findCountByUserName(form.getUserName());
		//toJson()によりregister_user.htmlのajax(result)に値を送る
		return gson.toJson(count);
	}
	
	//DBのマスターテーブル会員情報にregister_user.htmlの入力値を登録する
	@ResponseBody
	@PostMapping("/register")
	public boolean register(@RequestBody UserForm form) {
		//MstUserのインスタンスmを作り、中にregister_user.htmlの入力内容をUserFormを介してgetした値をsetして入れる
		MstUser m = new MstUser();
		m.setUserName(form.getUserName());
		m.setPassword(form.getPassword()); 
		m.setFamilyName(form.getFamilyName());
		m.setFirstName(form.getFirstName());
		m.setFamilyNameKana(form.getFamilyNameKana());
		m.setFirstNameKana(form.getFirstNameKana());
		m.setGender(form.getGender());
		//mに入れた入力内容をMstUserMapperのinsertメソッドで登録する
		int count = mstuserMapper.insert(m);
		//boolean型の判定を返す。
		return count > 0;
	}
}
