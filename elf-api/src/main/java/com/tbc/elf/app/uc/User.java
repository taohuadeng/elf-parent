package com.tbc.elf.app.uc;

import com.tbc.elf.base.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 16-2-23.
 */
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
    @Column(nullable = false)
    private String userName;

    /**
     * 工号
     */
    @Column(nullable = false)
    private String employeeCode;

    /**
     * 直属部门id
     */
    private String organizeId;

    /**
     * 显示顺序
     */
    private Double showOrder;

    /**
     * 职务名称
     */
    private String dutyName;

    /**
     * 隶属上级ID
     */
    private String superiorId;
    /**
     * 隶属上级名称
     */
    private String superiorName;

    private String superiorEmployeeCode;
    /**
     * 职级
     */
    private String dutyLevel;
    /**
     * 旧职级
     */
    private String dutyLevelOld;
    /**
     * 岗位
     */
    private String positionName;
    /**
     * 最后修改人
     */
    private String lastModifyBy;
    /**
     * 部门名称
     */
    private String organizeName;
    /**
     * 岗位id
     */
    private String positionId;
    /**
     * 帐号过期时间,
     * 为空时表示该公司无学员过期设置
     * 或者该学员不会自动过期,公司admin账户该字段为空
     */
    private Date expireTime;

    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 学员角色ID (不做持久化)
     */
    private String userRoleId;
    /**
     * 学员角色名称 (不做持久化)
     */
    private String userRoleName;
    /**
     * 群组ID（不做持久化   作为查询条件）
     */
    private String groupId;
    /**
     * 群组Ids
     */
    private String groupIds;
    private String groupIdsOld;
    /**
     * 群组Names
     */
    private String groupNames;
    private String categoryNames;
    /**
     * 扩展字段
     */
    private Map<String, Object> extMap;

    /**
     * 部门名称PATH
     */
    private String organizeNamePath;

    /**
     * 和organizeId同效（冗余属性 findUserBySearch中调用）
     */
    private String orgId;

    /**
     * 是否按当前人员的授权部门进行查询
     */
    private Boolean isAuthorization;
    /**
     * 是否查询管理员角色的人员
     */
    private Boolean returnAdmin;
    /**
     * 学员所属组织编号，不做持久化
     */
    private String organizeCode;
    /**
     * 所属岗位编号，不做持久化
     */
    private String positionCode;
    /**
     * 岗位path
     */
    private String positionNamePath;


    private String creatUserTime;

    private String optType;
    //修改导入用到（不持久化）
    private String accountStatus;
    //导入用到（不持久化）
    private String passwordTemp;

    //private GroupJoinReq groupJoinReq;

    private String loginName;
    private String idCard;
    private String nickName;
    private Date joinGroupTime;
    private Date joinGroupTimeTo;

}
