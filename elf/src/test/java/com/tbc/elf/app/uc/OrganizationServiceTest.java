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
import java.util.*;

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
        organization.setOrganizationId("40288111531716fa01531716ffa40000");
        organization.setComments("1");
        organization.setParentId("402881115316cbbe015316cbc3db0000");
        organization.setOrganizationName("123");
        organization.setLastModifyTime(new Date());
        organizationService.updateOrganization(organization);
        //organizationService.addOrganization(organization);

    }

    @Test
    public void testList() {
        Set<String> set1 = new HashSet<String>(1);
        set1.add("402881115316cbbe015316cbc3db0000");
        set1.add("402881115316cc11015316cc171c0000");
        List<Organization> result = organizationService.findOrganization(set1);
        System.out.println(result.size());
    }


}
