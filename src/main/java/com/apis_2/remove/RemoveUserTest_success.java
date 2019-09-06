package com.apis_2.remove;

import com.utils.RequestUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.env.Env_test.testKey;
import static com.url.TestUrl.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试场景：
 * 1. 调用 addUser 新增用户接口
 * 2. 调用 removeUser 删除用户接口 - 根据任意信息删除，本例根据 userName 删除
 * 3. 调用 findUser 查询用户接口 - 任意信息都可以查询，本例根据 userName 查询
 *
 * @author difeng
 */
public class RemoveUserTest_success {

    // 加密密钥 - 相同的环境，通常密钥也是相同的，所以在先对其定义并赋值。在本例中 key 只是做样子，并没有实际作用。
    private String key = testKey;

    /**
     * 定义变量（这些变量在测试代码中会多次使用）
     */
    private String url = null;  // 接口地址
    private Map<String, Object> param = null;  // 构造请求参数
    private String response = null;  // 接收响应报文

    /**
     * 初始化测试参数 - 初始化 User 数据，用于“添加用户”、“删除用户”和“查询用户”
     */
    private String userName = "userName";
    private String password = "password";

    /**
     * 1. 调用 addUser 新增用户接口
     */
    @Test(priority = 1)
    public void addUser() {

        // 获取 url
        url = addUser_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>();
        param.put("userName", userName);
        param.put("password", password);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, param, key);
        System.out.println("[Test Case] response = " + response);

        // 断言
        assertThat(response).contains("success");
    }

    /**
     * 2. 调用 removeUser 删除用户接口 - 根据任意信息删除，本例根据 userName 删除
     */
    @Test(priority = 2)
    public void removeUser() {

        // 获取 url
        url = removeUser_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>();
        param.put("userName", userName);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, param, key);
        System.out.println("[Test Case] response = " + response);

        // 断言
        assertThat(response).contains("删除成功");
    }

    /**
     * 3. 调用 findUser 查询用户接口 - 任意信息都可以查询，本例根据 userName 查询
     */
    @Test(priority = 3)
    public void findUser() {

        // 获取 url
        url = findUser_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>();
        param.put("userName", userName);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, param, key);
        System.out.println("[Test Case] response = " + response);

        // 断言
        assertThat(response).contains("success").contains("[]").as("这里可以添加一些断言的备注和描述信息");
    }
}
