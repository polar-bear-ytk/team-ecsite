package jp.co.internous.eagle.model.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.eagle.model.domain.MstUser;

@Mapper
public interface MstUserMapper {
	//UserControllerから呼び出される
	//ユーザー名（メールアドレス）がDBに存在している件数を検索する
	@Select ("SELECT COUNT(user_name) FROM mst_user WHERE user_name=#{userName}")
	int findCountByUserName(@Param("userName")String userName);
	
	//UserControllerから呼び出される
	//mut_userテーブルに各入力内容を登録する
	//Insertされた件数をint型で戻す。（0か1か）
	@Insert("INSERT INTO mst_user (user_name,password,family_name,first_name,family_name_kana,first_name_kana,gender)"+
				 "VALUES (#{userName},#{password},#{familyName},#{firstName},#{familyNameKana},#{firstNameKana},#{gender})")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int insert(MstUser m);
	
	//AuthControllerから呼び出される
	//mst_userテーブルにuserNameとpasswordが一致するレコードを取得し、MstUser型として返す
	@Select("SELECT * FROM mst_user WHERE user_name=#{userName} AND password=#{password}")
	MstUser findByUserNameAndPassword(
					@Param("userName")String userName,
					@Param("password")String password);
	
	//AuthControllerから呼び出される
	@Update("UPDATE mst_user SET password = #{password}, updated_at = now() WHERE user_name = #{userName}")
	int updatePassword(
			@Param("userName") String userName,
			@Param("password") String password);
}