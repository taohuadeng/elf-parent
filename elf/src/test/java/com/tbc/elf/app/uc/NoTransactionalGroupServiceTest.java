package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.Authority;
import com.tbc.elf.app.uc.model.Group;
import com.tbc.elf.app.uc.model.GroupCategory;
import com.tbc.elf.app.uc.model.Role;
import com.tbc.elf.app.uc.service.AuthorityService;
import com.tbc.elf.app.uc.service.GroupCategoryService;
import com.tbc.elf.app.uc.service.GroupService;
import com.tbc.elf.app.uc.service.RoleService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class NoTransactionalGroupServiceTest {
    private static GroupService groupService;
    private static GroupCategoryService categoryService;

    @BeforeClass
    public static void init() {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("classpath:/spring-config/spring-servlet.xml",
                "classpath:/spring-config/spring-context.xml");
        groupService = (GroupService) context.getBean("groupService");
        categoryService = (GroupCategoryService) context.getBean("groupCategoryService");
    }

    /**
     * Group 单向关联 GroupCategory
     * 情况一
     * 主控方不配置 @Cascade 属性 不调用【categoryService.save(category);】
     * 结论 保存都不成功 异常信息
     * <p/>
     * org.springframework.dao.InvalidDataAccessApiUsageException:
     * object references an unsaved transient instance - save the transient instance before flushing
     * <p/>
     * 情况二
     * 主控方不配置 @Cascade 属性 调用【categoryService.save(category);】
     * 结论 保存成功
     * <p/>
     * 情况三
     * 主控方配置 @Cascade(value = CascadeType.SAVE_UPDATE) 属性 不调用【categoryService.save(category);】
     * 结论 保存成功
     * <p/>
     * 情况四
     * 主控方配置 @Cascade(value = CascadeType.SAVE_UPDATE) 属性 调用【categoryService.save(category);】
     * 结论 保存成功
     */
    @Test
    public void test级联保存单向多对一() {
        GroupCategory category = new GroupCategory();
        category.setParentId(".");
        category.setGroupCategoryName("群组类别一");
        category.setIdPath(".");
        category.setNamePath(".群组类别一");
        category.setShowOrder(1.0);
        //categoryService.save(category);

        Group group = new Group();
        group.setGroupName("群组名称");
        group.setCategory(category);
        group.setOwner("陶发登");
        group.setStatus(Group.GroupStatus.ACTIVE);
        group.setValidateType(Group.ValidateType.APPOINT);
        groupService.save(group);
    }

    /**
     * Group 单向关联 GroupCategory
     * 情况一
     * 条件 不配置fetch属性
     * 结论 会级联查询
     * 情况二
     * 条件 配置fetch属性 @ManyToOne(fetch = FetchType.EAGER) 属性 <h1>默认属性</h1>
     * 结论 会级联查询
     * 情况三
     * 条件 配置fetch属性 @ManyToOne(fetch = FetchType.LAZY) 属性
     * 结论 不会级联查询
     */
    @Test
    public void test级联修改单向多对一() {
        String hql = "FROM Group WHERE corpCode = ?";
        List<Group> groups = groupService.listByHQL(hql, new Object[]{"default"});
        for (Group group : groups) {
            System.out.println(group.getGroupName());
        }
    }

    /**
     * Group 单向关联 GroupCategory
     * GroupCategory删除会报错
     */
    @Test
    public void test级联删除单向多对一() {
        String hql = "FROM GroupCategory WHERE corpCode = ?";
        List<GroupCategory> categories = categoryService.listByHQL(hql, new Object[]{"default"});
        for (GroupCategory category : categories) {
            categoryService.delete(category.getGroupCategoryId());
        }
    }
}
