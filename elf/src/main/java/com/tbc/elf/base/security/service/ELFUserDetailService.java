package com.tbc.elf.base.security.service;

import com.tbc.elf.app.uc.model.Authority;
import com.tbc.elf.app.uc.service.AuthorityService;
import com.tbc.elf.app.uc.service.LoginService;
import com.tbc.elf.app.uc.service.UserService;
import com.tbc.elf.base.security.model.ResourceDetails;
import com.tbc.elf.base.security.util.AuthenticationUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.List;

public class ELFUserDetailService implements UserDetailsService {
    private final String msg = "<<<<<<<<<<<<<<<<<<------------------->>>>>>>>>>>>>>>>>>";
    private final String rolePrefix = "ROLE_";

    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;
    @Resource
    private AuthorityService authorityService;

    /**
     * 根据用户名，登录处理
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String corpCode = "";
        String userName = "";
        if (username.contains("|")) {
            String[] split = username.split("\\|");
            corpCode = split[0];
            userName = split[1];
        }
        return null;
    }


//        System.out.println(msg + "登录名:" + username);
//        // 根据用户名查询出用户基本信息、权限
//        List<Operator> operList = systemService.getOperByAccount(username);
//        if (operList == null || operList.size() != 1) {
//            throw new UsernameNotFoundException("用户" + username + "不存在!");
//        }
//        Operator operator = operList.get(0);
//
//        // 权限取得
//        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//        if (operator.getRoles() != null && operator.getRoles().size() > 0) {
//            for (Role r : operator.getRoles()) {
//                if (r.getFunctions() != null && r.getFunctions().size() > 0) {
//                    for (Function f : r.getFunctions()) {
//                        //String roleName = rolePrefix + f.getId();
//                        //我改的
//                        String roleName = rolePrefix + f.getFunUrl();
//                        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
//                        auths.add(authority);
//                    }
//                }
//            }
//        }
//
//        // 构造UserDetails
//        /*boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;*/
//
//        return new User(operator.getOperAccountNo(), operator.getOperPassword(), true, true, true, true, auths);
//    }
//
    /**
     * 取得所有权限
     */
//    public List<ResourceDetails> findAuthority() {
//        // 去数据库中查询出所有权限
//        String hql = "FROM Authority WHERE corpCode = ?";
//        List<Authority> authorities = authorityService.listByHQL(hql, new Object[]{AuthenticationUtil.getCorpCode()});
//
//        return getResourceByPrivResource(functions);
//    }

    /**
     * 权限名称拼上ROLE_（ROLE_ + roleName）
     *
     * @param roleName
     */
    public String formatRoleName(String roleName) {
        return rolePrefix + roleName;
    }

//    private List<ResourceDetails> getResourceByPrivResource(List<Function> funs) {
//        List<ResourceDetails> result = new ArrayList<ResourceDetails>();
//        for (Function fun : funs) {
//            //String roleName = rolePrefix + String.valueOf(fun.getId());// .toUpperCase();
//            //我改的
//            String roleName = rolePrefix + String.valueOf(fun.getFunUrl());
//            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
//            result.add(new SecurityResource(fun.getFunUrl(), SecurityResource.RESOURCE_TYPE_URL, authority));
//        }
//
//        return result;
//    }

}
