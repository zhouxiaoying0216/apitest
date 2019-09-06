package com.apis_2.find;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.utils.RequestUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.env.Env_test.testKey;
import static com.url.TestUrl.findUser_JSONArray_toJSONString_url;
import static com.url.TestUrl.findUser_JSON_toJSONString_url;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试前提：
 * 1. 确保数据库中有一条 id 为 10 的用户信息（用于查询操作）
 * --------------------
 * 测试场景：
 * 1. 调用 findUser 查询用户接口 - sqlSessionTemplate.selectOne - 返回 json 格式字符串
 * public void findUser_JSON_toJSONString()
 */

/**
 * 测试前提：
 * 1. 确保数据库中有至少 2 条 sex = 2 的用户信息（用于查询操作）
 * --------------------
 * 测试场景：
 * 1. 调用 findUser 查询用户接口 - sqlSessionTemplate.selectList - 返回 json 格式字符串
 * public void findUser_JSONArray_toJSONString()
 *
 * @author difeng
 */
public class FindUserTest_success {

    // 加密密钥 - 相同的环境，通常密钥也是相同的，所以在先对其定义并赋值。在本例中 key 只是做样子，并没有实际作用。
    private String key = testKey;

    /**
     * 定义变量（这些变量在测试代码中会多次使用）
     */
    private String url = null;  // 接口地址
    private Map<String, Object> param = null;  // 构造请求参数
    private String response = null;  // 接收响应报文

    /**
     * 测试前提：
     * 1. 确保数据库中有一条 id 为 10 的用户信息（用于查询操作）
     * --------------------
     * 测试场景：
     * 1. 调用 findUser 查询用户接口 - sqlSessionTemplate.selectOne - 返回 json 格式字符串
     */
    @Test
    public void findUser_JSON_toJSONString() {

        /**
         * 初始化测试参数 - 初始化 User 数据
         */
        int id = 10;  // 根据 id 查询（param 仅需 put 一个参数即可）

        // 获取 url
        url = findUser_JSON_toJSONString_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>(16);
        param.put("id", id);  // 根据 id 查询（param 仅需 put 一个参数即可）

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, param, key);
        System.out.println("[Test Case] response = " + response);
        // 返回数据格式：
        // {"age":"17","id":100,"isDelete":"9","password":"100100100","permission":"permission100","sex":"100","userName":"dazhentan100"}

        /**
         * 在代码实现中，将 v2/findUser_JSON_toJSONString 接口的返回值定义为“字符串”类型（JSON 格式字符串）
         * 函数原型：public String findUser_JSON(@RequestBody UserInfo user)
         */
        // 使用 JSONPath.eval() 分别获取返回报文中的 userName 和 password
        JSONObject jsonObject = JSON.parseObject(response);
        String userName = JSONPath.eval(jsonObject, "$userName").toString();
        String password = JSONPath.eval(jsonObject, "password").toString();

        // 断言
        assertThat(userName).isEqualTo("dazhentan10");
        assertThat(password).isEqualTo("101010");
    }

    /**
     * 测试前提：
     * 1. 确保数据库中有至少 2 条 sex = 2 的用户信息（用于查询操作）
     * --------------------
     * 测试场景：
     * 1. 调用 findUser 查询用户接口 - sqlSessionTemplate.selectList - 返回 json 格式字符串
     */
    @Test
    public void findUser_JSONArray_toJSONString() {

        /**
         * 初始化测试参数 - 初始化 User 数据
         */
        // 根据 sex 查询（param 仅需 put 一个参数即可）
        String sex = "2";

        // 获取 url
        url = findUser_JSONArray_toJSONString_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        param = new HashMap<String, Object>(16);
        param.put("sex", sex);  // 根据 sex 查询（param 仅需 put 一个参数即可）

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, param, key);
        System.out.println("[Test Case] response = " + response);
        // 返回数据格式：
        // [{"id":3,"password":"111111","sex":"2","userName":"dazhentan"},{"id":4,"isDelete":"1","password":"111111","sex":"2","userName":"dazhentan"}]

        /**
         * 在代码实现时，将 v2/findUser_JSONArray_toJSONString 接口的返回值定义为“字符串”类型（JSON 格式字符串）
         * 函数原型：public String findUser_JSONArray(@RequestBody UserInfo user)
         */
        // 将 response 转为 JSONArray 对象，根据 index 获取指定的 JSON 格式字符串
        int index = 0;
        JSONArray jsonArray = JSONArray.parseArray(response);
        String jsonString = jsonArray.getString(index);
        System.out.println("[Test Case] jsonString = " + jsonString);

        // 使用 JSONPath.eval() 分别获取 jsonString 的 userName 和 password
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String userName = JSONPath.eval(jsonObject, "$userName").toString();
        String password = JSONPath.eval(jsonObject, "password").toString();

        // 断言
        assertThat(userName).isEqualTo("dazhentan").as("这里可以添加一些断言的备注和描述信息");
        assertThat(password).isEqualTo("111111").as("这里可以添加一些断言的备注和描述信息");
    }
}
