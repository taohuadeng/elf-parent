package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 系统角色表
 *
 * @author TaoFaDeng@ELF@TEAM
 * @since 2016年2月23日17:03:00
 */
@Entity
@Table(name = "t_uc_role",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"corpCode", "roleName"})})
public class Role extends BaseModel {
    /**
     * 角色类型
     */
    public enum RoleType {
        SYSTEM("系统角色"), CUSTOM("自定义角色");
        private final String text;

        private RoleType(String text) {
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
    private String roleId;

    /**
     * 角色名称
     */
    @Column(nullable = false, length = 50)
    private String roleName;

    /**
     * 角色描述
     */
    @Column(nullable = false, length = 50)
    private String comments;

    /**
     * 角色类型
     */
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
