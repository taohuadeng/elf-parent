package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.PositionCategory;
import com.tbc.elf.base.service.BaseServiceImpl;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位类别服务的实现
 *
 * @author ELF@TEAM
 * @since 2016-2-24
 */
public class PositionCategoryServiceImpl extends BaseServiceImpl<PositionCategory> implements PositionCategoryService {

    @Override
    @Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED)
    public String save(PositionCategory positionCategory) {
        return null;
    }
}
