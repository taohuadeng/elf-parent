package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2016/2/23.
 */
public class Auth extends BaseModel {

    /**
     * 主键
     */
    @Id
    @Column(nullable = false, length = 32)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    private String authId;

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
     * 类型
     */

    @Column(nullable = false, length = 20)
    private String type;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
