package com.tbc.elf.app.uc.model;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 用户岗位信息
 *
 * @author ELF@TEAM
 * @since 2016年2月23日17:03:00
 */
@Entity
@Table(name = "t_uc_position")
public class Position extends BaseModel {

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
    @Column(length = 32)
    private String dutyId;

    /**
     * 岗位序列id
     */
    @Column(length = 32)
    private String positionLineId;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SELECT)
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "position_category_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SELECT)
    private PositionCategory category;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public PositionCategory getCategory() {
        return category;
    }

    public void setCategory(PositionCategory category) {
        this.category = category;
    }
}

