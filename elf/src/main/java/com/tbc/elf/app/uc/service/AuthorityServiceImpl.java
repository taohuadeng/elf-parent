package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.Authority;
import com.tbc.elf.base.service.BaseServiceImpl;
import com.tbc.elf.base.util.SqlBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements AuthorityService {
    @Resource
    private RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public List<String> listAuthorityUrls(String userId) {
        Assert.hasText(userId, "UserId is empty!");

        List<String> roleIds = roleService.listRoleIds(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<String>(0);
        }

        SqlBuilder builder = new SqlBuilder("SELECT auth.source_url FROM t_uc_role_authorities as ra");
        builder.append("LEFT JOIN t_uc_authority as auth ON ra.authority_id=auth.authority_id");
        builder.append("WHERE ra.role_id IN (:roleIds)");
        builder.addParameter("roleIds", roleIds);

        return baseService.queryBySQL(builder, String.class);
    }
}
