package com.progerslifes.diplom.solr.service.impl;

import com.progerslifes.diplom.solr.service.DataImportService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataImportServiceImpl implements DataImportService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public QueryResponse dataImport(String command) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/dataimport");
        solrQuery.setParam("command", command);

        return solrClient.query("progerslifes", solrQuery);
    }
}
