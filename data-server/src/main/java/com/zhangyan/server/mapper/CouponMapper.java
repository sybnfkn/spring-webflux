package com.zhangyan.server.mapper;

import com.zhangyan.server.domain.Coupon;
import org.apache.ibatis.annotations.*;

/**
 * 流量券mapper组件
 * @author zhangyan
 *
 */
@Mapper
public interface CouponMapper {

	/**
	 * 查询用户账号的面额最高的一张流量券
	 * @param userAccountId 用户账号id
	 * @return 流量券
	 */
	@Select("SELECT * "
			+ "FROM coupon "
			+ "WHERE user_account_id=#{userAccountId} "
			+ "AND status=1 "
			+ "ORDER BY coupon_amount DESC "
			+ "LIMIT 1")
	@Results({
		@Result(column = "user_account_id", property = "userAccountId"),  
		@Result(column = "coupon_amount", property = "couponAmount"),
		@Result(column = "start_time", property = "startTime"),
		@Result(column = "end_time", property = "endTime"),
		@Result(column = "created_time", property = "createdTime"),
		@Result(column = "modified_time", property = "modifiedTime")
	})
	Coupon queryByUserAccountId(
			@Param("userAccountId") Long userAccountId);
	
	/**
	 * 更新流量券的状态
	 * @param id 流量券id
	 * @param status 流量券状态
	 */
	@Update("UPDATE coupon SET status=#{status} WHERE id=#{id}")  
	void updateStatus(@Param("id") Long id, @Param("status") Integer status);
	
	/**
	 * 插入一张流量券
	 * @param coupon 流量券
	 */ 
	@Insert("INSERT INTO coupon("
			+ "user_account_id,"
			+ "coupon_amount,"
			+ "status,"
			+ "end_time"
		+ ") VALUES("
			+ "#{userAccountId},"
			+ "#{couponAmount},"
			+ "#{status},"
			+ "#{endTime}"
		+ ")")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Coupon coupon);

	/**
	 * 删除流量券
	 * @param id
	 */
	@Delete("DELETE FROM coupon WHERE id=#{id}")
	void delete(@Param("id") Long id);
	
}
