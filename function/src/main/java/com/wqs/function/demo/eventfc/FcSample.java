package com.wqs.function.demo.eventfc;

import com.aliyuncs.fc.client.FunctionComputeClient;
import com.aliyuncs.fc.constants.Const;
import com.aliyuncs.fc.model.Code;
import com.aliyuncs.fc.request.*;
import com.aliyuncs.fc.response.*;

import java.io.IOException;
import java.net.HttpURLConnection;

public class FcSample {
    private static final String CODE_DIR = "D:\\workspace\\projectdemo\\function\\src\\main\\resources";
    private static final String REGION = "cn-chengdu";
    private static final String SERVICE_NAME = "test_service";
    private static final String FUNCTION_NAME = "test_function";

    public static void main(final String[] args) throws IOException {
        String accessKey = System.getenv("ACCESS_KEY");
        String accessSecretKey = System.getenv("SECRET_KEY");
        String accountId = System.getenv("ACCOUNT_ID");
        String role = System.getenv("ROLE");

        // Initialize FC client
        FunctionComputeClient fcClient = new FunctionComputeClient(REGION, accountId, accessKey, accessSecretKey);

        // Set to a specific endpoint in case needed, endpoint sample: http://123456.cn-hangzhou.fc.aliyuncs.com
        // fcClient.setEndpoint("http://{accountId}.{regionId}.fc.aliyuncs.com.");

        // Create a service
        CreateServiceRequest csReq = new CreateServiceRequest();
        csReq.setServiceName(SERVICE_NAME);
        csReq.setDescription("FC test service");
        csReq.setRole(role);
        CreateServiceResponse csResp = fcClient.createService(csReq);
        System.out.println("Created service, request ID " + csResp.getRequestId());

        // Create a function
        CreateFunctionRequest cfReq = new CreateFunctionRequest(SERVICE_NAME);
        cfReq.setFunctionName(FUNCTION_NAME);
        cfReq.setDescription("Function for test");
        cfReq.setMemorySize(128);
        cfReq.setRuntime("nodejs4.4");
        cfReq.setHandler("counter.handler");
        // Used in initializer situations.
        cfReq.setInitializer("counter.initializer");
        Code code = new Code().setDir(CODE_DIR);
        cfReq.setCode(code);
        cfReq.setTimeout(10);
        CreateFunctionResponse cfResp = fcClient.createFunction(cfReq);
        System.out.println("Created function, request ID " + cfResp.getRequestId());

        // Invoke the function with a string as function event parameter, Sync mode
        InvokeFunctionRequest invkReq = new InvokeFunctionRequest(SERVICE_NAME, FUNCTION_NAME);
        String payload = "Hello FunctionCompute!";
        invkReq.setPayload(payload.getBytes());
        InvokeFunctionResponse invkResp = fcClient.invokeFunction(invkReq);
        System.out.println(new String(invkResp.getContent()));

        // Invoke the function, Async mode
        invkReq.setInvocationType(Const.INVOCATION_TYPE_ASYNC);
        invkResp = fcClient.invokeFunction(invkReq);
        if (HttpURLConnection.HTTP_ACCEPTED == invkResp.getStatus()) {
            System.out.println("Async invocation has been queued for execution, request ID: " + invkResp.getRequestId());
        } else {
            System.out.println("Async invocation was not accepted");
        }
        /*// Delete the function
        DeleteFunctionRequest dfReq = new DeleteFunctionRequest(SERVICE_NAME, FUNCTION_NAME);
        DeleteFunctionResponse dfResp = fcClient.deleteFunction(dfReq);
        System.out.println("Deleted function, request ID " + dfResp.getRequestId());

        // Delete the service
        DeleteServiceRequest dsReq = new DeleteServiceRequest(SERVICE_NAME);
        DeleteServiceResponse dsResp = fcClient.deleteService(dsReq);
        System.out.println("Deleted service, request ID " + dsResp.getRequestId());*/
    }
}