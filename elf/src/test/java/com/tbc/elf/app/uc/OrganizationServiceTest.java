package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.Organization;
import com.tbc.elf.app.uc.model.User;
import com.tbc.elf.app.uc.service.OrganizationService;
import com.tbc.elf.app.uc.service.UserService;
import com.tbc.elf.base.BaseTests;
import com.tbc.elf.base.service.HibernateBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 测试测试类
 */
public class OrganizationServiceTest extends BaseTests {
    private Log LOG = LogFactory.getLog(OrganizationServiceTest.class);
    @Resource
    private HibernateBaseService baseService;
    @Resource
    OrganizationService organizationService;

    @Test
    @Rollback(false)
    public void testSave() {
        Organization organization = new Organization();
        organization.setShowOrder(1);
        organization.setNamePath("1");
        organization.setCorpCode("1");
        organization.setNamePath("1");
        organization.setLastModifyBy("1");
        organization.setCreateBy("1");
        organization.setCreateTime(new Date());
        organization.setComments("1");
        organization.setOrganizationName("1");
        organization.setLastModifyTime(new Date());
        organizationService.save(organization);

    }

    @Test
    public void testList() {
        List<Object[]> result = organizationService.getOrganizations();
        for (Object[] myResult : result) {
            System.out.println(myResult[0] + "-----" + myResult[1]);
        }
    }
}
