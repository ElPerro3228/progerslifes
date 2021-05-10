package com.progerslifes.diplom.validation.validators;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.security.cert.CertificateRevokedException;

@Component
public class GitHubAccountValidator implements Validator {

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Override
    public boolean supports(Class<?> aClass) {
        return String.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (supports(o.getClass())) {
            String githubLogin = (String) o;
            try {
                HttpResponse<String> response = Unirest.get(githubApiUrl + githubLogin).asString();
                verifyLogin(response, errors);
            } catch (UnirestException | JsonProcessingException e) {
                errors.rejectValue("githubLogin","Cannot access github login");
            }
        }
    }

    private void verifyLogin(HttpResponse<String> response, Errors errors) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (mapper.readTree(response.getBody()).get("id") == null) {
            errors.rejectValue("githubLogin", "validation.githubForm.login","Cannot access github login");
        }
    }
}
