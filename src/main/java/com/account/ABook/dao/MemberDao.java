package com.account.ABook.dao;

import com.account.ABook.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    public Member examineMember(Member member) throws Exception;
}

