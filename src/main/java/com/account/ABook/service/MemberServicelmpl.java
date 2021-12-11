package com.account.ABook.service;


import com.account.ABook.dao.MemberDao;
import com.account.ABook.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServicelmpl implements  MemberService{

    @Autowired
    MemberDao memberDao;

    public Member main(Member member){

        Member LoginMember = null;

        try{
            LoginMember = memberDao.examineMember(member);
        }catch (Exception e){
            e.printStackTrace();
        }

        return LoginMember;
    }

}
