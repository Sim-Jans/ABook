package com.account.ABook.controllers;

import com.account.ABook.model.Member;
import com.account.ABook.service.MemberService;
import com.account.ABook.service.MemberServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestController
public class MemberController {

    @Autowired
    MemberServicelmpl memberService;

    @RequestMapping (value="/login", method = RequestMethod.POST)
    public ResponseEntity examineMember(HttpServletRequest request, HttpServletResponse response, @RequestBody Member member) throws Exception{

        System.out.println("로그인");

        System.out.println("1:  "+member.getId());
        System.out.println("2:  "+member.getPwd());

        Member oneMember = memberService.main(member);

        if(oneMember != null){
            if (oneMember.getStatus().equals("Y")){
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                HttpSession session = request.getSession();
                session.setAttribute("member", oneMember);

                return new ResponseEntity(oneMember, HttpStatus.OK);
            }else{
                return new ResponseEntity(oneMember, HttpStatus.BAD_GATEWAY);
            }
        }else{
            return new ResponseEntity("not", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping (value="/home", method = RequestMethod.GET)
    public ResponseEntity test(HttpServletRequest request, HttpServletResponse response){
        System.out.println("세션 시작");

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기 dlrhdll

        HttpSession session = request.getSession();

        Member OneMember = (Member) session.getAttribute("member");

        System.out.println("12:   "+ OneMember);

        long beforeTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("시간차이(m) : "+secDiffTime);

        if(OneMember != null){
            return new ResponseEntity("ok", HttpStatus.OK);
        }else{
            return new ResponseEntity("no", HttpStatus.OK);
        }
    }


}
