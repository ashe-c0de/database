package com.ashe.database.es.conf;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ElasticsearchUriException extends RuntimeException {

    public ElasticsearchUriException(String message) {
        super(message);
    }

}
