package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author difeng
 */
public class RequestUtils {

    /**
     * doPostStringEntity() 方法说明：
     * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
     * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
     * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
     *
     * @param url   测试地址
     * @param param 报文参数
     * @param key   密钥
     * @return String response
     */
    public static String doPostStringEntity(String url, Map<String, Object> param, String key) {
        System.out.println("[RequestUtils] url = " + url);
        System.out.println("[RequestUtils] param = " + param);
        System.out.println("[RequestUtils] key = " + key);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");

        String body = JSONObject.toJSONString(param);

        StringEntity stringEntity = new StringEntity(body, "utf-8");

        httpPost.setEntity(stringEntity);

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String response = null;
        try {
            response = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[RequestUtils] response = " + response);

        return response;
    }

    /**
     * doPostFormEntity() 方法说明：
     * 1. 传输报文是 form 表单（键值对）格式，即：代码中实现的 POST 请求，其参数类型使用了 @RequestParam 或不加任何 @Request 标识
     * 2. http 信息头使用 application/x-www-form-urlencoded 注：但是测试代码中的 httpPost.setHeader 并没有起到作用，这一句代码可以不写。
     * 3. 测试代码的 HttpEntity（实体）使用 UrlEncodedFormEntity
     *
     * @param url   测试地址
     * @param param 报文参数
     * @param key   密钥
     * @return
     */
    public static String doPostFormEntity(String url, List<NameValuePair> param, String key) {
        System.out.println("[RequestUtils] url = " + url);
        System.out.println("[RequestUtils] param = " + param);
        System.out.println("[RequestUtils] key = " + key);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        // 随意写或不写 setHeader 都不影响 http 请求的运行结果。
//        httpPost.setHeader("content", "application/x-www-form-urlencoded");
//        httpPost.setHeader("11", "11");  // 因为这个 content 是默认的，写了或者写错了，都不会起作用。

        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpPost.setEntity(formEntity);

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String response = null;
        try {
            response = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[RequestUtils] response = " + response);

        return response;
    }
}
