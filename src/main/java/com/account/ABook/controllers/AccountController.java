package com.account.ABook.controllers;

import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;
import com.account.ABook.model.Search;
import com.account.ABook.service.AccountServiceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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

    //날짜
    public String ChangeDate(String[] dateList){

        String date = "";

        for(int i = 0;i < dateList.length; i++){
            if(i == 2 || i == 4) date+= "-";
            date += dateList[i];
        }

        return date;
    }

    /*
    가게부 오픈
     */
    @RequestMapping(value="/getAccountList", method = RequestMethod.POST)
    public ResponseEntity getAccountList(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) Search search) throws Exception{

        System.out.println("시작");

        Member member = getMember(request);

        if(member != null){
            System.out.println("oracle");
            List<AccountBook> list = null;
            LocalDate now = LocalDate.now();

            //날짜 존재여부
            if(search != null){
                if(!"".equals(search.getAfterDate())){
                    search.setAfterDate(search.getAfterDate().substring(2,search.getAfterDate().length()));
                }
                if(!"".equals(search.getBeforeDate())){
                    search.setBeforeDate(search.getBeforeDate().substring(2,search.getBeforeDate().length()));
                }
                System.out.println(search);

                list =  accountService.getAccountList(search, member);
            }else{
                //날짜 없음

                System.out.println("시작");

                String toDay = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")).substring(2,8);

                String week = now.plusWeeks(-2).format(DateTimeFormatter.ofPattern("yyyyMMdd")).substring(2, 8);

                //날짜 변경
                String[] dateList = toDay.split("");
                toDay = ChangeDate(dateList);

                dateList = week.split("");
                week = ChangeDate(dateList);

                Search reSearch = new Search();

                reSearch.setToDay(toDay);
                reSearch.setWeek(week);

                list =  accountService.getAccountList(reSearch, member);
            }

            if(list == null) {
                return new ResponseEntity("NO", HttpStatus.BAD_GATEWAY);
            }


            List<Map<String, Object>> days = new ArrayList<>();

            for(int i = 0;i< 14;i++){
                //String[] date = new String[2];
                Map<String, Object> date = new HashMap<String, Object>();

                if(i != 0) now = now.plusDays(-1);
                DayOfWeek dayOfWeek = now.getDayOfWeek();
                int dayValue = dayOfWeek.getValue();
                String value = "";
                //요일 변경
                if(dayValue == 1) value = "월";
                else if(dayValue == 2) value = "화";
                else if(dayValue == 3) value = "수";
                else if(dayValue == 4) value = "목";
                else if(dayValue == 5) value = "금";
                else if(dayValue == 6) value = "토";
                else if(dayValue == 7) value = "일";

                //날짜 - 추가
                String forDay = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")).substring(2,8);;
                String[] forDateList = forDay.split("");
                System.out.println((forDay));
                forDay = ChangeDate(forDateList);

                System.out.println((forDay));
                //추가
                date.put("forDay", forDay);
                date.put("value", value);

                days.add(date);
            }




            //map
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("list", list);
            map.put("days", days);

            System.out.println(list);
            System.out.println("끝");
            return new ResponseEntity(map, HttpStatus.OK);
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
        accountBook.setPay(params.get("pay"));
        accountBook.setPaydate(params.get("date"));
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
            Map<String, Object> map = new HashMap<String, Object>();
            List<Kategorie> kategorie = accountService.getKategorie(member);

            List<Map<String, Object>> payWay = accountService.getPayWay(member);

            map.put("kategorie", kategorie);
            map.put("payWay", payWay);

            return new ResponseEntity(map, HttpStatus.OK);
        }

        return new ResponseEntity("not", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/insurtKategorie", method = RequestMethod.POST)
    public ResponseEntity insurtKategorie(HttpServletRequest request, @RequestBody Kategorie kategorie) throws Exception {
        System.out.println(kategorie);
        Member member = getMember(request);
        Optional<Member> ifMember= Optional.ofNullable(member);

        if(ifMember.isPresent()){
            kategorie.setMemberNpm(member.getMemberNpm());

            if(accountService.insurtKategorie(kategorie) > 0){
                return new ResponseEntity("ok", HttpStatus.OK);
            }
        }
        System.out.println(kategorie);
        return new ResponseEntity("not", HttpStatus.BAD_REQUEST);
    }









}
