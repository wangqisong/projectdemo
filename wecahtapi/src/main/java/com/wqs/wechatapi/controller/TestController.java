package com.wqs.wechatapi.controller;

import com.wqs.wechatapi.util.MsgDigest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 接入微信公共号接口
 */
@RestController
@RequestMapping(value = "/testC")
public class TestController {

    @RequestMapping("/verifyToken")
    public ResponseEntity verifyToken(HttpServletRequest request){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] tempArr = {timestamp,nonce,echostr};
        Arrays.sort(tempArr);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String newS = tempArr[0]+tempArr[1]+tempArr[2];
        String digest = MsgDigest.digest(newS, "sha-1");
        System.out.println(digest);
        System.out.println(signature);

        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String token ="qweasd";
        return ResponseEntity.ok().body(echostr);
    }

    @RequestMapping(value = "/test")
    public ResponseEntity test(HttpServletRequest request){
        String msg = "test msg";
        return ResponseEntity.ok().body(msg);
    }

    @RequestMapping(value = "/api")
    public ResponseEntity api(HttpServletRequest request){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] tempArr = {timestamp,nonce,echostr};
        Arrays.sort(tempArr);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String newS = tempArr[0]+tempArr[1]+tempArr[2];
        String digest = MsgDigest.digest(newS, "sha-1");
        System.out.println(digest);
        System.out.println(signature);
        return ResponseEntity.ok().body(echostr);
    }

    public static void main(String[] args) {
        String[] tempArr = {"bimestamp","aonce","bchostr","12"};
        Arrays.sort(tempArr);
        System.out.println();
    }
}
