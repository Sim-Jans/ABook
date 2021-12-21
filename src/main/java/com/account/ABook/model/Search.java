package com.account.ABook.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Search {
    String beforeDate;
    String afterDate;

    String toDay;

    String week;
}
