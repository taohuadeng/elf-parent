package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.Authority;
import com.tbc.elf.app.uc.service.RoleService;
import com.tbc.elf.base.BaseTests;
import com.tbc.elf.base.service.HibernateBaseService;
import com.tbc.elf.base.util.UUIDGenerator;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleServiceTest extends BaseTests {
    @Resource
    private HibernateBaseService baseService;
    @Resource
    private RoleService roleService;

    @Test
    @Rollback(false)
    public void test() {
        long cu = System.currentTimeMillis();
        System.out.println(cu);
        List<Authority> authorities = new ArrayList<Authority>();
        for (int i = 0; i < 500000; i++) {
            String uuid = UUIDGenerator.uuid();
            Authority authority = new Authority();
            authority.setSourceUrl("url-" + i);
            authority.setSourceName("name-" + i);
            authority.setShowOrder(i + 1);
            authority.setCorpCode("default");
            authority.setCreateBy(uuid);
            authority.setAuthorityType(Authority.AuthorityType.SYSTEM);
            authority.setLastModifyBy(uuid);
            authority.setCreateTime(new Date());
            authority.setLastModifyTime(new Date());
            authorities.add(authority);
            //baseService.save(authority);
        }

        baseService.bathSave(authorities);
        System.out.println("costs:" + (System.currentTimeMillis() - cu));

//        String hql = "from Role role,Authority auth where role.roleId = '1234'";
//        List list = baseService.find(hql, null);
    }

    @Test
    public void get() {
        long cu = System.currentTimeMillis();
        System.out.println(cu);

        System.out.println("costs:" + (System.currentTimeMillis() - cu));

        String hql = "from Authority";
        List list = baseService.find(hql, null);
        System.out.println("costs:" + (System.currentTimeMillis() - cu));
    }
}
