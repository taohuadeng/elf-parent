package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/2/23.
 */
@Table(name="t_uc_position")
public class Position extends BaseModel{

    /**
     * 主键
     */
    @Id
    @Column(nullable = false, length = 32)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    private String positionId;

    /**
     * 岗位名称
     */
    @Column(nullable = false, length = 50)
    private String positionName;

    /**
     * 排序
     */
    @Column(nullable = false)
    private double showOrder;

    /**
     * 职务体系id
     */
    @Column(length = 32)
    private String dutyArchitectureId;
    /**
     * 职务id
     */
    @Column( length = 32)
    private String dutyId;
    /**
     * 岗位序列id
     */
    @Column( length = 32)
    private String positionLineId;
    /**
     * 岗位类别id
     */
    @Column(nullable = false, length = 32)
    private String positionCategoryId;

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

    public double getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(double showOrder) {
        this.showOrder = showOrder;
    }

    public String getDutyArchitectureId() {
        return dutyArchitectureId;
    }

    public void setDutyArchitectureId(String dutyArchitectureId) {
        this.dutyArchitectureId = dutyArchitectureId;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public String getPositionLineId() {
        return positionLineId;
    }

    public void setPositionLineId(String positionLineId) {
        this.positionLineId = positionLineId;
    }

    public String getPositionCategoryId() {
        return positionCategoryId;
    }

    public void setPositionCategoryId(String positionCategoryId) {
        this.positionCategoryId = positionCategoryId;
    }
}

