package com.account.ABook.service;


import com.account.ABook.dao.AccountDao;
import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;
import com.account.ABook.model.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountDao accountDao;

    @Override
    public List<AccountBook> getAccountList(Search search, Member member) throws Exception{
        List<AccountBook> list = accountDao.getAccountList(search, member);

        return list;
    }

    @Override
    public int insertAccountBook(AccountBook accountBook) throws Exception{
        return accountDao.insertAccountBook(accountBook);
    };

    public List<Kategorie> getKategorie(Member member) throws Exception{
        return accountDao.getKategorie(member);
    }

    public List<Map<String, Object>> getPayWay(Member member) throws Exception{
        return accountDao.getPayWay(member);
    }

    @Override
    public int insurtKategorie(Kategorie kategorie) throws Exception{
        return accountDao.insurtKategorie(kategorie);
    }

}
