package com.account.ABook.controllers;

import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;
import com.account.ABook.service.AccountServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class AccountController {


    @Autowired
    AccountServiceImpl accountService;

    /*
    멤버 값 세션 오픈
     */
    public Member getMember(HttpServletRequest request){

        HttpSession session = request.getSession();
        Member member = null;

        try{
            member = (Member) session.getAttribute("member");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return member;
    }

    /*
    가게부 오픈
     */
    @RequestMapping(value="/getAccountList", method = RequestMethod.POST)
    public ResponseEntity getAccountList(HttpServletRequest request, HttpServletResponse response) throws Exception{

        System.out.println("시작");

        Member member = getMember(request);

        if(member != null){
            System.out.println("oracle");
            List<AccountBook> list =  accountService.getAccountList(member);

            if(list == null){
                return new ResponseEntity("NO", HttpStatus.BAD_GATEWAY);
            }
            System.out.println("끝");
            return new ResponseEntity(list, HttpStatus.OK);
        }
        //멤버 없음
        else{
            return new ResponseEntity("not", HttpStatus.BAD_REQUEST);
        }

    }

    /*
    가게부 입력
     */
    @RequestMapping(value="/insertAccountBook", method = RequestMethod.POST)
    public ResponseEntity insertAccountBook(HttpServletRequest request, HttpServletResponse response, @RequestBody HashMap<String,String> params) throws Exception{

        Member member = getMember(request);

        System.out.println(params);
        AccountBook accountBook = new AccountBook();


        accountBook.setStatus(params.get("status"));
        accountBook.setMainMenu(params.get("id"));
        accountBook.setName(params.get("name"));
        accountBook.setSub(params.get("sub"));
        accountBook.setPrice(params.get("price"));
        accountBook.setMemberNpm(member.getMemberNpm());

        if(member != null){
            if(accountService.insertAccountBook(accountBook) > 0){
                return new ResponseEntity("ok", HttpStatus.OK);
            }
        }

        return new ResponseEntity("not", HttpStatus.OK);
    }

    /*
    카테고리 오픈
     */
    @RequestMapping(value="/getKategorie", method= RequestMethod.POST)
    public ResponseEntity getKategorie(HttpServletRequest request) throws Exception {

        Member member = getMember(request);

        if(member != null){
            List<Kategorie> kategorie = accountService.getKategorie(member);

            return new ResponseEntity(kategorie, HttpStatus.OK);
        }

        return new ResponseEntity("not", HttpStatus.BAD_REQUEST);
    }












}
