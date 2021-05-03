package com.progerslifes.diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@EnableJpaRepositories("com.progerslifes.diplom.repository")
@EnableSolrRepositories("com.progerslifes.diplom.solr.repository")
@SpringBootApplication
public class DiplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);
    }

}
