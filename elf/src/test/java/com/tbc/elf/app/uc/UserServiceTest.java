package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.User;
import com.tbc.elf.base.BaseTests;
import com.tbc.elf.base.service.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 测试测试类
 */
public class UserServiceTest extends BaseTests {
    private Log LOG = LogFactory.getLog(UserServiceTest.class);
    @Resource
    private BaseService baseService;

    @Test
    public void testHello() {
        String hql ="from User";
        User user = baseService.getByHQL(hql);
        LOG.info("Hello ELF TESTS!");
    }
}
