package com.zhangyan.server.mapper;

import com.zhangyan.server.domain.Coupon;
import com.zhangyan.server.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 流量券mapper组件
 * @author zhangyan
 *
 */
@Mapper
public interface UserMapper {

	/**
	 * @return
	 */
	@Select("SELECT * "
			+ "FROM user ")
	@Results({
		@Result(column = "id", property = "id"),
		@Result(column = "name", property = "name"),
		@Result(column = "age", property = "age"),
	})
	List<User> queryAllUser();
	
	/**
	 * 更新流量券的状态
	 * @param id 流量券id
	 * @param status 流量券状态
	 */
//	@Update("UPDATE coupon SET status=#{status} WHERE id=#{id}")
//	void updateStatus(@Param("id") Long id, @Param("status") Integer status);
	
	/**
	 * 插入一张流量券
	 * @param coupon 流量券
	 */ 
//	@Insert("INSERT INTO coupon("
//			+ "user_account_id,"
//			+ "coupon_amount,"
//			+ "status,"
//			+ "end_time"
//		+ ") VALUES("
//			+ "#{userAccountId},"
//			+ "#{couponAmount},"
//			+ "#{status},"
//			+ "#{endTime}"
//		+ ")")
//	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
//	void insert(Coupon coupon);

	/**
	 * 删除流量券
	 * @param id
	 */
//	@Delete("DELETE FROM coupon WHERE id=#{id}")
//	void delete(@Param("id") Long id);
	
}
