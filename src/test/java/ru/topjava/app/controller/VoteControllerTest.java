package ru.topjava.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.topjava.app.dto.response.VoteForResponse;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class VoteControllerTest extends AbstractControllerTest {


    @Test
    void getAll() throws Exception {
        MvcResult res = mockMvc.perform(get("/vote/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        VoteForResponse[] actual = objectMapper.readValue(res.getResponse().getContentAsString(), VoteForResponse[].class);
        String data = getResourceFileContextAsString("classpath:vote/getAll.json");
        VoteForResponse[] expected = objectMapper.readValue(data, VoteForResponse[].class);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        assertEquals(Arrays.hashCode(expected), Arrays.hashCode(actual));
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/vote/44569c6f-c11f-4d0c-8578-b57b736bc079/6a1fd295-66c4-490b-b8de-24b8029a4db9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        VoteForResponse actual = objectMapper.readValue(res.getResponse().getContentAsString(), VoteForResponse.class);
        String data = getResourceFileContextAsString("classpath:vote/getById.json");
        VoteForResponse expected = objectMapper.readValue(data, VoteForResponse.class);
        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void save() throws Exception {
        MvcResult res = mockMvc.perform(post("/vote/save")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:vote/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}