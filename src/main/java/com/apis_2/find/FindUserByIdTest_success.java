package com.apis_2.find;

import com.utils.RequestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.env.Env_test.testKey;
import static com.url.TestUrl.findUserById_url;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试前提：
 * 1. 确保数据库中有一条 id 为 100 的用户信息（用于查询操作）
 * --------------------
 * 测试场景：
 * 1. 调用 findUserById 根据 id 查询用户接口 - application/x-www-form-urlencoded - UrlEncodedFormEntity
 *
 * @author difeng
 */
public class FindUserByIdTest_success {

    // 加密密钥 - 相同的环境，通常密钥也是相同的，所以在先对其定义并赋值。在本例中 key 只是做样子，并没有实际作用。
    private String key = testKey;

    /**
     * 定义变量（这些变量在测试代码中会多次使用）
     */
    private String url = null;  // 接口地址
    List<NameValuePair> nameValuePairListParam = null;  // 构造请求参数
    private String response = null;  // 接收响应报文

    /**
     * 初始化测试参数 - 初始化 User 数据，用于“查询用户”
     */
    //注：虽然，在研发代码和数据库中定义的 id 是 int 类型，
    // 但是 new BasicNameValuePair("", "") 的参数是两个字符串的“键值对”格式，
    // 所以只能传入 String 类型，如：("id", "100")
    private String id = "100";

    @Test
    public void findUserById_success() {

        // 获取 url
        url = findUserById_url;
        System.out.println("[Test Case] url = " + url);

        /**
         * 构造 NameValuePair（键值对）格式的数据
         */
        nameValuePairListParam = new ArrayList<NameValuePair>();
        /**
         * 初始化测试参数
         * 注：
         * 虽然，在研发代码和数据库定义中，id 是 int 类型，但是 new BasicNameValuePair() 键值对的参数，都只能
         * 传入 String 类型，所以是 ("id", "100")
         */
        nameValuePairListParam.add(new BasicNameValuePair("id", id));

        /**
         * doPostFormEntity() 方法说明：
         * 1. 传输报文是 form 表单（键值对）格式，即：代码中实现的 POST 请求，其参数类型使用了 @RequestParam 或不加任何 @Request 标识
         * 2. http 信息头使用 application/x-www-form-urlencoded 注：但是测试代码中的 httpPost.setHeader 并没有起到作用，这一句代码可以不写。
         * 3. 测试代码的 HttpEntity（实体）使用 UrlEncodedFormEntity
         */
        response = RequestUtils.doPostFormEntity(url, nameValuePairListParam, key);
        System.out.println("[Test Case] response = " + response);

        /**
         * 返回数据中，是否包含“查询成功”和“dazhentan”
         */
        assertThat(response).contains("success").contains("dazhentan").as("这里可以添加一些断言的备注和描述信息");
    }
}
