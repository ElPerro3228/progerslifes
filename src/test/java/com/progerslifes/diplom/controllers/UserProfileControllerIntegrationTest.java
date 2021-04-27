package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.DiplomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DiplomApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class UserProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFollowings() throws Exception {
        mockMvc.perform(get("/profile/slohvin/following")
                .with(user("slohvin").password("Stas1234").roles("USER"))
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("username", is("anonymousUser")),
                                hasProperty("userProfile", notNullValue())
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("username", is("psviridiv")),
                                hasProperty("userProfile", notNullValue())
                        )
                )));
    }

    @Test
    public void testGetFollowers() throws Exception {
        mockMvc.perform(get("/profile/psviridiv/followers")
                .with(user("slohvin").password("Stas1234").roles("USER"))
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
                .andExpect(model().attribute("users", hasSize(1)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("username", is("slohvin")),
                                hasProperty("userProfile", notNullValue())
                        )
                )));
    }

}
