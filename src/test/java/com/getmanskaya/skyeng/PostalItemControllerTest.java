package com.getmanskaya.skyeng;

import com.getmanskaya.skyeng.entity.PostOffice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getmanskaya.skyeng.entity.PostalItem;
import com.getmanskaya.skyeng.entity.PostalItemType;
import com.getmanskaya.skyeng.repository.PostOfficeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = SkyengApplication.class)
@WebAppConfiguration
public class PostalItemControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PostOfficeRepository postOfficeRepository;

    @PostConstruct
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createPostalItem_success() throws Exception {
        PostalItem postalItem = new PostalItem();
        postalItem.setIdentifier("5058");
        postalItem.setPostalItemType(PostalItemType.POSTCARD);
        postalItem.setRecipientIndex("20023");
        postalItem.setRecipientAddress("Lesnaya 30");
        postalItem.setRecipientName("Natasha");
        PostOffice postOffice = postOfficeRepository.findById(2L).get();
        postalItem.setFinalPostOffice(postOffice);
        this.mockMvc.perform(post("/api/postal-items/1")
                        .content(objectMapper.writeValueAsString(postalItem))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().string("Postal item id: " + postalItem.getId()));

    }
}

