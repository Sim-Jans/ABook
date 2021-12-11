package com.account.ABook.service;

import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;

import java.util.List;

public interface AccountService {

    List<AccountBook> getAccountList(Member member) throws Exception;

    int insertAccountBook(AccountBook accountBook) throws Exception;

    List<Kategorie> getKategorie(Member member) throws Exception;
}
