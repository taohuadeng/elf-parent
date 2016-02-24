package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.User;
import com.tbc.elf.app.uc.service.UserService;
import com.tbc.elf.base.BaseTests;
import com.tbc.elf.base.service.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 测试测试类
 */
public class UserServiceTest extends BaseTests {
    private Log LOG = LogFactory.getLog(UserServiceTest.class);
    @Resource
    private BaseService baseService;
    @Resource
    private UserService userService;

    @Test
    public void testHello() {
        String hql ="from User";
        User user = baseService.getByHQL(hql);
        LOG.info("Hello ELF TESTS!");
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("test");
        user.setSex(User.SexType.MALE);
        user.setEmployeeCode("001");
        user.setOrganizationId("");
        user.setOrganizationName("");
        user.setAccountStatus(User.AccountStatus.ENABLE);
        user.setCorpCode("default");
        user.setCreateBy("sdf");
        user.setCreateTime(new Date());
        user.setLastModifyBy("sdf");
        user.setLastModifyTime(new Date());
        userService.save(user);
    }
}
