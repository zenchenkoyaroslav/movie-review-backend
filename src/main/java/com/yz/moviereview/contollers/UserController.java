package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController extends Controller {

    @PostMapping("/login")
    public @ResponseBody User login(@RequestBody User user){
        User userDB = userService.login(user.getUsername(), user.getPassword());
        return userDB;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        User userDB = null;
        try {
            userDB = getUser(request);
            userService.logout(userDB);
        } catch (ValidationException e){
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
