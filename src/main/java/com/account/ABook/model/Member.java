package com.account.ABook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
public class Member {

    private int memberNpm;
    private String  id;

    private String pwd;

    private String hiredate;

    private String status;
}
