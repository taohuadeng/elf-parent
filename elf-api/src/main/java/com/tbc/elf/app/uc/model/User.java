package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统权限表
 *
 * @author ELF@TEAM
 * @since 2016年2月23日17:03:00
 */
@Entity
@Table(name = "t_uc_user")
public class User extends BaseModel {
    /**
     * 用户性别类型
     */
    public enum SexType {
        MALE("男"), FEMALE("女"), SECRECY("保密");
        private final String text;

        private SexType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

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
     * 角色类型
     */
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private SexType sex;

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
    private double showOrder;

    @ManyToOne
    @JoinColumn(name = "position_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Position position;

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
     * 帐号过期时间,
     * 为空时表示该公司无学员过期设置
     * 或者该学员不会自动过期,公司admin账户该字段为空
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;

    /**
     * 身份证号码
     */
    @Column(length = 20)
    private String idCard;

    /**
     * 手机号码
     */
    @Column(length = 20)
    private String mobile;

    /**
     * 邮箱
     */
    @Column(length = 50)
    private String email;

    /**
     * 角色List
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SELECT)
    public List<Role> roles = new ArrayList<Role>();

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

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
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

    public double getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(double showOrder) {
        this.showOrder = showOrder;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
