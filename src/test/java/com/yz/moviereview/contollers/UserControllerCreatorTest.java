package com.yz.moviereview.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.moviereview.Creator;
import com.yz.moviereview.entities.Film;
import com.yz.moviereview.entities.Review;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.requests.ReviewRequest;
import com.yz.moviereview.service.ReviewService;
import com.yz.moviereview.service.UserService;
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

    private static final Long FILM_ID_1 = 1L;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private Creator creator;

    private User user;

    @Before
    public void init(){
        user = new User();
        //user.setId(USER_ID_1);
        user.setName(USER_NAME_1);
        user.setUsername(USER_USERNAME_1);
        user.setPassword(USER_PASSWORD_HASH_1);
        user.setEmail("test@test.test");
        user.setRole(USERROLE.USER);
    }

    @Test
    public void addReviewTest() {
        User newUser = creator.saveEntity(user);

        Film film = new Film();
        //film.setId(FILM_ID_1);
        film.setCountry("USA");
        film.setDescription("blabla");
        film.setTitle("film bla bla");
        film.setYear(1999);
        film.setPoster("https://i2.rozetka.ua/goods/14858384/139793131_images_14858384305.jpg");

        Film newFilm = creator.saveEntity(film);

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setUserId(newUser.getId());
        reviewRequest.setFilmId(newFilm.getId());
        reviewRequest.setContent("bla bla");
        reviewRequest.setRate(5);
        reviewRequest.setTitle("bla bla");

        Review newReview = reviewService.addReview(reviewRequest);

        Assert.assertNotNull(newReview.getId());
    }

}
