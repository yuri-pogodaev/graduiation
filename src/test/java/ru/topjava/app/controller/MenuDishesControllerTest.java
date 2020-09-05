package ru.topjava.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.topjava.app.dto.response.MenuDishesForResponse;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class MenuDishesControllerTest extends AbstractControllerTest {

    @Test
    void getAll() throws Exception {
        MvcResult res = mockMvc.perform(get("/menuDishes/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MenuDishesForResponse[] actual = objectMapper.readValue(res.getResponse().getContentAsString(), MenuDishesForResponse[].class);
        String data = getResourceFileContextAsString("classpath:menudishes/getAll.json");
        MenuDishesForResponse[] expected = objectMapper.readValue(data, MenuDishesForResponse[].class);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        assertEquals(Arrays.hashCode(expected), Arrays.hashCode(actual));
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/menuDishes/d2f63f20-da39-4606-9aae-1c492d3c4a42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        MenuDishesForResponse actual = objectMapper.readValue(res.getResponse().getContentAsString(), MenuDishesForResponse.class);
        String data = getResourceFileContextAsString("classpath:menudishes/getById.json");
        MenuDishesForResponse expected = objectMapper.readValue(data, MenuDishesForResponse.class);
        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void delete() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/menuDishes/{id}", "d2f63f20-da39-4606-9aae-1c492d3c4a42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    @Test
    void save() throws Exception {
        MvcResult res = mockMvc.perform(post("/menuDishes/save")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:menudishes/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }


    @Test
    void update() throws Exception {
        MvcResult res = mockMvc.perform(put("/menuDishes/update/d2f63f20-da39-4606-9aae-1c492d3c4a42")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:menudishes/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}