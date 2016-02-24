package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.Organization;
import com.tbc.elf.base.service.BaseService;

import java.util.List;

/**
 * 人员所属部门的业务逻辑操作
 * @author ELF@TEAM
 * @since 2016/2/24  13:40
 */
public interface OrganizationService extends BaseService<Organization>{
   List<Object[]> getOrganizations();
}
