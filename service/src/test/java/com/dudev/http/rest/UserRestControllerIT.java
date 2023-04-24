package com.dudev.http.rest;

import com.dudev.basetest.IntegrationTestBase;
import com.dudev.dto.UserCreateEditDto;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.repository.UserRepository;
import com.dudev.service.ImageService;
import com.dudev.service.UserService;
import com.dudev.util.EntityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
@WithMockUser(username = "testUsername", password = "testPassword", authorities = "ADMIN")
public class UserRestControllerIT extends IntegrationTestBase {

    private final String REST_URL = "/api/v1/users";
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final EntityManager entityManager;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Test
    void findAvatar() throws Exception {
        User user = User.builder()
                .phoneNumber("89283284834")
                .username("Transformer111")
                .role(Role.USER)
                .fullName("Optimus Prime")
                .password("AutobotsAreTheBest2233")
                .image("10-04-2021_21-21-09_2.jpg")
                .build();
        userRepository.saveAndFlush(user);
        byte[] expectedContent = imageService.get(user.getImage()).orElseThrow();

        mockMvc.perform(get(String.format(REST_URL + "/%d/avatar", user.getId())))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        content().contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE),
                        content().bytes(expectedContent)
                );
    }

    @Test
    void findAllEmptyFilter() throws Exception {
        List<User> users = EntityUtil.getUsers();
        EntityUtil.insertUsers(entityManager);

        mockMvc.perform(get(REST_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[*].username", containsInAnyOrder("Transformer111",
                        "NightWatcher11",
                        "Morty14",
                        "Pope",
                        "williamson")));
    }

    @Test
    void findById() throws Exception {
        User user = User.builder()
                .phoneNumber("89283284834")
                .username("Transformer111")
                .role(Role.USER)
                .fullName("Optimus Prime")
                .password("AutobotsAreTheBest2233")
                .image("10-04-2021_21-21-09_2.jpg")
                .build();
        userRepository.saveAndFlush(user);

        mockMvc.perform(get(REST_URL + "/" + user.getId()))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        jsonPath("$.username").value(user.getUsername())
                );
    }
// TODO: 24.04.2023 create with multipart?
//    @Test
//    void create() throws Exception {
//        String imageName = "10-04-2021_21-21-09_2.jpg";
//        byte[] image = imageService.get(imageName).orElseThrow();
//
//        MockMultipartFile imageFile = new MockMultipartFile("Img", imageName + "Test",
//                MediaType.APPLICATION_OCTET_STREAM_VALUE,
//                image);
//        UserCreateEditDto user = new UserCreateEditDto("Ivan Ivanov", "Mark123", Role.USER,
//                "234234", "pass", "addr", null);
//
//        mockMvc.perform(multipart(REST_URL)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isCreated());
//    }

    @Test
    void create() throws Exception {
        UserCreateEditDto user = new UserCreateEditDto("Ivan Ivanov", "Mark123", Role.USER,
                "234234", "pass", "addr", null);

        mockMvc.perform(post(REST_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        User user = User.builder()
                .phoneNumber("89283284834")
                .username("Transformer111")
                .role(Role.USER)
                .fullName("Optimus Prime")
                .password("AutobotsAreTheBest2233")
                .image("10-04-2021_21-21-09_2.jpg")
                .build();
        userRepository.saveAndFlush(user);
        user.setRole(Role.ADMIN);

        mockMvc.perform(put(REST_URL + "/" + user.getId())
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void delete() throws Exception {
        User user = User.builder()
                .phoneNumber("89283284834")
                .username("Transformer111")
                .role(Role.USER)
                .fullName("Optimus Prime")
                .password("AutobotsAreTheBest2233")
                .image("10-04-2021_21-21-09_2.jpg")
                .build();
        userRepository.saveAndFlush(user);

        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + "/" + user.getId())
                        .with(csrf()))
                .andExpect(status().isNoContent());
        Assertions.assertThat(userService.findById(user.getId()).orElse(null)).isNull();
    }
}
