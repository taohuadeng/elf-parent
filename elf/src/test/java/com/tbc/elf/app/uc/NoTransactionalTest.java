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

    /**
     * Role 主控方 Authority 被控方
     * 情况一
     * 主控方不配置 @Cascade 属性 不调用【authorityService.batchSaveOrUpdate(authorities);】
     * 结论 保存都不成功 异常信息
     * <p>
     * org.springframework.dao.InvalidDataAccessApiUsageException:
     * object references an unsaved transient instance - save the transient instance before flushing
     * <p/>
     * This happens because you have a collection in your entity, and that collection has one or more items
     * which are not present in the database. By specifying the above options you tell hibernate to save them
     * to the database when saving their parent.
     * </p>
     * <p/>
     * 情况二
     * 主控方不配置 @Cascade 属性 调用【authorityService.batchSaveOrUpdate(authorities);】
     * 结论 role成功保存 authority成功保存 级联关系成功保存 不报错 维护关系表
     * <p/>
     * 情况三
     * 主控方配置 @Cascade(value = {SAVE_UPDATE}) 属性 不调用【authorityService.batchSaveOrUpdate(authorities);】
     * 结论 role成功保存 authority成功保存 级联关系成功保存 不报错 维护关系表
     * <p/>
     * 情况四
     * 主控方配置 @Cascade(value = {SAVE_UPDATE}) 属性 调用【authorityService.batchSaveOrUpdate(authorities);】
     * 结论 role成功保存 authority成功保存 级联关系成功保存 不报错 维护关系表
     */
    @Test
    public void test级联保存主控() {
        List<Authority> authorities = new ArrayList<Authority>();
        for (int i = 0; i < 10; i++) {
            Authority authority = new Authority();
            authority.setSourceUrl("url-" + i);
            authority.setParentId(".");
            authority.setShowOrder(i + 1);
            authority.setSourceName("name-" + i);
            authority.setAuthorityType(Authority.AuthorityType.SYSTEM);
            authorities.add(authority);
        }

        authorityService.batchSaveOrUpdate(authorities);

        Role role = new Role();
        role.setRoleName("roleName");
        role.setRoleType(Role.RoleType.SYSTEM);
        role.setAuthorities(authorities);
        role.setComments("角色描述");
        roleService.save(role);
    }

    /**
     * Authority 被控方 Role 主控方
     * 情况一
     * 条件 被控方不配置 @Cascade 属性 不调用【roleService.batchSaveOrUpdate(roles);】
     * 结论 authority成功保存  其他都不保存 不报错
     * <p/>
     * 情况二
     * 条件 被控方不配置 @Cascade 属性 调用【roleService.batchSaveOrUpdate(roles);】
     * 结论 role成功保存 authority成功保存 级联关系不保存 不报错
     * <p/>
     * 情况三
     * 条件 被控方配置 @Cascade(value = {SAVE_UPDATE})属性 不调用【roleService.batchSaveOrUpdate(roles);】
     * 结论 role成功保存 authority成功保存 级联关系不保存 不报错 不维护关系表
     * <p/>
     * 情况四
     * 条件 被控方配置 @Cascade(value = {SAVE_UPDATE})属性 调用【roleService.batchSaveOrUpdate(roles);】
     * 结论 role成功保存 authority成功保存 级联关系不保存 不报错 不维护关系表
     */
    @Test
    public void test级联保存被控() {
        List<Role> roles = new ArrayList<Role>();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setRoleName("roleName" + i);
            role.setRoleType(Role.RoleType.SYSTEM);
            role.setComments("角色描述" + i);


            roles.add(role);
        }

        roleService.batchSaveOrUpdate(roles);

        Authority authority = new Authority();
        authority.setSourceUrl("url-");
        authority.setParentId(".");
        authority.setShowOrder(1);
        authority.setSourceName("name-");
        authority.setRoles(roles);
        authority.setAuthorityType(Authority.AuthorityType.SYSTEM);
        authorityService.save(authority);
    }

    /**
     * Role 主控方 Authority 被控方
     * 情况一
     * 主控方不配置fetch属性
     * 结论 不会级联查询
     * <p/>
     * 情况二
     * 主控方配置 @ManyToMany(fetch = FetchType.EAGER) 属性
     * 结论 会级联查询
     * <p/>
     * 情况三
     * 主控方配置 @ManyToMany(fetch = FetchType.LAZY) 属性  默认属性
     * 结论 不会级联查询
     */
    @Test
    public void test级联查询主控() {
        String hql = "FROM Role WHERE corpCode = ?";
        List<Role> roles = roleService.listByHQL(hql, new Object[]{"default"});
        for (Role role : roles) {
            System.out.println(role.getRoleName());
        }
    }

    /**
     * Role 主控方 Authority 被控方
     * 情况一
     * 被控方不配置fetch属性
     * 结论 不会级联查询
     * 情况二
     * 被控方配置fetch属性 @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER) 属性
     * 结论 会级联查询
     * 情况三
     * 被控方配置fetch属性 @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY) 属性 默认属性
     * 结论 不会级联查询
     */
    @Test
    public void test级联查询被控() {
        String hql = "FROM Authority WHERE corpCode = ?";
        List<Authority> authorities = authorityService.listByHQL(hql, new Object[]{"default"});
        for (Authority authority : authorities) {
            System.out.println(authority.getSourceName());
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
