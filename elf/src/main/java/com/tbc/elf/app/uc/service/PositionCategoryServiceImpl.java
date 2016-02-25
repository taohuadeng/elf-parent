package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.PositionCategory;
import com.tbc.elf.base.service.BaseServiceImpl;
import com.tbc.elf.base.util.ElfConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
        Assert.notNull(positionCategory,"PositionCategory is null !");

        String parentId = positionCategory.getParentId();
        if (StringUtils.isEmpty(parentId)) {
            positionCategory.setParentId(ElfConstant.ROOT_PARENT_ID);
            positionCategory.setNamePath(positionCategory.getCategoryName());
        } else {
            PositionCategory parent = super.load(parentId);
            positionCategory.setNamePath(parent.getNamePath() + ElfConstant.DOT + positionCategory.getCategoryName());
        }


        return super.save(positionCategory);
    }

    private double getMaxShowOrder(String parentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select max(showOrder) from PositionCategory where corpCode = ?");
        return 0d;
    }
}
