package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.Authority;
import com.tbc.elf.app.uc.model.Role;
import com.tbc.elf.app.uc.service.AuthorityService;
import com.tbc.elf.app.uc.service.RoleService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class NoTransactionalTest {
    private static AuthorityService authorityService;
    private static RoleService roleService;

    @BeforeClass
    public static void init() {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("classpath:/spring-config/spring-servlet.xml",
                "classpath:/spring-config/spring-context.xml");
        authorityService = (AuthorityService) context.getBean("authorityService");
        roleService = (RoleService) context.getBean("roleService");
    }

    @Test
    public void testGet() {
        String hql = "FROM Authority WHERE corpCode = ?";
        List<Authority> authorities = authorityService.listByHQL(hql, new Object[]{"default"});
        for (Authority authority : authorities) {
            authority.setSourceName(authority.getSourceName() + "hello2");
            List<Role> roles = authority.getRoles();
            for (Role role : roles) {
                role.setRoleName(role.getRoleName() + "hello");
            }
        }

        String hql2 = "FROM Role WHERE corpCode = ?";
        List<Role> roles = roleService.listByHQL(hql2, new Object[]{"default"});
        for (Role role : roles) {
            role.setRoleName(role.getRoleName() + ":role-name2");
            List<Authority> authorityList = role.getAuthorities();
            for (Authority authority : authorityList) {
                System.out.println(authority.getSourceName());
            }
        }
    }

    @Test
    public void test级联更新() {
        String hql = "FROM Authority WHERE corpCode = ?";
        List<Authority> authorities = authorityService.listByHQL(hql, new Object[]{"default"});
        for (Authority authority : authorities) {
            authority.setSourceName(authority.getSourceName() + "hello");
            List<Role> roles = authority.getRoles();
            for (int i = 0; i < roles.size(); i++) {
                Role role = roles.get(i);
                role.setRoleName("hello234" + i);
            }
        }

        authorityService.saveOrUpdate(authorities);
    }

    @Test
    public void test删除1() {
        String hql = "FROM Authority WHERE corpCode = ?";
        List<Authority> authorities = authorityService.listByHQL(hql, new Object[]{"default"});
        List<String> authorityIds = new ArrayList<String>(authorities.size());
        for (Authority authority : authorities) {
            authorityIds.add(authority.getAuthorityId());
        }

        authorityService.delete(authorityIds);
    }

    @Test
    public void test删除2() {
        String hql = "FROM Role WHERE corpCode = ?";
        List<Role> authorities = roleService.listByHQL(hql, new Object[]{"default"});
        List<String> authorityIds = new ArrayList<String>(authorities.size());
        for (Role authority : authorities) {
            authorityIds.add(authority.getRoleId());
        }

        roleService.delete(authorityIds);
    }
}
