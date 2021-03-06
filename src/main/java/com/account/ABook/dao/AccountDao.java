package com.account.ABook.dao;

import com.account.ABook.model.AccountBook;
import com.account.ABook.model.Kategorie;
import com.account.ABook.model.Member;
import com.account.ABook.model.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface AccountDao {

    //내용 입력
    public int insertAccountBook(AccountBook accountBook) throws Exception;

    public List<AccountBook> getAccountList (Search search, Member member) throws Exception;

    public List<Kategorie> getKategorie(Member member) throws Exception;

    public int insurtKategorie(Kategorie kategorie);

    public List<Map<String, Object>> getPayWay(Member member) throws Exception;
}
