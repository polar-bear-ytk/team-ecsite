package jp.co.internous.eagle.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.eagle.model.domain.MstDestination;

@Mapper
public interface MstDestinationMapper {
	
	//DestinationControllerから呼び出される
	//mst_destinationテーブルに各入力内容を登録する
	@Insert ("INSERT INTO mst_destination (user_id, family_name, first_name, tel_number, address) "+
			 "VALUES ( #{userId}, #{familyName}, #{firstName}, #{telNumber}, #{address})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstDestination destination);
	
	//SettlementControllerから呼び出される
	//mst_destinationテーブルの#{userId}に紐づくレコードを取得し、ORDER BY id ASCによって、idの昇順でList<MstDestination>型の値を返す（ASC==昇順（defaultで昇順）、DESC==降順）
	@Select("SELECT * FROM mst_destination WHERE user_id = #{userId} AND status = 1 ORDER BY id ASC")
	List<MstDestination> findByUserId(@Param("userId") int userId);
	
	//DestinationControllerから呼び出される
	//@Updateにより、mst_destinationテーブルの#{id}に紐づくstatusを0に上書きする。(テーブル議定書よりstatusのdefault値は1(0==無効))
	@Update("UPDATE mst_destination SET status = 0, updated_at = now() WHERE id = #{id}")
	int logicalDeleteById(@Param("id") int id);
}
