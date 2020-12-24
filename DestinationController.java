package jp.co.internous.eagle.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.eagle.model.domain.MstDestination;
import jp.co.internous.eagle.model.domain.MstUser;
import jp.co.internous.eagle.model.form.DestinationForm;
import jp.co.internous.eagle.model.mapper.MstDestinationMapper;
import jp.co.internous.eagle.model.mapper.MstUserMapper;
import jp.co.internous.eagle.model.session.LoginSession;




@Controller
@RequestMapping("/eagle/destination")
public class DestinationController {
		
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstUserMapper mstuserMapper;
	
	@Autowired
	private MstDestinationMapper mstdestinationMapper;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		MstUser user = mstuserMapper.findByUserNameAndPassword(loginSession.getUserName(),loginSession.getPassword());
		
		m.addAttribute("user", user);
		// page_header.htmlでsessionの変数を表示させているため、loginSessionも画面に送る。
		m.addAttribute("loginSession",loginSession);
		return "destination";
	}
	
	//@SuppressWarnings("unchecked")により、ProblemsのWarningsに表示されているモノのコンパイル時に出てくる警告を抑制する
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	@ResponseBody
	//settlement.htmlからAjax(Json)により呼び出される
	public boolean delete(@RequestBody String destinationId) {
		//fromJsonにより、JSONからJavaオブジェクト型に変換し、Map型のインスタンスmapを作成し、Map型mapに代入（Map<キー,値>オブジェクト名）
		Map<String, String> map = gson.fromJson(destinationId, Map.class);
		String id = map.get("destinationId");
		//integer.parseInt(id)により、String型の文字列数字をint型の整数に変える
		int result = mstdestinationMapper.logicalDeleteById(Integer.parseInt(id));

		return result > 0;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	//destination.htmlのjavascriptによって、入力内容をdialogConfig.jsを介し、dialog_input_user_confirm.htmlに登録内容を表示し、登録ボタンが押された時、dialogConfig.jsのAjaxで呼び出される。
	public String register(@RequestBody DestinationForm form) {
		// 宛先を登録
		MstDestination destination = new MstDestination(form);
		int userId =  loginSession.getUserId();
		destination.setUserId(userId);
		int count = mstdestinationMapper.insert(destination);
		
		// 登録した宛先のIDを取得
		Integer id = 0;
		if (count > 0) {
			id = destination.getId();
		}
		return id.toString();
	}
}
