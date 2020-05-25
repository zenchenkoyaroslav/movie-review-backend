package com.yz.moviereview.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.repositories.UserRepository;
import com.yz.moviereview.requests.UserRequest;
import org.hibernate.mapping.Any;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final Long USER_ID_1 = 1L;
    private static final String USER_USERNAME_1 = "user";
    private static final String USER_NAME_1 = "name";
    private static final String USER_PASSWORD_1 = "password";
    private static final String USER_PASSWORD_HASH_1 = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @MockBean(name = "userRepository")
    private UserRepository userRepository;


    @Before
    public void init(){
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setId(USER_ID_1);
        user.setName(USER_NAME_1);
        user.setUsername(USER_USERNAME_1);
        user.setPassword(USER_PASSWORD_HASH_1);
        user.setEmail("test@test.test");
        user.setRole(USERROLE.USER);
        Mockito.when(userRepository.findByUsername(USER_USERNAME_1)).thenReturn(user);
        Mockito.when(userRepository.save(any())).thenReturn(user);
    }

    @Test
    public void loginSuccess() throws Exception {
        UserRequest user = new UserRequest();
        user.setUsername(USER_USERNAME_1);
        user.setPassword(USER_PASSWORD_1);
        String content = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mvc.perform(post("/users/login").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        content = mvcResult.getResponse().getContentAsString();
        UserRequest response = objectMapper.readValue(content, UserRequest.class);
        assertNotNull(response.getToken());
    }

    @Test
    public void loginFailedWrongUsername() throws Exception {
        UserRequest user = new UserRequest();
        user.setUsername("WrongUsername");
        user.setPassword(USER_PASSWORD_1);
        String content = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mvc.perform(post("/users/login").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    public void loginFailedWrongPassword() throws Exception {
        UserRequest user = new UserRequest();
        user.setUsername(USER_USERNAME_1);
        user.setPassword("WrongPassword");
        String content = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mvc.perform(post("/users/login").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
    }
}
