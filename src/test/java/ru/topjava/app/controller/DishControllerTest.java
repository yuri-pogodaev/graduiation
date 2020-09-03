package ru.topjava.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.topjava.app.dto.response.DishForResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

class DishControllerTest extends AbstractControllerTest {

    @Test
    void getAll() {
    }

    @WithMockUser("user1@yandex.ru")
    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/dish/06d18c89-1461-48ad-bcfa-a726a51d3ec3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String json = res.getResponse().getContentAsString();
        DishForResponse actual = objectMapper.readValue(json, DishForResponse.class);
        String data = getResourceFileContextAsString("classpath:dish/getById.json");
        DishForResponse expected = objectMapper.readValue(data, DishForResponse.class);

        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    void save() {
    }

    @Test
    void delete() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/dish/{id}", "06d18c89-1461-48ad-bcfa-a726a51d3ec3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @WithMockUser("admin@gmail.com")
    @Test
    void update() throws Exception {

        MvcResult res = mockMvc.perform(put("/dish/update/09f79f73-5d25-4461-85d8-2e1bdf9420e9")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:dish/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}