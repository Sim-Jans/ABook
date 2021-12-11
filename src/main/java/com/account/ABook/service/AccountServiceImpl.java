package com.account.ABook.service;


import com.account.ABook.dao.AccountDao;
import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountDao accountDao;

    @Override
    public List<AccountBook> getAccountList(Member member) throws Exception{
        List<AccountBook> list = accountDao.getAccountList(member);

        return list;
    }

    @Override
    public int insertAccountBook(AccountBook accountBook) throws Exception{
        return accountDao.insertAccountBook(accountBook);
    };

    public List<Kategorie> getKategorie(Member member) throws Exception{
        return accountDao.getKategorie(member);
    }

}
