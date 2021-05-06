package com.progerslifes.diplom.solr.service.impl;

import com.progerslifes.diplom.solr.document.User;
import com.progerslifes.diplom.solr.repository.UserSolrRepository;
import com.progerslifes.diplom.solr.service.SolrSuggestService;
import com.progerslifes.diplom.solr.service.SuggestedTerm;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.client.solrj.response.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolrSuggestServiceImpl implements SolrSuggestService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private UserSolrRepository userSolrRepository;

    @Value("${suggest.dictionary}")
    private String suggestDictionary;

    @Override
    public List<SuggestedTerm> getSuggestions(String query) throws IOException, SolrServerException {
        List<Suggestion> suggestions = createSuggestions(query);
        List<SuggestedTerm> suggestedTerms;
        if (!suggestions.isEmpty()) {
            suggestedTerms = suggestions.stream()
                    .map(suggestion -> new SuggestedTerm(suggestion.getTerm(), suggestion.getPayload()))
                    .collect(Collectors.toList());
        } else {
            List<User> users =  userSolrRepository.getUserByUsernameOrByName(query, query);
            suggestedTerms = users.stream()
                    .map(user -> new SuggestedTerm(user.getUsername(), user.getPicturePath()))
                    .collect(Collectors.toList());
        }
        return suggestedTerms;
    }

    private List<Suggestion> createSuggestions(String query) throws SolrServerException, IOException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/suggest");
        solrQuery.setParam("suggest", true);
        solrQuery.setParam("suggest.build", true);
        solrQuery.setParam("suggest.dictionary", suggestDictionary);
        solrQuery.setParam("suggest.q", query);

        QueryResponse queryResponse = solrClient.query("progerslifes", solrQuery);
        SuggesterResponse suggesterResponse = queryResponse.getSuggesterResponse();
        return suggesterResponse.getSuggestions().get("usernameSuggester");
    }
}
