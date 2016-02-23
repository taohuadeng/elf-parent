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
@Table(name="t_uc_group_category")
public class GroupCategory extends BaseModel{

     /**
     * 主键
     */
    @Id
    @Column(nullable = false, length = 32)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	private String groupCategoryId;

	/**
	 * 分类名称
	 */
    @Column(nullable = false, length = 32)
	private String groupCategoryName;

	/**
	 * 类别全路径，方便查询
	 */
    @Column(nullable = false, length = 200)
	private String idPath;

	/**
	 * 类别路径名
	 */
    @Column(nullable = false, length = 200)
	private String namePath;

	/**
	 * 上级分类id
	 */
    @Column(nullable = false, length = 32)
	private String parentId;

	/**
	 * 排序号
	 */
    @Column(nullable = false)
	private Double showOrder;
}
