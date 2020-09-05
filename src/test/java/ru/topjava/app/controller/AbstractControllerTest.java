package ru.topjava.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.topjava.app.config.TestConfig;
import ru.topjava.app.config.TestSeecurityConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class, TestSeecurityConfig.class})
@AutoConfigureMockMvc
@Rollback
@Transactional
public abstract class AbstractControllerTest {
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected String getResourceFileContextAsString(String filepath) throws IOException {
        File file = getResourceFile(filepath);
        return FileUtils.readFileToString(file, Charset.availableCharsets().get("UTF-8"));
    }

    protected File getResourceFile(String filepath) throws IOException {
        return resourceLoader.getResource(filepath).getFile();
    }
//    @Autowired
//    private ContextWrapper contextWrapper;

}
