package com.progerslifes.diplom.solr.controller;

import com.google.gson.Gson;
import com.progerslifes.diplom.solr.service.DataImportService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DataImportController {

    @Autowired
    private DataImportService dataImportService;

    @PostMapping("/data/import")
    public String dataImport(@RequestParam("command") String command) throws IOException, SolrServerException {
        return queryResponseToJson(dataImportService.dataImport(command));
    }

    private String queryResponseToJson(QueryResponse queryResponse) {
        Gson gson = new Gson();
        return gson.toJson(queryResponse.getResponse());
    }
}
