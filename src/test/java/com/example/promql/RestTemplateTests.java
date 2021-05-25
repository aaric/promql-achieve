package com.example.promql;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateTests
 *
 * @author Aaric, created on 2020-11-23T09:00.
 * @version 0.4.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RestTemplateTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGet() {
        String url = "https://jsonplaceholder.typicode.com/albums/1";
        Albums albums = restTemplate.getForObject(url, Albums.class);

        log.info("{}", albums);
        Assertions.assertNotNull(albums);
    }

    @Data
    public static class Albums {
        private Long id;
        private Integer userId;
        private String title;
    }
}
