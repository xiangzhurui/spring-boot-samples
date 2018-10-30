package com.xiangzhurui.example.demo.restful.config;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import com.google.common.collect.Maps;
import com.xiangzhurui.example.demo.restful.RestfulDemoApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

/**
 * @author xiangzhurui
 * @version 2018/10/29 20:16
 */
@Slf4j
public class QueryParamsUrlTemplateHandlerTest extends RestfulDemoApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void expand() {

        QueryParamsUrlTemplateHandler queryParamsUrlTemplateHandler = new QueryParamsUrlTemplateHandler();
        Map<String, String> uriVariables = Maps.newHashMap();

        uriVariables.put("q", "测试用例");

        String uriTemplate = "https://cn.bing.com/search";
        URI uri = queryParamsUrlTemplateHandler.expand(uriTemplate, uriVariables);
        System.out.println(uri.toString());

        System.out.println(new DefaultUriTemplateHandler().expand("https://cn.bing.com/search/{q}", uriVariables).toASCIIString());


        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setUriTemplateHandler(queryParamsUrlTemplateHandler);
        String map = restTemplate.getForObject(uriTemplate, String.class, uriVariables);
        System.out.println(map);
    }

    @Test
    public void test() throws IOException {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        HttpClient httpClient = restTemplateConfig.httpClient();

        HttpResponse httpResponse = httpClient.execute(new HttpGet("https://cn.bing.com"));
        HttpEntity httpEntity = httpResponse.getEntity();
        String s = EntityUtils.toString(httpEntity);

        System.out.println(s);

    }

    @Test
    public void testGet() {
        QueryParamsUrlTemplateHandler queryParamsUrlTemplateHandler = new QueryParamsUrlTemplateHandler();
        Map<String, String> queryVariables = Maps.newHashMap();

        queryVariables.put("q", "测试用例");

        String uriTemplate = "https://cn.bing.com/search";
        URI uri = queryParamsUrlTemplateHandler.expand(uriTemplate, queryVariables);

        restTemplate.setUriTemplateHandler(queryParamsUrlTemplateHandler);
        ResponseEntity<String> bingStr = restTemplate.getForEntity(uriTemplate, String.class, queryVariables);

        log.info("访问必应搜索首页结果：{},{}", bingStr.getStatusCode().toString(),bingStr.getStatusCode().getReasonPhrase());
        String s = Jsoup.parse(bingStr.getBody()).html();
        log.info("格式化后的bing首页：\n {}", s);
    }
}