package com.tbc.elf.base.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/basic/*")
public class LoginController {

    private Log logger = LogFactory.getLog(LoginController.class);

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));// 去掉空格
    }

    /**
     * 未登录，跳转登录页面
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("进入【浦发银行投诉管理】登录页面！");
        }

        logger.info("进入【浦发银行投诉管理】登录页面！");
        logger.debug("进入【浦发银行投诉管理】登录页面！");
        return new ModelAndView("/login");
    }

    /**
     * 登出，跳转登录页面
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
//    @RequestMapping("/loginOut")
//    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        loginService.saveLoginHis(LoginHis.LoginType.LOGOUT);
//        if (logger.isDebugEnabled()) {
//            logger.debug("登出系统！");
//        }
//        return new ModelAndView("/login");
//    }

    /**
     * 登录成功，跳转到菜单页面
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/loginSuccess")
    public ModelAndView loginSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.getSession().setAttribute("uname", AuthenticationUtil.getMyUserDetails().getOperName());
        ModelAndView modelAndView = new ModelAndView("/base/login/main");
//        loginService.saveLoginHis(LoginHis.LoginType.LOGIN);
        return modelAndView;
    }

    @RequestMapping("/mainHead")
    public ModelAndView mainHead(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/base/login/main-head");
        return modelAndView;
    }

    @RequestMapping("/mainLeft")
    public ModelAndView mainLeft(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/base/login/main-left");
        return modelAndView;
    }

    /**
     * 登录成功，跳转到菜单页面
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/loginFailed")
    public ModelAndView loginFailed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("/base/exception/exceptionLogin");
    }

    /**
     * 登录成功，跳转到菜单页面
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("/base/exception/exceptionDenied");
    }

    /**
     * session会话超时
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/sessionOut")
    public ModelAndView sessionOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("/base/exception/exceptionSessionTimeOut");
    }

}
