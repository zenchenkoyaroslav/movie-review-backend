package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class Controller {
    @Autowired
    protected UserService userService;

    protected User getUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authHeader)){
            throw new ValidationException("Authorization header is missing or empty");
        }
        User user = userService.getUserByToken(authHeader);
        if (user == null){
            throw new ValidationException("Authorization failed! Wrong token");
        }
        return user;
    }
}
