package com.zhangyan.webmvc.ayschttp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.message.BasicNameValuePair;

/**
 * http client 使用
 * */
public class HttClientUseDemo extends HttpClientService {


    private static final ExecutorService POOL = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws InterruptedException {

        new HttClientUseDemo().getConfCall();
    }

    public void getConfCall() throws InterruptedException {

        String url = "http://127.0.0.1:9002/getAllUser/time";

        List<BasicNameValuePair> urlParams = new ArrayList<BasicNameValuePair>();
        urlParams.add(new BasicNameValuePair("latency", "1"));

        while (true) {
//            Thread.sleep(200);
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall2()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall2()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall2()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall2()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall()));
            POOL.execute(() -> exeHttpReq(url, false, urlParams, null, new GetConfCall2()));
        }


    }

    public void exeHttpReq(String baseUrl, boolean isPost,
                           List<BasicNameValuePair> urlParams,
                           List<BasicNameValuePair> postBody,
                           FutureCallback<HttpResponse> callback) {

        try {
            System.out.println("enter exeAsyncReq");
            exeAsyncReq(baseUrl, isPost, urlParams, postBody, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 被回调的对象，给异步的httpclient使用
     *
     * */
    class GetConfCall implements FutureCallback<HttpResponse> {

        private String name = "lisi";
        /**
         * 请求完成后调用该函数
         */
        @Override
        public void completed(HttpResponse response) {

            System.out.println(name + "_" + response.getStatusLine().getStatusCode());
            System.out.println(name + "_" + getHttpContent(response));

            HttpClientUtils.closeQuietly(response);

        }

        /**
         * 请求取消后调用该函数
         */
        @Override
        public void cancelled() {

        }

        /**
         * 请求失败后调用该函数
         */
        @Override
        public void failed(Exception e) {

        }

    }

    class GetConfCall2 implements FutureCallback<HttpResponse> {

        private String name = "zhangsan";

        /**
         * 请求完成后调用该函数
         */
        @Override
        public void completed(HttpResponse response) {

            System.out.println(name + ":" + response.getStatusLine().getStatusCode());
            System.out.println(name + ":" + getHttpContent(response));

            HttpClientUtils.closeQuietly(response);

        }

        /**
         * 请求取消后调用该函数
         */
        @Override
        public void cancelled() {

        }

        /**
         * 请求失败后调用该函数
         */
        @Override
        public void failed(Exception e) {

        }

    }
}