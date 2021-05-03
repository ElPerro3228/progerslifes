package com.progerslifes.diplom.solr.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SolrSuggestService {

    List<SuggestedTerm> getSuggestions(String query) throws IOException, SolrServerException;
}
