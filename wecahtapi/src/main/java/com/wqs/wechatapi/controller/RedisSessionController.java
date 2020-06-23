package com.wqs.wechatapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RedisSessionController {

    @RequestMapping("/saveSession")
    public ResponseEntity saveSession(HttpSession session){
        session.setAttribute("user","wangqisong");
        return ResponseEntity.ok().body(session);
    }

    @RequestMapping("/getSession")
    public ResponseEntity getSession(HttpSession session){
        session.getAttribute("user");
        return ResponseEntity.ok().body(session);
    }
}
