package com.apis.remove;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.url.TestUrl.removeUserById_url;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试场景：
 * 删除用户接口 - 根据 id 删除
 *
 * @author difeng
 */
public class RemoveUserByIdTest {

    /**
     * removeUserById 接口的报文格式是 @RequestParam 所以：
     * 1. httpPost.setHeader 并没有起到实际作用，随意写或不写 setHeader 都不影响 http 请求的运行结果。
     * 2. httpPost.setEntity 使用的 entity 是 UrlEncodedFormEntity
     */
    @Test
    public void removeUserById_fail() throws IOException {

        // 1. 请求地址
        String url = removeUserById_url;
        System.out.println("[Test Case] url = " + url);

        // 2. 创建 CloseableHttpClient 对象，用于执行 execute(httpPost) 并获取 CloseableHttpResponse 响应对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 3. 创建 HttpPost 对象，并设置 url
        HttpPost httpPost = new HttpPost(url);  // public HttpPost(final String uri)

        // 4. 设置 http 请求的 header 信息头
        // 代码实现使用的是 @RequestParam 来标识参数类型，这时候 httpPost.setHeader 并没有起到实际作用。
        // 随意写或不写 setHeader 都不影响 http 请求的运行结果。
//        httpPost.setHeader("", "");
//        httpPost.setHeader("sdfasdf", "testsefas");

        // 5. 构造 NameValuePair（键值对）格式的数据
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("id", "133"));  // public BasicNameValuePair(final String name, final String value)

        // 6. 创建 UrlEncodedFormEntity 对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");  // throws UnsupportedEncodingException

        // 7. httpPost 设置具体的 entity（本例是 UrlEncodedFormEntity）
        httpPost.setEntity(formEntity);  // public void setEntity(final HttpEntity entity)

        // 8. 创建 CloseableHttpResponse 对象，用于接收 httpClient 发送 post 请求的响应数据
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);  // public CloseableHttpResponse execute(final HttpHost target, final HttpRequest request)

        // 9. 通过 EntityUtils.toString 输出响应数据的报文实体 httpResponse.getEntity()
        String response = EntityUtils.toString(httpResponse.getEntity());
        System.out.println("[Test Case] response = " + response);

        // 10. 断言 - 函数原型 public boolean contains(CharSequence s)
        assertThat(response).contains("failed").as("这里可以添加一些断言的备注和描述信息");
        // 断言 - 另一种用法
        assertThat(response.contains("failed")).isEqualTo(true).as("这里可以添加一些断言的备注和描述信息");
    }
}
