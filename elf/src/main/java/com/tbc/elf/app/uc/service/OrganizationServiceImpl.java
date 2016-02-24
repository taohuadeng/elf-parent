package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.Organization;
import com.tbc.elf.base.service.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService {

    public List<Object[]> getOrganizations() {
        List<String> columns = new ArrayList<String>(2);
        columns.add("organizationId");
        columns.add("organizationName");
        return getOrganizationsByColumnsAndOrganization(null, columns, null);
    }

    public Map<String, Organization> getOrgIdAndOrganizeMap(Set<String> orgIds) {
        if (CollectionUtils.isEmpty(orgIds)) {
            throw new IllegalArgumentException("OrgIds is empty!");
        }

        return null;
    }

    private List<Object[]> getOrganizationsByColumnsAndOrganization(List<String> organizationIds
            , List<String> columns, Organization organization) {
        String hql = "select ";
        for (String columnName : columns) {
            hql += columnName + ",";
        }

        hql = hql.substring(0, hql.length() - 1);
        hql += " from Organization";
        List<Object[]> result = baseService.find(hql);
        return result;
    }
}
