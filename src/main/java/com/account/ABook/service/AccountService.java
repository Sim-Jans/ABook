package com.account.ABook.service;

import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;
import com.account.ABook.model.Search;

import java.util.List;
import java.util.Map;

public interface AccountService {

    List<AccountBook> getAccountList(Search search, Member member) throws Exception;

    int insertAccountBook(AccountBook accountBook) throws Exception;

    List<Kategorie> getKategorie(Member member) throws Exception;

    int insurtKategorie(Kategorie kategorie) throws Exception;

    public List<Map<String, Object>> getPayWay(Member member) throws Exception;

}
