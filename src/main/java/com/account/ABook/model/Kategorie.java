package com.account.ABook.model;

import lombok.Data;

@Data
public class Kategorie {

    int kategorieno;        //id
    int member_npm;         //멤버id
    String mainmenu;        //상위
    String submenu;         //상세정보
    String todate;          //날짜

}
