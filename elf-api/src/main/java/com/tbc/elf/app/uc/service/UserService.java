package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.User;

/**
 * 人员信息的业务逻辑操作
 *
 * @author ELF@TEAM
 * @since 2016年2月23日17:03:00
 */
public interface UserService {

    /**
     * 该方法用于人员的新增操作
     *
     * @return 用户主键
     * @since 2016年2月24日09:32:00
     */
    String save(User user);
}
