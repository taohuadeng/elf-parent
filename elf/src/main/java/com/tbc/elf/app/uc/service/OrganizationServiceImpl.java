package com.tbc.elf.app.uc.service;

import com.tbc.elf.app.uc.model.Organization;
import com.tbc.elf.base.service.BaseServiceImpl;
import com.tbc.elf.base.util.ElfConstant;
import com.tbc.elf.base.util.HqlBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    @Override
    public String initOrganization(String corpCode, String lastResultStatus) {
        return null;
    }

    @Override
    public String addOrganization(Organization organization) {
        Assert.notNull(organization, "Organization must be not null");

        String pNamePath = "";
        if (StringUtils.isNotBlank(organization.getParentId())) {
            Organization parentOrg = get(organization.getParentId());
            if (parentOrg != null) {
                pNamePath = parentOrg.getNamePath();
            }

        }

        if (StringUtils.isNotEmpty(pNamePath)) {
            organization.setNamePath(pNamePath + "." + organization.getOrganizationName());
        } else {
            organization.setNamePath(organization.getOrganizationName());
        }

        Double maxShowOrder = getMaxShowOrderByParentId(organization.getParentId());
        double showOrder = maxShowOrder == null ? 1 : maxShowOrder + 1;
        organization.setShowOrder(showOrder);
        return save(organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        Assert.notNull(organization, "Organization must be not null");
        String organizationId = organization.getOrganizationId();
        Assert.hasText(organizationId, "OrganizationId must be not Blank");
        Organization oldOrg = get(organizationId);
        String organizationName = organization.getOrganizationName();
        if (!oldOrg.getOrganizationName().equals(organizationName) && StringUtils.isNotBlank(organizationName)) {
            Organization parentOrg = get(oldOrg.getParentId());
            HqlBuilder hqlBuilder = new HqlBuilder("from Organization where parentId =:parentId");
            hqlBuilder.addParameter("parentId", organizationId);
            String namePath = parentOrg.getNamePath() + ElfConstant.DOT + organizationName;
            List<Organization> childrenOrg = baseService.queryList(hqlBuilder);
            for (Organization organization1 : childrenOrg) {
                organization1.setNamePath(organization1.getNamePath().replace(oldOrg.getNamePath(), ""));
                organization1.setNamePath(oldOrg.getNamePath() + organization1.getNamePath());
                baseService.saveOrUpdate(organization1);
            }
            oldOrg.setNamePath(namePath);
            childrenOrg.add(organization);
            baseService.saveOrUpdate(oldOrg);
        }

    }

    public Map<String, Organization> getOrgIdAndOrganizeMap(Set<String> orgIds) {
        Assert.notEmpty(orgIds, "OrgIds must be not empty");

        List<String> columns = new ArrayList<String>(2);
        columns.add("organizationId");
        columns.add("namePath");
        List<Object[]> resultList = getOrganizationsByColumnsAndOrganization(new ArrayList<String>(orgIds), columns, null);
        if (CollectionUtils.isEmpty(resultList)) {
            return new HashMap<String, Organization>(0);
        }

        Map<String, Organization> resultMap = new HashMap<String, Organization>(resultList.size());
        for (Object[] result : resultList) {
            Organization organization = new Organization();
            organization.setNamePath((String) result[1]);
            organization.setOrganizationId((String) result[0]);
            resultMap.put(organization.getOrganizationId(), organization);
        }

        return resultMap;
    }

    @Override
    public List<Organization> findOrganization(Set<String> organizationIds) {
        Assert.notEmpty(organizationIds, "OrganizationIds must not be empty!");
        List<String> columns = new ArrayList<String>(2);
        columns.add("organizationId");
        columns.add("organizationName");
        columns.add("namePath");
        List<Object[]> resultList =
                getOrganizationsByColumnsAndOrganization(new ArrayList<String>(organizationIds), columns, null);
        if (CollectionUtils.isEmpty(resultList)) {
            return new ArrayList<Organization>(0);
        }

        List<Organization> organizations = new ArrayList<Organization>(resultList.size());
        for (Object[] result : resultList) {
            Organization organization = new Organization();
            organization.setNamePath((String) result[1]);
            organization.setOrganizationId((String) result[0]);
            organizations.add(organization);
        }
        return organizations;
    }

    public String findOrgNodeJson(String orgName, Integer limitOrgNum) {

        return null;
    }

    @Override
    public List<Organization> fillOrgNameByOrgId(List<Organization> orgList) {
        return null;
    }

    @Override
    public String findOrgTreeJson(String orgId, Boolean hasOrgCode) {
        return null;
    }

    @Override
    public String findRootOrgTreeJson(String orgId, Boolean hasOrgCode) {
        return null;
    }

    @Override
    public String getAvailableOrgTreeJson(String currentRootOrgId, String currentOrgId) {
        return null;
    }

    @Override
    public String findOrganizationTreeNew(String root, boolean hasOrgCode, boolean hasNamePath) {
        return null;
    }

    @Override
    public Organization findOrganizationByCode(String OrganizationCode) {
        return null;
    }

    @Override
    public Map<String, String> checkOrganization(Organization Organization) {
        return null;
    }

    @Override
    public List<String> checkOrganizationName(Organization Organization) {
        return null;
    }

    @Override
    public String findOrganizationNodeByUser(String userId) {
        return null;
    }

    @Override
    public String getAvailableParentOrgTree(String currentRootOrgId, String currentOrgId) {
        return null;
    }

    @Override
    public void moveOrganization(String organizationId, Boolean isUp) {
       /* Organization curOrg = get(organizationId);
        MdlBuilder builder = new MdlBuilder();
		builder.append("select a.organizeId from Organize a where a.corpCode = ? ");
		builder.addParameter(organize.getCorpCode());
		builder.append("and a.parentId = ? ");
		builder.addParameter(organize.getParentId());

		if(isUp){
			builder.append("and a.showOrder < ? ");
			builder.addParameter(organize.getShowOrder());
			builder.append(" order by a.showOrder desc ");
		}else{
			builder.append("and a.showOrder > ? ");
			builder.addParameter(organize.getShowOrder());
			builder.append(" order by a.showOrder ");
		}

		List<Organize> ids = dataService.getEntityList(builder);
		if(CollectionUtils.isNotEmpty(ids)){
			return ids.get(0).getOrganizeId();
		}*/

    }

    @Override
    public Integer getAvailableCountByOrg(Organization Organization, String removeOrgId) {
        return null;
    }

    @Override
    public Long getPersonLimitCountForSecondLevel() {
        return null;
    }

    @Override
    public Integer getPersonCountForSecondLevel() {
        return null;
    }

    @Override
    public Integer findAvailableCountForSecond() {
        return null;
    }

    @Override
    public boolean judgeIsSecondLevel(String OrganizationId) {
        return false;
    }

    @Override
    public Long getCurrentUserCountByOrganizationIdWithChild(String OrganizationId) {
        return null;
    }

    @Override
    public List<String> getChildIdsByOrganizationId(String OrganizationId) {
        return null;
    }

    @Override
    public List<String> getChildIdsByOrganizationdId(List<String> OrganizationIdList) {
        return null;
    }

    @Override
    public List<String> getParentIdsByOrganizationId(String OrganizationId) {
        return null;
    }

    @Override
    public String findCorpRootId() {
        return null;
    }

    @Override
    public String addOrganizationForUpgrade(Organization Organization) {
        return null;
    }

    @Override
    public boolean checkOrganizationIsExists(String OrganizationId) {
        return false;
    }

    @Override
    public void updateOrgNameByCorp(String corpCode, String corpName) {

    }

    @Override
    public List<String> checkOrganizationCode(Organization Organization) {
        return null;
    }

    @Override
    public Map<String, Organization> getOrganizationByCorpCode(String corpCode) {
        return null;
    }

    @Override
    public Map<String, Organization> findOrganizationByIds(List<String> orgIds) {
        return null;
    }

    @Override
    public List<Organization> findOrganizationByNamePath(String namePath) {
        return null;
    }

    @Override
    public Map<String, String> findOrganizationIdsByNamePath(List<String> namePath) {
        return null;
    }

    @Override
    public Map<String, String> batchSyncOrganization(List<Organization> Organizations) {
        return null;
    }

    @Override
    public String syncOrganization(Organization Organization) {
        return null;
    }

    @Override
    public void deleteOrgByNoChild(String corpCode) {

    }

    @Override
    public String getSameNameBrotherId(String parentId, String orgName) {
        return null;
    }

    @Override
    public List<String> findOnlyThreeLevelOrgIds() {
        return null;
    }

    @Override
    public List<Organization> getOrganizationsByLevel(Integer level) {
        return null;
    }

    @Override
    public List<Organization> getOrganizationsByChargeUserId(String chargeUserId) {
        return null;
    }

    @Override
    public Organization getOrganizationByIdAndLevel(String OrganizationId, Integer level) {
        return null;
    }

    @Override
    public List<String> getOrganizationFirstChildrenById(String OrganizationId) {
        return null;
    }

    @Override
    public Organization findRootOrgByCorpCode(String corpCode) {
        return null;
    }

    @Override
    public Long getUserByOrganizationId(String OrganizationId, boolean hasChild) {
        return null;
    }

    @Override
    public void calculateUserCount(String OrganizationId, Integer count, boolean hasMe, Map<String, Organization> mapOrganizationIdToMap) {

    }

    @Override
    public boolean checkOrgName(Organization Organization) {
        return false;
    }

    @Override
    public void synOrgNamePath() {

    }

    @Override
    public List<String> getOrganizationNames() {
        return null;
    }

    @Override
    public List<Organization> getSecOrgNodeList() {
        return null;
    }

    @Override
    public List<Organization> getLevelOrganizationList(Boolean isLimit, int orgLevel) {
        return null;
    }

    @Override
    public List<String> getFirstAndSecLevelOrgIds(String orgId) {
        return null;
    }

    @Override
    public Map<String, String> getOrgIdNamePathMap(Set<String> orgIds) {
        return null;
    }

    @Override
    public String findOrgTreeJsonByDepth(String orgId, int depth) {
        return null;
    }

    @Override
    public List<Organization> getAllOrganization() {
        return null;
    }

    @Override
    public void save(List<Organization> Organizations) {

    }

    @Override
    public Map<String, List<String>> getUserIdListGroupByOrgId(List<String> orgIdList, boolean includeChildOrg, List<String> accountStatusList) {
        return null;
    }

    @Override
    public Map<String, Organization> getOrgIdAndOrganizationMap(Set<String> orgIds) {
        return null;
    }

    private List<Object[]> getOrganizationsByColumnsAndOrganization(List<String> organizationIds
            , List<String> columns, Organization organization) {
        HqlBuilder hqlBuilder = new HqlBuilder("select ");
        hqlBuilder.disableSplit();
        for (String columnName : columns) {
            hqlBuilder.append(columnName + ",");
        }
        hqlBuilder.removeLastCharacter();
        hqlBuilder.enableSplit();
        hqlBuilder.append("from Organization");
        hqlBuilder.append("where organizationId in (:orgIds)");
        hqlBuilder.addParameter("orgIds", organizationIds);
        if (organization.getLastModifyTime() != null) {
            hqlBuilder.append("and lastModifyTime >= :lastModifyTime");
            hqlBuilder.addParameter("lastModifyTime", organization.getLastModifyTime());
        }

        if (organization.getShowOrder() > 0) {
            hqlBuilder.append("and showOrder = :showOrder");
            hqlBuilder.addParameter("showOrder", organization.getShowOrder());
        }

        List<Object[]> result = baseService.queryList(hqlBuilder);
        return result;
    }

    private Double getMaxShowOrderByParentId(String parentId) {

        HqlBuilder hqlBuilder = new HqlBuilder("select max(showOrder) from Organization ");
        hqlBuilder.append("where parentId =:parentId");
        hqlBuilder.addParameter("parentId", parentId);
        return baseService.queryUniqueResult(hqlBuilder);
    }
}
