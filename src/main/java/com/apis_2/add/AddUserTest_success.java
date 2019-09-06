package com.apis_2.add;

import com.utils.RequestUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.env.Env_test.testKey;
import static com.url.TestUrl.addUser_url;
import static com.url.TestUrl.findUser_url;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试场景：
 * 1. 调用 addUser 新增用户接口
 * 2. 调用 findUser 查询用户接口，根据 userName 查询数据结果（findUser 接口可以根据任意字段查询）
 *
 * @author difeng
 */
public class AddUserTest_success {

    // 加密密钥 - 相同的环境，通常密钥也是相同的，所以在先对其定义并赋值。在本例中 key 只是做样子，并没有实际作用。
    private String key = testKey;

    /**
     * 定义变量（这些变量在测试代码中会多次使用）
     */
    private String url = null;  // 接口地址
    private Map<String, Object> param = null;  // 构造请求参数
    private String response = null;  // 接收响应报文

    /**
     * 初始化测试参数 - 初始化 User 数据，用于“新增用户”和“查询用户”
     */
    // 字段 id 为数据库自增的，这个参数从接口传入也是无效的。在项目 model/User 类中，可以不定义 id 这个属性。
    private int id = 1;
    private String userName = "dazhentan" + randomNumeric(2);
    private String password = randomNumeric(6);
    private String age = "16";
    private String sex = "1";
    private String permission = randomNumeric(8);
    private String isDelete = "1";

    @Test
    public void addUser() {

        /**
         * 1. 调用 addUser 新增用户接口
         */
        // 获取 url
        url = addUser_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>(16);
//        param.put("id", id);
        param.put("userName", userName);
        param.put("password", password);
        param.put("age", age);
        param.put("sex", sex);
        param.put("permission", permission);
        param.put("isDelete", isDelete);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, param, key);
        System.out.println("[Test Case] response = " + response);

        // 断言 - 验证新增的数据，响应报文包含“添加成功”和 userName 的值。
        assertThat(response).contains("success").contains(userName).as("这里是断言的备注和描述信息");

        /**
         * 2. 调用 findUser 查询用户接口，根据 userName 查询数据结果（findUser 接口可以根据任意字段查询）
         */
        // 获取 url
        url = findUser_url;
        System.out.println("[Test Case] url = " + url);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>(16);
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

        // 断言 - 响应报文包含 userName 和 permission 的值
        assertThat(response).contains(userName).contains(permission).as("这里可以添加一些断言的备注和描述信息");
    }
}
