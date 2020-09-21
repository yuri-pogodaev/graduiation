package ru.topjava.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.topjava.app.dto.response.MenuItemForResponse;
import ru.topjava.app.dto.response.RestaurantForResponse;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    @Test
    void create() throws Exception {
        MvcResult res = mockMvc.perform(post("/admin/restaurants")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:restaurant/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
    }

    @Test
    void update() throws Exception {
        MvcResult res = mockMvc.perform(put("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:restaurant/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    void delete() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/admin/restaurants/{id}", "44569c6f-c11f-4d0c-8578-b57b736bc079")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079")
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
    void getAll() throws Exception {
        MvcResult res = mockMvc.perform(get("/admin/restaurants/")
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
    void save() throws Exception {
        MvcResult res = mockMvc.perform(post("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:menudishes/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
    }

    @Test
    void testUpdate() throws Exception{
        MvcResult res = mockMvc.perform(put("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus/fea3f331-8a69-4062-9c5c-66e9a1564833")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:menudishes/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteMenu() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus/d2f63f20-da39-4606-9aae-1c492d3c4a42", "d2f63f20-da39-4606-9aae-1c492d3c4a42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    void getAllMenu() throws Exception {
        MvcResult res = mockMvc.perform(get("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MenuItemForResponse[] actual = objectMapper.readValue(res.getResponse().getContentAsString(), MenuItemForResponse[].class);
        String data = getResourceFileContextAsString("classpath:menudishes/getAll.json");
        MenuItemForResponse[] expected = objectMapper.readValue(data, MenuItemForResponse[].class);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        assertEquals(Arrays.hashCode(expected), Arrays.hashCode(actual));
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    void getMenuById() throws Exception {
        MvcResult res = mockMvc.perform(get("/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus/d2f63f20-da39-4606-9aae-1c492d3c4a42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        MenuItemForResponse actual = objectMapper.readValue(res.getResponse().getContentAsString(), MenuItemForResponse.class);
        String data = getResourceFileContextAsString("classpath:menudishes/getById.json");
        MenuItemForResponse expected = objectMapper.readValue(data, MenuItemForResponse.class);
        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());
    }

}