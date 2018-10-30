package com.xiangzhurui.example.demo.restful.config;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiangzhurui
 * @version 2018/10/29 20:14
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        return clientHttpRequestFactory;
    }

    /**
     * 使用连接池的 HttpClient 实例
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setConnectionManager(getPoolingHttpClientConnectionManager())
                .setConnectionManagerShared(true)
                .setDefaultRequestConfig(getRequestConfig())
                // 设置重试次数
                .setRetryHandler(getRetryHandler())
                .build();
        return closeableHttpClient;
    }

    /**
     * HTTP 请求重试次数
     *
     * @return
     */
    private DefaultHttpRequestRetryHandler getRetryHandler() {
        return new DefaultHttpRequestRetryHandler(3, false);
    }


    /**
     * HTTP 连接池配置
     *
     * @return
     */
    private PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {

        SSLConnectionSocketFactory sslConnectionSocketFactory = getSslConnectionSocketFactory();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory)
                .build();

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        // 连接池最大并发连接数
        poolingHttpClientConnectionManager.setMaxTotal(100);

        // 单路由最大并发数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);

        return poolingHttpClientConnectionManager;
    }

    private SSLConnectionSocketFactory getSslConnectionSocketFactory() {
        TrustManager trustManager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("init SSLContext fail! ", e);
        }
        return new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
    }

    public SSLConnectionSocketFactory getSslConnectionSocketFactory1() {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustAllStrategy());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build());
            return sslConnectionSocketFactory;
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP 请求配置
     *
     * @return
     */
    private RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(200)
                .setSocketTimeout(500)
                .setConnectionRequestTimeout(200)
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .build();
        return requestConfig;
    }


}
