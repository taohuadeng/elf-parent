package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.User;
import com.tbc.elf.base.service.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {
    private Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String save(User user) {
        Assert.notNull(user, "User is null!");

        return super.save(user);
    }
}
