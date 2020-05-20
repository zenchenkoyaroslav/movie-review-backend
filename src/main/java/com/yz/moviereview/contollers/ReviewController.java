package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.Review;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends Controller {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public @ResponseBody Review addReview(@RequestBody Review review, HttpServletRequest request){
        User user = getUser(request);
        review.setUser(user);
        return reviewService.addReview(review);
    }
}
