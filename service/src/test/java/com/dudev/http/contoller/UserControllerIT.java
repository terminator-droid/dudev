package com.dudev.http.contoller;

import com.dudev.basetest.IntegrationTestBase;
import com.dudev.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;

import static com.dudev.dto.UserCreateEditDto.Fields.address;
import static com.dudev.dto.UserCreateEditDto.Fields.fullName;
import static com.dudev.dto.UserCreateEditDto.Fields.phoneNumber;
import static com.dudev.dto.UserCreateEditDto.Fields.rawPassword;
import static com.dudev.dto.UserCreateEditDto.Fields.role;
import static com.dudev.dto.UserCreateEditDto.Fields.username;
import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
@WithMockUser(username = "testUsername", password = "testPassword")
public class UserControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    private final EntityManager entityManager;

//    @BeforeEach
//    void init() {
//        List<GrantedAuthority> roles = Arrays.asList(Role.USER, Role.ADMIN);
//
//        User testUser = new User("testUsername", "testPassword", roles);
//        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), roles);
//
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//        securityContext.setAuthentication(authenticationToken);
//        SecurityContextHolder.setContext(securityContext);
//    }

    @Test
    void findAll() throws Exception {
        EntityUtil.insertUsers(entityManager);
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(5)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "tester")
                        .param(role, "ADMIN")
                        .param(rawPassword, "password")
                        .param(address, "address")
                        .param(phoneNumber, "phoneNumber")
                        .param(fullName, "fullname"))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/users/*"));
    }

    @Test
    void update() throws Exception {
        EntityUtil.insertUsers(entityManager);
        mockMvc.perform(post(format("/users/%d/update", 1))
                        .param(username, "tester")
                        .param(role, "ADMIN")
                        .param(rawPassword, "password")
                        .param(address, "address")
                        .param(phoneNumber, "phoneNumber")
                        .param(fullName, "fullname"))
                .andExpectAll(status().is3xxRedirection(), redirectedUrlPattern("/users/*"));
    }

    @Test
    void delete() throws Exception {
        EntityUtil.insertUsers(entityManager);
        mockMvc.perform(post(format("/users/%d/delete", 1)))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/users"));
    }
}
