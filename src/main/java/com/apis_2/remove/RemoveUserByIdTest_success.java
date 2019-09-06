package com.apis_2.remove;

import com.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.env.Env_test.testKey;
import static com.url.TestUrl.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 注意：
 * 通过 XML 文件的方式，多线程执行测试用例时，parallel 的取值可能会导致测试失败。
 * parallel="tests"
 * parallel="classes"
 * parallel="method"
 * <p>
 * 另外：
 * <p>
 * 通过 XML 文件的方式执行测试用例，在多线程执行测试用例时，测试代码什么时候写在一个 @Test 中，
 * 什么时候写在多个 @Test 中，就需要仔细思考，或在执行测试用例失败时，进行调整。
 * <p>
 * 如：RemoveUserByIdTest_success.java 这条测试用，如果把测试场景分开写在多个 @Test 中，
 * 即便是指定了执行顺序 @Test(priority = n)，也会在删除方法时出现“删除失败”的情况，
 * 所以就把整个测试场景写在一个 @Test 当中，这样才能够保证每次都删除成功。
 */

/**
 * 测试场景：
 * 1. 调用 addUser 新增用户接口
 * 2. 调用 findUser 查询用户接口 - 并获取到用户的 id 用于“删除”
 * 3. 调用 removeUserById 接口，根据 id 删除用户
 * 4. 调用 findUserById 查询用户接口 - 数据已经被删除，接口返回“查询失败”
 *
 * @author difeng
 */
public class RemoveUserByIdTest_success {

    // 加密密钥 - 相同的环境，通常密钥也是相同的，所以在先对其定义并赋值。在本例中 key 只是做样子，并没有实际作用。
    private String key = testKey;

    /**
     * 定义变量（这些变量在测试代码中会多次使用）
     */
    private String url = null;  // 接口地址
    private Map<String, Object> stringObjectMapParam = null;  // 构造请求参数
    private List<NameValuePair> nameValuePairListParam = null;
    private String response = null;  // 接收响应报文

    /**
     * 初始化测试参数 - 初始化 User 数据，用于“添加用户”、“删除用户”和“查询用户”
     */
    private String userName = "userName";
    private String password = "password";
    private int id = 0;

    /**
     * 1. 调用 addUser 新增用户接口
     */
    @Test(priority = 1)
    public void addUser1() {

        // 获取 url
        url = addUser_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        stringObjectMapParam = new HashMap<String, Object>();
        stringObjectMapParam.put("userName", userName);
        stringObjectMapParam.put("password", password);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, stringObjectMapParam, key);
        System.out.println("[Test Case] response = " + response);

        // 断言
        assertThat(response).contains("success");
//    }

        /**
         * 2. 调用 findUser 查询用户接口 - 并获取到用户的 id 用于“删除”
         */
//    @Test(priority = 2)
//    public void findUser() {

        // 获取 url
        url = findUser_url;
        System.out.println("[Test Case] url = " + url);

        // 构造 post 请求参数（在 doPostStringEntity() 方法内，会将 Map 转为 json 格式字符串）
        stringObjectMapParam = new HashMap<String, Object>();
        stringObjectMapParam.put("userName", userName);

        /**
         * doPostStringEntity() 方法说明：
         * 1. 传输报文是 json 格式的字符串，即：代码中实现的 POST 请求，其参数类型使用了 @RequestBody
         * 2. http 信息头使用 application/json 即：测试代码 httpPost.setHeader("content-type", "application/json");
         * 3. 测试代码的 HttpEntity（实体）使用 StringEntity
         */
        // 调用 doPostStringEntity() 实现 httpclient 发送 post 请求
        response = RequestUtils.doPostStringEntity(url, stringObjectMapParam, key);
        System.out.println("[Test Case] response = " + response);

        String left = "findUser：[UserInfo(id=";  // 左边界
        String right = ",";  // 右边界
        // 转为 int 类型，用于 removeUserById 的 id 参数
        id = Integer.parseInt(StringUtils.substringBetween(response, left, right));
        System.out.println("[Test Case] 查询结果：id = " + id);

        // 因为不能提前知道 id 的数值，所以此处断言 id 为正数（非负数）
        assertThat(id).isPositive().isNotNegative();
//    }

        /**
         * 3. 调用 removeUserById 接口，根据 id 删除用户
         */
//    @Test(priority = 3)
//    public void removeUser() {

        // 获取 url
        url = removeUserById_url;
        System.out.println("[Test Case] url = " + url);

        /**
         * 构造 NameValuePair（键值对）格式的数据
         */
        nameValuePairListParam = new ArrayList<NameValuePair>();
        /**
         * 初始化测试参数
         * 注：
         * 虽然，在研发代码和数据库定义中，id 是 int 类型，但是 new BasicNameValuePair() 键值对的参数，都只能
         * 传入 String 类型，所以是 ("id", String.valueOf(id))
         */
        nameValuePairListParam.add(new BasicNameValuePair("id", String.valueOf(id)));

        /**
         * doPostFormEntity() 方法说明：
         * 1. 传输报文是 form 表单（键值对）格式，即：代码中实现的 POST 请求，其参数类型使用了 @RequestParam 或不加任何 @Request 标识
         * 2. http 信息头使用 application/x-www-form-urlencoded 注：但是测试代码中的 httpPost.setHeader 并没有起到作用，这一句代码可以不写。
         * 3. 测试代码的 HttpEntity（实体）使用 UrlEncodedFormEntity
         */
        response = RequestUtils.doPostFormEntity(url, nameValuePairListParam, key);
        System.out.println("[Test Case] response = " + response);

        // 断言
        assertThat(response).contains("success");
//    }

        /**
         * 4. 调用 findUserById 查询用户接口 - 数据已经被删除，接口返回“查询失败”
         */
//    @Test(priority = 4)
//    public void findUserById() {

        // 获取 url
        String url = findUserById_url;
        System.out.println("[Test Case] url = " + url);

        /**
         * 构造 NameValuePair（键值对）格式的数据
         */
        nameValuePairListParam = new ArrayList<NameValuePair>();
        /**
         * 初始化测试参数
         * 注：
         * 虽然，在研发代码和数据库定义中，id 是 int 类型，但是 new BasicNameValuePair() 键值对的参数，都只能
         * 传入 String 类型，所以是 ("id", String.valueOf(id))
         */
        nameValuePairListParam.add(new BasicNameValuePair("id", String.valueOf(id)));

        /**
         * doPostFormEntity() 方法说明：
         * 1. 传输报文是 form 表单（键值对）格式，即：代码中实现的 POST 请求，其参数类型使用了 @RequestParam 或不加任何 @Request 标识
         * 2. http 信息头使用 application/x-www-form-urlencoded 注：但是测试代码中的 httpPost.setHeader 并没有起到作用，这一句代码可以不写。
         * 3. 测试代码的 HttpEntity（实体）使用 UrlEncodedFormEntity
         */
        response = RequestUtils.doPostFormEntity(url, nameValuePairListParam, key);
        System.out.println("[Test Case] response = " + response);

        /**
         * 返回数据中，是否包含“查询失败”
         */
        assertThat(response).contains("failed").as("这里可以添加一些断言的备注和描述信息");
    }
}
