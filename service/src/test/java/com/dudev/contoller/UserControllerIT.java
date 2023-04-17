package com.dudev.contoller;

import com.dudev.basetest.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.dudev.dto.UserCreateEditDto.Fields.address;
import static com.dudev.dto.UserCreateEditDto.Fields.fullName;
import static com.dudev.dto.UserCreateEditDto.Fields.password;
import static com.dudev.dto.UserCreateEditDto.Fields.phoneNumber;
import static com.dudev.dto.UserCreateEditDto.Fields.role;
import static com.dudev.dto.UserCreateEditDto.Fields.username;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class UserControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(5)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                .param(username, "tester")
                .param(role, "role")
                .param(password, "password")
                .param(address, "address")
                .param(phoneNumber, "phoneNumber")
                .param(fullName, "fullname"))
        .andExpectAll(status().is3xxRedirection(),
                redirectedUrlPattern("/users/*"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/users/*/update")
                .param(username, "tester")
                .param(role, "role")
                .param(password, "password")
                .param(address, "address")
                .param(phoneNumber, "phoneNumber")
                .param(fullName, "fullname"))
                .andExpectAll(status().is3xxRedirection(), redirectedUrlPattern("/users/*"));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/users/*/delete")
                .param("id", "1"))
                .andExpectAll(status().is3xxRedirection(), redirectedUrl("/users"));
    }
}
