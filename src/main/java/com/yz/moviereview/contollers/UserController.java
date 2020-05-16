package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.requests.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController extends Controller {

    @PostMapping(value = "/login")
    public @ResponseBody UserRequest login(@RequestBody UserRequest request){
        User userDB = userService.login(request.getUsername(), request.getPassword());
        request.setToken(userDB.getToken());
        return request;
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
