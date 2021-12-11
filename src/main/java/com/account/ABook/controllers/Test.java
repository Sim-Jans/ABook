package com.account.ABook.controllers;

import com.account.ABook.dao.TestDao;
import com.account.ABook.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Test {

    @Autowired
    TestService testService;

    @Autowired
    TestDao testDao;

    @RequestMapping("/palylist")
    public String playList(){
        return "playList";
    }

    @RequestMapping(path="/test", method = RequestMethod.GET)
    public ResponseEntity test(){

        System.out.println("okkkkk");

        HashMap map = new HashMap();

        map.put("aa", 123);

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping(path="/oracle")
    public ResponseEntity oracle(){

        HashMap map = new HashMap();




       return new ResponseEntity(map, HttpStatus.OK);
    }

}
