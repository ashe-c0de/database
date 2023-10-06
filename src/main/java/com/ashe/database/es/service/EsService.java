package com.ashe.database.es.service;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EsService {

    /**
     * 异步非阻塞式client
     */
    private final ElasticsearchAsyncClient elasticsearchAsyncClient;

    public EsService(ElasticsearchAsyncClient elasticsearchAsyncClient) {
        this.elasticsearchAsyncClient = elasticsearchAsyncClient;
    }

    public void creatIndex() {
        log.info("start");
        elasticsearchAsyncClient
                // 异步创建索引
                .indices().create(c -> c.index("products"))
                // 执行完后的回调函数
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        // 执行失败
                        log.error("products exists", exception);
                    } else {
                        // 执行成功
                        log.info("products has build");
                    }
                });
        log.info("end");
    }
}
