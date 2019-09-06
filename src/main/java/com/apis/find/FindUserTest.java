package com.apis.find;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.url.TestUrl.findUser_url;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试场景：
 * 查询用户接口 - 根据任意字段查询
 *
 * @author difeng
 */
public class FindUserTest {

    /**
     * findUser 接口的报文格式是 @RequestBody 所以：
     * 1. httpPost.setHeader 使用的 content-type 是 application/json
     * 2. httpPost.setEntity 使用的 entity 是 StringEntity
     */
    @Test
    public void findUser_success() throws IOException {

        // 1. 请求地址
        String url = findUser_url;
        System.out.println("[Test Case] url = " + url);

        // 2. 创建 CloseableHttpClient 对象，用于执行 execute(httpPost) 并获取 CloseableHttpResponse 响应对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 3. 创建 HttpPost 对象，并设置 url
        HttpPost httpPost = new HttpPost(url);  // public HttpPost(final String uri)

        // 4. 设置 http 请求的 header 信息头
        httpPost.setHeader("content-type", "application/json");  // public void setHeader(final String name, final String value)

        // 5. 构造数据
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("sex", "1");

        // 6. 将构造的 Map 类型转为 json 格式的字符串，用于 StringEntity() 构造函数的参数
        String body = JSONObject.toJSONString(param);

        // 7. 创建 StringEntity 对象，因为 header 的 content-type 是 application/json
        StringEntity stringEntity = new StringEntity(body, "utf-8");  // public StringEntity(final String string, final String charset)

        // 8. httpPost 设置具体的 entity（本例是 StringEntity）
        httpPost.setEntity(stringEntity);  // public void setEntity(final HttpEntity entity)

        // 9. 创建 CloseableHttpResponse 对象，用于接收 httpClient 发送 post 请求的响应数据
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);  // public CloseableHttpResponse execute(final HttpHost target, final HttpRequest request)

        // 10. 通过 EntityUtils.toString 输出响应数据的报文实体 httpResponse.getEntity()
        String response = EntityUtils.toString(httpResponse.getEntity());  // public static String toString(final HttpEntity entity)
        System.out.println("[Test Case] response = " + response);

        // 11. 断言 - 函数原型 public boolean contains(CharSequence s)
        assertThat(response).contains("findUser：[UserInfo").as("这里可以添加一些断言的备注和描述信息");
        assertThat(response).contains("findUser：[UserInfo").contains("userName").as("这里可以添加一些断言的备注和描述信息");
        // 断言 - 另一种用法
        assertThat(response.contains("findUser：[UserInfo")).isEqualTo(true).as("这里可以添加一些断言的备注和描述信息");
    }
}
