package com.progerslifes.diplom.solr.controller;

import com.progerslifes.diplom.solr.service.SolrSuggestService;
import com.progerslifes.diplom.solr.service.SuggestedTerm;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SuggestController {
    @Autowired
    private SolrSuggestService solrSuggestService;

    @PostMapping("/suggest")
    public List<SuggestedTerm> getSuggestions(@RequestParam("query") String query) throws IOException, SolrServerException {
        return solrSuggestService.getSuggestions(query);
    }
}
