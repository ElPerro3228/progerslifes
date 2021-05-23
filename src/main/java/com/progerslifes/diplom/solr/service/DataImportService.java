package com.progerslifes.diplom.solr.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;

public interface DataImportService {
    QueryResponse dataImport(final String command) throws IOException, SolrServerException;
}
