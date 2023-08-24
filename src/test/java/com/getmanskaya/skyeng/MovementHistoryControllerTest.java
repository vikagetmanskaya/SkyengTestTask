package com.getmanskaya.skyeng;

import com.getmanskaya.skyeng.entity.MovementHistory;
import com.getmanskaya.skyeng.entity.MovementStatus;
import com.getmanskaya.skyeng.entity.PostOffice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getmanskaya.skyeng.entity.PostalItem;
import com.getmanskaya.skyeng.repository.PostOfficeRepository;
import com.getmanskaya.skyeng.repository.PostalItemRepository;
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
public class MovementHistoryControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    @Autowired
    private PostalItemRepository postalItemRepository;
    private PostOffice postOffice;
    private PostalItem postalItem;
    @PostConstruct
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        postOffice = postOfficeRepository.findById(2L).get();
        postalItem = postalItemRepository.findById(25L).get();

    }
    @Test
    public void createMovementHistory_success() throws Exception {
        MovementHistory movementHistory = new MovementHistory();
        movementHistory.setPostOffice(postOffice);
        movementHistory.setStatus(MovementStatus.SENDING);
        movementHistory.setPostalItem(postalItem);
        this.mockMvc.perform(post("/api/movement-histories")
                        .content(objectMapper.writeValueAsString(movementHistory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().string("Movement history id: " + movementHistory.getId()));

    }
    @Test
    public void testGetById() throws Exception {
        this.mockMvc.perform(get("/api/movement-histories/8"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
[MovementHistory{
id=1
, dateTime=2023-08-16T20:07:19
, postOffice=PostOffice{
id=1
, postcode=220, 222, 223
, name=BelPochta Minsk
}
, status=REGISTERED
, postalItem=PostalItem{
id=8
, identifier=20222020202020
, postalItemType=LETTER, recipientIndex=2200030
, recipientAddress=Kupala 11
, recipientName=Vika
, finalPostOffice=PostOffice{
id=1
, postcode=220, 222, 223
, name=BelPochta Minsk
}
}
}, MovementHistory{
id=2
, dateTime=2023-08-17T01:14:44
, postOffice=PostOffice{
id=1
, postcode=220, 222, 223
, name=BelPochta Minsk
}
, status=SENDING
, postalItem=PostalItem{
id=8
, identifier=20222020202020
, postalItemType=LETTER, recipientIndex=2200030
, recipientAddress=Kupala 11
, recipientName=Vika
, finalPostOffice=PostOffice{
id=1
, postcode=220, 222, 223
, name=BelPochta Minsk
}
}
}]"""));


    }
}