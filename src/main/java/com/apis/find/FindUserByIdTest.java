package com.apis.find;

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

import static com.url.TestUrl.findUserById_url;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试场景：
 * 查询用户接口 - 根据 id 查询
 *
 * @author difeng
 */
public class FindUserByIdTest {

    /**
     * findUserById 接口的报文格式是 @RequestParam 所以：
     * 1. httpPost.setHeader 并没有起到实际作用，随意写或不写 setHeader 都不影响 http 请求的运行结果。
     * 2. httpPost.setEntity 使用的 entity 是 UrlEncodedFormEntity
     */
    @Test
    public void findUserById_success() throws IOException {

        // 1. 请求地址
        String url = findUserById_url;
        System.out.println("[Test Case] url = " + url);

        // 2. 创建 CloseableHttpClient 对象，用于执行 execute(httpPost) 并获取 CloseableHttpResponse 响应对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 3. 创建 HttpPost 对象，并设置 url
        HttpPost httpPost = new HttpPost(url);  // public HttpPost(final String uri)

        // 4. 设置 http 请求的 header 信息头
        // 代码实现使用的是 @RequestParam 来标识参数类型，这时候 httpPost.setHeader 并没有起到实际作用。
        // 随意写或不写 setHeader 都不影响 http 请求的运行结果。
//        httpPost.setHeader("content", "application/x-www-form-urlencoded");
//        httpPost.setHeader("11", "11");  // 因为这个 content 是默认的，写了或者写错了，都不会起作用。

        // 5. 构造 NameValuePair（键值对）格式的数据
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("id", "1"));  // public BasicNameValuePair(final String name, final String value)

        // 6. 创建 UrlEncodedFormEntity 对象
        UrlEncodedFormEntity fromEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");

        // 7. httpPost 设置具体的 entity（本例是 UrlEncodedFormEntity）
        httpPost.setEntity(fromEntity);  // public void setEntity(final HttpEntity entity)

        // 8. 创建 CloseableHttpResponse 对象，用于接收 httpClient 发送 post 请求的响应数据
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);  // public CloseableHttpResponse execute(final HttpHost target, final HttpRequest request)

        // 9. 通过 EntityUtils.toString 输出响应数据的报文实体 httpResponse.getEntity()
        String response = EntityUtils.toString(httpResponse.getEntity());
        System.out.println("[Test Case] response = " + response);

        // 10. 断言
        assertThat(response.contains("success")).isEqualTo(true);
    }
}
