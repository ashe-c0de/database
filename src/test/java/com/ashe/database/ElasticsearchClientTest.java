package com.ashe.database;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
@Slf4j
@SuppressWarnings("unused")
public class ElasticsearchClientTest {

    @Resource
    private ElasticsearchProperties elasticsearchProperties;
    @Resource
    private ElasticsearchClient elasticsearchClient;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    void uri() {
        List<String> uris = elasticsearchProperties.getUris();
        Iterator<String> iterator = uris.iterator();
        if (iterator.hasNext()) {
            log.info(iterator.next());
        }
    }

    /**
     * 索引
     */
    @Test
    @SuppressWarnings("all")
    void index() {
        Assertions.assertDoesNotThrow(() -> {
            // 创建索引products
            elasticsearchClient.indices().create(c -> c.index("products"));
            // 查询索引
//            GetIndexResponse response = elasticsearchClient.indices().get(builder -> builder.index("products"));
//            Map<String, IndexState> result = response.result();
//            System.out.println(result);
            // 判断users索引是否存在文档ID为001的文档
//            BooleanResponse exists = elasticsearchClient.exists(builder -> builder.index("users").id("001"));
//            if (exists.value())
//                log.info("yes, it has.");
            // 删除索引
//            DeleteIndexResponse deleteResponse = elasticsearchClient.indices().delete(builder -> builder.index("women"));
//            boolean acknowledged = deleteResponse.acknowledged();
//            log.info(String.valueOf(acknowledged));
        });
    }

}
