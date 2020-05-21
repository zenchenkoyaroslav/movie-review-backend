package com.yz.moviereview.service;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.entities.Review;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.repositories.ReviewRepository;
import com.yz.moviereview.requests.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;

    public Review addReview(ReviewRequest review){
        Objects.requireNonNull(review);
        Review newReview = new Review();
        if (StringUtils.isEmpty(review.getTitle())){
            throw new ValidationException("Title is empty");
        }
        newReview.setTitle(review.getTitle());
        if (StringUtils.isEmpty(review.getContent())){
            throw new ValidationException("Content is empty");
        }
        newReview.setContent(review.getContent());
        if (review.getRate() == null){
            throw new ValidationException("Rate is null");
        }
        if (review.getRate() < 1 || review.getRate() > 5){
            throw new ValidationException("Rate should be in interval from 1 to 5");
        }
        newReview.setRate(review.getRate());
        Film film = filmService.getFilm(review.getFilmId());
        if (film == null){
            throw new ValidationException("Film doesnt exist");
        }
        newReview.setFilm(film);
        User user = userService.getUser(review.getUserId());
        if (user == null){
            throw new ValidationException("User doesnt exist");
        }
        if (user.getRole() == USERROLE.DELETED){
            throw new ValidationException("User is deleted");
        }
        newReview.setUser(user);
        Review reviewDB = reviewRepository.findByUser_IdAndFilm_Id(user.getId(), film.getId());
        if (reviewDB != null){
            throw new ValidationException("User already add review");
        }
        return reviewRepository.save(newReview);
    }

}
