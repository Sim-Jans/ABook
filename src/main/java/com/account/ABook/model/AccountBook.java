package com.account.ABook.model;

import lombok.Data;

@Data
public class AccountBook {
    int accountno;      //id
    String status;      //수익,지출
    String paydate;     //날짜
    String mainMenu;    //카테고리
    String subMenu;     //카테고리 서브
    String name;        //이름
    String sub;         //설명
    String price;       //가격
    int memberNpm;      //멤버
    String icon;        //아이콘
}
