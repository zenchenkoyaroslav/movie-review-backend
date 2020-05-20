package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.requests.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController extends Controller {

    @GetMapping(value = "/current")
    public @ResponseBody User getCurrentUser(HttpServletRequest request){
        return getUser(request);
    }

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

    @PostMapping
    public @ResponseBody User newUser(User user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public @ResponseBody User updateUser(@PathVariable("id") Long id, @RequestBody User user, HttpServletRequest request){
        User current = getUser(request);
        if (current.getId() != id){
            if (current.getRole() != USERROLE.ADMIN){
                throw new ValidationException("User is not admin");
            }
        }
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id, HttpServletRequest request) {
        User current = getUser(request);
        if (current.getRole() != USERROLE.ADMIN){
            throw new ValidationException("User is not admin");
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
