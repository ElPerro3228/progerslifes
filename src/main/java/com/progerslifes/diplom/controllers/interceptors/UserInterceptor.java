package com.progerslifes.diplom.controllers.interceptors;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.ProfilePageFacade;
import com.progerslifes.diplom.facades.dto.PostDTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private ProfilePageFacade profilePageFacade;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getAttribute("user") == null) {
            User user = profilePageFacade.getUser();
            request.setAttribute("user", user);
        }
        if (modelAndView != null) {
            modelAndView.getModelMap().
                    addAttribute("postTemplate", new PostDTO());
        }
    }
}
