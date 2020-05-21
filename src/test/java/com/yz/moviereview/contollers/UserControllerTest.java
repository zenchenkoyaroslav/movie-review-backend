package com.yz.moviereview.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.requests.UserRequest;
import com.yz.moviereview.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final Long USER_ID_1 = 1L;
    private static final String USER_USERNAME_1 = "user";
    private static final String USER_PASSWORD_1 = "password";
    private static final String USER_PASSWORD_HASH_1 = "$2a$10$ITjRTdRw4E4n3aa2z0EWteNOavKfBZBl0dS4EkYtZiIKmcL2U9rbC";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    User user;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(USER_ID_1);
        user.setUsername(USER_USERNAME_1);
        user.setPassword(USER_PASSWORD_HASH_1);
        user.setEmail("test@test.test");
        user.setRole(USERROLE.USER);
        Mockito.when(userService.getUser(user.getId())).thenReturn(user);
    }

    @Test
    public void loginSuccess() throws Exception {
        String content = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mvc.perform(post("/users/login").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        content = mvcResult.getResponse().getContentAsString();
        User response = objectMapper.readValue(content, User.class);
        assertNotNull(response.getToken());
    }

    @Test
    public void loginFailedWrongUsername() throws Exception {
        UserRequest user = new UserRequest();
        user.setUsername(USER_USERNAME_1);
        user.setPassword(USER_PASSWORD_1);
        String content = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mvc.perform(post("/users/login").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    public void loginFailedWrongPassword() throws Exception {
        User user = new User();
        user.setUsername(USER_USERNAME_1);
        user.setPassword("WrongPassword");
        String content = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mvc.perform(post("/users/login").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
    }
}
