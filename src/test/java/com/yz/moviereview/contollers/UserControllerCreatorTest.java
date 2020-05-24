package com.yz.moviereview.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.moviereview.Creator;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerCreatorTest {
    private static final Long USER_ID_1 = 1L;
    private static final String USER_USERNAME_1 = "user";
    private static final String USER_NAME_1 = "name";
    private static final String USER_PASSWORD_1 = "password";
    private static final String USER_PASSWORD_HASH_1 = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";


    @Autowired
    private UserController userController;

    @Autowired
    private Creator creator;

    private User user;

    @Before
    public void init(){
        user = new User();
        //user.setId(USER_ID_1);
        user.setName(USER_NAME_1);
        user.setUsername(USER_USERNAME_1);
        user.setPassword(USER_PASSWORD_1);
        user.setEmail("test@test.test");
        user.setRole(USERROLE.USER);
    }

    @Test
    public void newUserTest() {
        creator.saveEntity(user);
        User newUser = userController.newUser(user);
        Assert.assertEquals(newUser.getName(), user.getName());
    }

}
