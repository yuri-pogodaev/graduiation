package ru.topjava.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.topjava.app.dto.response.UserForResponse;
import ru.topjava.app.repository.UserRepository;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class UserControllerTest extends AbstractControllerTest {
    //    @WithMockUser("user1@yandex.ru")
    @Autowired
    private UserRepository userRepository;

    @Test
    void getAll() throws Exception {
        MvcResult res = mockMvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        UserForResponse[] actual = objectMapper.readValue(res.getResponse().getContentAsString(), UserForResponse[].class);
        String data = getResourceFileContextAsString("classpath:user/getAll.json");
        UserForResponse[] expected = objectMapper.readValue(data, UserForResponse[].class);
        System.out.println(Arrays.toString(expected));
        assertEquals(Arrays.hashCode(expected), Arrays.hashCode(actual));
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }


    //    @WithMockUser("user1@yandex.ru")
    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/user/6a1fd295-66c4-490b-b8de-24b8029a4db9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        UserForResponse actual = objectMapper.readValue(res.getResponse().getContentAsString(), UserForResponse.class);
        String data = getResourceFileContextAsString("classpath:user/getById.json");
        UserForResponse expected = objectMapper.readValue(data, UserForResponse.class);
        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());

    }

    @WithMockUser("admin@gmail.com")
    @Test
    void update() throws Exception {

        MvcResult res = mockMvc.perform(put("/user/update/09898357-f0fa-45ab-acee-9d048894c89b")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:user/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void save() throws Exception {
        MvcResult res = mockMvc.perform(post("/user/save")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:user/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        //вытянуть или count
    }

    @Test
    void delete() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", "6a1fd295-66c4-490b-b8de-24b8029a4db9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        userRepository.count();
    }
}