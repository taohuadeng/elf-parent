package com.tbc.elf.base.security;

import com.tbc.elf.app.uc.service.AuthorityService;
import com.tbc.elf.app.uc.service.OrganizationService;
import com.tbc.elf.app.uc.service.RoleService;
import com.tbc.elf.app.uc.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 人员详细信息过滤器
 *
 * @author ELF@TEAM
 * @since 2016年2月24日16:52:39
 */
public class ELFUserDetailsFilter implements Filter {

    public static final String USER_SERVICE = "userService";
    public static final String ROLE_SERVICE = "roleService";
    public static final String AUTHORITY_SERVICE = "authorityService";
    public static final String ORGANIZATION_SERVICE = "organizationService";
    private UserService userService;
    private RoleService roleService;
    private AuthorityService authorityService;
    private OrganizationService organizationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*String userServiceName = filterConfig.getInitParameter(USER_SERVICE);// 用户Service名称
        String roleServiceName = filterConfig.getInitParameter(ROLE_SERVICE);// 角色Service名称
        String authorityServiceName = filterConfig.getInitParameter(AUTHORITY_SERVICE);// 权限Service名称
        String organizationServiceName = filterConfig.getInitParameter(ORGANIZATION_SERVICE);// 组织Service名称*/
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig
                .getServletContext());

        userService = (UserService) wac.getBean(USER_SERVICE);
        roleService = (RoleService) wac.getBean(ROLE_SERVICE);
        authorityService = (AuthorityService) wac.getBean(AUTHORITY_SERVICE);
        organizationService = (OrganizationService) wac.getBean(ORGANIZATION_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

    }

    @Override
    public void destroy() {

    }
}
