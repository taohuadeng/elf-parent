package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.Login;
import com.tbc.elf.base.service.BaseService;

/**
 * 人员信息的业务逻辑操作
 *
 * @author ELF@TEAM
 * @since 2016年2月24日16:52:39
 */
public interface LoginService extends BaseService<Login> {

    /**
     * 该方法用于根据公司编号和登录名获取对象
     *
     * @param loginName 登录名
     * @param corpCode  公司编号
     * @return 登录密码
     */
    String getPasswordByLoginAndCorpCode(String loginName, String corpCode);
}
