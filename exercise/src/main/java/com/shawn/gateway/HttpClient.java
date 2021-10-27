package com.shawn.gateway;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yansq
 * @date 2020/11/1
 */
public class HttpClient {

    /**
     * 最大连接数
     */
    private final static int MAX_TOTAL_CONNECTIONS = 300;
    /**
     * 每个路由最大连接数
     */
    private final static int MAX_ROUTE_CONNECTIONS = 200;
    /**
     * 连接超时时间
     */
    private final static int CONNECT_TIMEOUT = 5000;
    /**
     * 读取超时时间
     */
    private final static int SOCKET_TIMEOUT = 5000;

    /**
     * 创建httpclient连接池
     */
    private static PoolingHttpClientConnectionManager connManager;

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(SOCKET_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setConnectionRequestTimeout(CONNECT_TIMEOUT).build();
    private static CloseableHttpClient client;
    static {
        //创建httpclient连接池
        connManager = new PoolingHttpClientConnectionManager();
        //设置连接池最大数量
        connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        //设置单个路由最大连接数量
        connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
        connManager.closeExpiredConnections();
        connManager.closeIdleConnections(30, TimeUnit.SECONDS);
        client = HttpClients.createMinimal(connManager);
    }


    public static void main(String[] args) {
        String response = HttpClient.get("http://localhost:8080");
        System.out.println(response);
    }


    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        return execute(httpGet);
    }


    public static String post(String url, Object paramObj) {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(paramObj), ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        return execute(httpPost);
    }

    private static String execute(HttpUriRequest request){
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            request.abort();
        }
        return null;
    }
}
