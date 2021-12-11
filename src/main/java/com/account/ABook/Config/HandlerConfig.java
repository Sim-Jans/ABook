package com.account.ABook.Config;

import com.account.ABook.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class HandlerConfig extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

//        HttpSession session = request.getSession();
//
//        Member OneMember = (Member) session.getAttribute("member");
//
//        if(OneMember == null){
//            System.out.println("실패");
//            return  false;
//        }

        return true;
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
        System.out.println("afterCompletion");
    }
}
