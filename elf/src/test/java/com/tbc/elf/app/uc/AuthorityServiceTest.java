package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.Authority;
import com.tbc.elf.app.uc.service.AuthorityService;
import com.tbc.elf.base.BaseTests;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 系统权限服务测试类
 *
 * @author ELF@TEAM
 * @since 2016年2月23日17:03:00
 */
public class AuthorityServiceTest extends BaseTests {
    private Log LOG = LogFactory.getLog(AuthorityServiceTest.class);

    @Resource
    private AuthorityService authorityService;

    @Test
    public void test() {
        Authority authority = new Authority();
        authority.setParentId("297ea177531873b801531873c21f0000");
        authority.setSourceUrl("/app/uc/user/*");
        authority.setSourceName("人员管理");
        String authorityId = authorityService.save(authority);
        LOG.info(authorityId);
        Assert.assertNotNull(authorityId);

        Authority auth = authorityService.get(authorityId);
        Assert.assertNotNull(auth);
        Assert.assertEquals("人员管理", auth.getSourceName());
        Assert.assertEquals("/app/uc/user/*", auth.getSourceUrl());

        String hql = "FROM Authority WHERE corpCode = ?";
        List<Authority> authorities = authorityService.listByHQL(hql, new Object[]{"default"});
        Assert.assertNotNull(authorities);
    }

    @Test
    public void testGetAuthorities(){
        List<String> list = authorityService.listAuthorityUrls("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        Assert.assertNotNull(list);
        for (String s : list) {
            LOG.info(s);
        }
    }
}
