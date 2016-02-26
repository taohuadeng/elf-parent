package com.tbc.elf.base.security.util;

import com.tbc.elf.app.uc.model.Organization;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.List;

/**
 * 此工具类可以方便获取当前用户信息、所属机构、权限等
 */
public class AuthenticationUtil {
    private static final ThreadLocal<ELFUserDetails> ELFUserDetailsHolder = new ThreadLocal<ELFUserDetails>();

    Log LOG = LogFactory.getLog(AuthenticationUtil.class);
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    private AccessDecisionManager accessDecisionManager;

    /**
     * 清空ThreadLocal（单元测试用）
     */
    public static void clearThreadLocal() {
        ELFUserDetailsHolder.remove();
    }

    /**
     * 取得当前用户标识（登录名称）
     */
    public static String getLoginName() {
        Authentication currentUser = getCurrentUserAuthentication();
        if (currentUser != null) {
            return currentUser.getName();
        }

        return null;
    }

    /**
     * 取得当前用户Model
     */
    public static ELFUserDetails getUserDetails() {
        return ELFUserDetailsHolder.get();
    }

    /**
     * 设置当前用户Model
     */
    public static void setMyUserDetails(ELFUserDetails ELFUserDetails) {
        ELFUserDetailsHolder.set(ELFUserDetails);
    }

    /**
     * 取得当前用户所在部门名称
     */
    public static String getOrgName() {
        String result = null;
        ELFUserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            result = userDetails.getOrganizationName();
        }

        return result;
    }

    /**
     * 取得当前用户所在公司
     */
    public static String getCorpCode() {
        /*String result = null;
        ELFUserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            result = userDetails.getCorpCode();
        }

        return result;*/
        return "default";
    }

    /**
     * 获取当前登录用户
     */
    public static Authentication getCurrentUserAuthentication() {
        Authentication currentUser = null;

        SecurityContext context = SecurityContextHolder.getContext();
        if (null != context) {
            currentUser = context.getAuthentication();
        }

        return currentUser;
    }

    /**
     * 获取登录用户的权限
     */
    public static Collection<? extends GrantedAuthority> getGrantedAuthority() {
        Authentication currentUser = AuthenticationUtil.getCurrentUserAuthentication();
        if (currentUser != null) {
            return currentUser.getAuthorities();
        }

        return null;
    }

    /**
     * 根据URL，判断当前用户是否拥有访问权限
     *
     * @return boolean
     */
    public boolean accessibleTo(String accessPattern) {
        Authentication userAuthentication = getCurrentUserAuthentication();
        Collection<ConfigAttribute> configAttributeDefinition = filterInvocationSecurityMetadataSource
                .getAttributes(accessPattern);
        if (configAttributeDefinition == null) {
            return true;
        }

        try {
            accessDecisionManager.decide(userAuthentication, userAuthentication, configAttributeDefinition);
            return true;
        } catch (InsufficientAuthenticationException e) {
            LOG.debug(e.getMessage(), e);
            return false;
        } catch (AccessDeniedException e) {
            LOG.debug(e.getMessage());
            return false;
        }
    }

    public FilterInvocationSecurityMetadataSource getFilterInvocationSecurityMetadataSource() {
        return filterInvocationSecurityMetadataSource;
    }

    public void setFilterInvocationSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
    }

    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        this.accessDecisionManager = accessDecisionManager;
    }
}