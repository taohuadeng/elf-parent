package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * 人员信息实体
 */
@Entity
@Table(name = "t_uc_user")
public class User extends BaseModel {

    /**
     * 主键
     */
    @Id
    @Column(nullable = false, length = 32)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    private String userId;

	/**
	 * 姓名
	 */
    @Column(nullable = false, length = 50)
	private String userName;

	/**
	 * 工号
	 */
    @Column(nullable = false, length = 50)
	private String employeeCode;

	/**
	 * 直属部门id
	 */
    @Column(nullable = false, length = 32)
	private String organizationId;

    /**
     * 直属部门 名称
     */
    @Column(nullable = false, length = 50)
    private String organizationName;
	/**
	 * 显示顺序
	 */
    @Column(nullable = false)
	private Double showOrder;

	/**
	 * 岗位id
	 */
    @Column( length = 32)
	private String positionId;

	/**
	 * 岗位
	 */
    @Column( length = 50)
	private String positionName;

	/**
	 * 职级
	 */
    @Column(length = 10)
	private String dutyLevel;

	/**
	 * 职务名称
	 */
    @Column(length = 50)
	private String dutyName;
	/**
	 * 隶属上级ID
	 */
    @Column(length = 32)
	private String superiorId;

    /**
     * 用户状态
     */
    @Column(nullable = false, length = 10)
	private String accountStatus;

    /**
     * 身份证号码
     */
    @Column(length = 20)
    private String idCard;

    /**
     * 手机号码
     */
    @Column( length = 20)
    private String mobile;

    /**
     * 邮箱
     */
    @Column( length = 50)
    private String email;

    /**
     * 帐号过期时间,
     * 为空时表示该公司无学员过期设置
     * 或者该学员不会自动过期,公司admin账户该字段为空
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date expireTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Double getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Double showOrder) {
        this.showOrder = showOrder;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDutyLevel() {
        return dutyLevel;
    }

    public void setDutyLevel(String dutyLevel) {
        this.dutyLevel = dutyLevel;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}
