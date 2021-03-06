package com.birtu.oj.allsearch.nai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.birtu.oj.allsearch.nai.repository")
public class NaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaiApplication.class, args);
    }

}
