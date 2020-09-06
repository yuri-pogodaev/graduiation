package ru.topjava.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.topjava.app.dto.response.RestaurantForResponse;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class RestaurantControllerTest extends AbstractControllerTest {

    @Test
    void getAll() throws Exception {
        MvcResult res = mockMvc.perform(get("/restaurant/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        RestaurantForResponse[] actual = objectMapper.readValue(res.getResponse().getContentAsString(), RestaurantForResponse[].class);
        String data = getResourceFileContextAsString("classpath:restaurant/getAll.json");
        RestaurantForResponse[] expected = objectMapper.readValue(data, RestaurantForResponse[].class);
//        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        assertEquals(Arrays.hashCode(expected), Arrays.hashCode(actual));
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/restaurant/44569c6f-c11f-4d0c-8578-b57b736bc079")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        RestaurantForResponse actual = objectMapper.readValue(res.getResponse().getContentAsString(), RestaurantForResponse.class);
        String data = getResourceFileContextAsString("classpath:restaurant/getById.json");
        RestaurantForResponse expected = objectMapper.readValue(data, RestaurantForResponse.class);
        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void save() throws Exception {
        MvcResult res = mockMvc.perform(post("/restaurant/save")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:restaurant/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void delete() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/restaurant/{id}", "44569c6f-c11f-4d0c-8578-b57b736bc079")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void update() throws Exception {
        MvcResult res = mockMvc.perform(put("/restaurant/update/44569c6f-c11f-4d0c-8578-b57b736bc079")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:restaurant/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}