package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限表
 *
 * @author ELF@TEAM
 * @since 2016年2月23日17:03:00
 */
@Entity
@Table(name = "t_uc_authority")
public class Authority extends BaseModel {
    /**
     * 角色类型
     */
    public enum AuthorityType {
        SYSTEM("系统角色"), CUSTOM("自定义角色");
        private final String text;

        private AuthorityType(String text) {
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
    private String authorityId;

    /**
     * 资源URL
     */
    @Column(nullable = false, length = 200)
    private String sourceUrl;

    /**
     * 资源名称
     */
    @Column(nullable = false, length = 200)
    private String sourceName;

    /**
     * 排序
     */
    @Column(nullable = false)
    private double showOrder;

    /**
     * 权限类型
     */
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;

    @ManyToOne
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Authority parentAuthority;

    @OneToMany(mappedBy = "parentAuthority")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private List<Authority> childAuthorities = new ArrayList<Authority>();

    @ManyToMany(mappedBy = "authorities")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    public List<Role> roles = new ArrayList<Role>();

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public double getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(double showOrder) {
        this.showOrder = showOrder;
    }

    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public Authority getParentAuthority() {
        return parentAuthority;
    }

    public void setParentAuthority(Authority parentAuthority) {
        this.parentAuthority = parentAuthority;
    }

    public List<Authority> getChildAuthorities() {
        return childAuthorities;
    }

    public void setChildAuthorities(List<Authority> childAuthorities) {
        this.childAuthorities = childAuthorities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
