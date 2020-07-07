package com.wqs.function.demo.httpfc;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.FunctionComputeLogger;
import com.aliyun.fc.runtime.FunctionInitializer;
import com.aliyun.fc.runtime.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Hello implements HttpRequestHandler, FunctionInitializer {
    private static int count = 0;

    private static int init = 0;

    public void handleRequest(HttpServletRequest request, HttpServletResponse response, Context context)
            throws IOException {
        count += 1;
        FunctionComputeLogger logger = context.getLogger();
        logger.debug(String.format("init: %d , invoke times: %d", init, count));

        String requestPath = (String) request.getAttribute("FC_REQUEST_PATH");
        String requestURI = (String) request.getAttribute("FC_REQUEST_URI");
        String requestClientIP = (String) request.getAttribute("FC_REQUEST_CLIENT_IP");
        response.setStatus(200);
        response.setHeader("header1", "value1");
        response.setHeader("header2", "value2");

        String body = String.format("Path: %s%n Uri: %s%n IP: %s5n", requestPath, requestURI, requestClientIP);
        logger.debug(body);
        OutputStream out = response.getOutputStream();
        out.write((body).getBytes());

        String key1 = request.getParameter("key1");
        logger.debug(String.format("param key1: %s \n", key1));
        out.write(key1.getBytes());
        out.flush();
        out.close();
    }

    @Override
    public void initialize(Context context) throws IOException {
        FunctionComputeLogger logger = context.getLogger();
        logger.debug("===初始化==");
        init += 1;
    }

    public static void main(String[] args) {

    }
}