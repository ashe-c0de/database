package com.ashe.database.es.conf;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java Client: 8.10
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/connecting.html">api reference</a>
 */
@Configuration
@RequiredArgsConstructor
public class Conf {

    private final ElasticsearchProperties elasticsearchProperties;

    // 使用原子类
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    /**
     * Create the low-level client
     */
    @Bean
    public RestClient restClient() {
        List<String> uris = elasticsearchProperties.getUris();
        if (ObjectUtils.isEmpty(uris)) {
            throw new ElasticsearchUriException("Elasticsearch URI is not configured.");
        }
        return RestClient
                .builder(HttpHost.create(getNextUri(uris)))
                /*
                    TODO 身份验证标头，设置具有特定请求头时，才能成功访问ElasticSearch
                    .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                    })
                 */
                .setDefaultHeaders(new Header[]{})
                .build();
    }

    /**
     * Create the transport with a Jackson mapper
     */
    @Bean
    public ElasticsearchTransport transport() {
        return new RestClientTransport(
                restClient(), new JacksonJsonpMapper());
    }

    /**
     * And create the API client
     * Synchronous blocking client 同步阻塞式client
     */
    @Bean
    public ElasticsearchClient syncClient() {
        return new ElasticsearchClient(transport());
    }

    /**
     * Asynchronous non-blocking client 异步非阻塞式client
     */
    @Bean
    public ElasticsearchAsyncClient asyncClient() {
        return new ElasticsearchAsyncClient(transport());
    }

    // 获取下一个 URI，使用轮询算法
    private String getNextUri(List<String> uris) {
        // 单机
        if (uris.size() == 1) {
            return uris.get(0);
        }
        // 集群
        synchronized (this) {
            int index = currentIndex.getAndIncrement();
            if (index >= uris.size()) {
                index = 0;
                currentIndex.set(index);
            }
            return uris.get(index);
        }
    }

}
