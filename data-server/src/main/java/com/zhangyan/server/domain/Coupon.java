package com.zhangyan.server.domain;

import java.util.Date;

/**
 * 流量券
 * @author zhangyan
 *
 */
public class Coupon {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 流量券所属的用户的账号id
	 */
	private Long userAccountId;
	/**
	 * 流量券金额
	 */
	private Double couponAmount;
	/**
	 * 流量券的状态
	 */
	private Integer status;
	/**
	 * 流量券的开始生效时间
	 */
	private Date startTime;
	/**
	 * 流量券的终止过期时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public Double getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
}
