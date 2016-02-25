package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.PositionCategory;
import com.tbc.elf.base.service.BaseService;

/**
 * 岗位类别服务接口
 *
 * @author ELF@TEAM
 * @since 2016-2-24
 */
public interface PositionCategoryService extends BaseService<PositionCategory>{

    /**
     * 保存岗位类别信息（自动维护idPath、namePath和showOrder字段）
     *
     * @param positionCategory 岗位类别
     * @return 岗位类别id
     */
    String save(PositionCategory positionCategory);
}
